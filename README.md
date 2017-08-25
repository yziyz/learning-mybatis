# learning-mybatis
# 1 内容 
1) 整合SpringBoot和MyBatis
2) 实现CRUD
3) 动态SQL及分页
4) 实现一对一映射
5) 实现一对多映射

# 2 准备
## 2.1 项目结构
```
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── org
        │       └── cdjavaer
        │           └── learning
        │               └── mybatis
        │                   ├── Application.java
        │                   ├── domain
        │                   │   ├── City.java
        │                   │   ├── Order.java
        │                   │   └── User.java
        │                   ├── dto
        │                   │   ├── CreateUserDto.java
        │                   │   ├── IndexCityDto.java
        │                   │   ├── IndexUserDto.java
        │                   │   └── UpdateUserDto.java
        │                   ├── mapper
        │                   │   ├── CityMapper.java
        │                   │   ├── OrderMapper.java
        │                   │   └── UserMapper.java
        │                   ├── service
        │                   │   ├── CityService.java
        │                   │   ├── CitySqlProvider.java
        │                   │   ├── OrderService.java
        │                   │   ├── OrderSqlProvider.java
        │                   │   ├── UserService.java
        │                   │   └── UserSqlProvider.java
        │                   └── web
        │                       ├── CityController.java
        │                       ├── OrderController.java
        │                       └── UserController.java
        └── resources
            └── application.yml
```
## 2.2 添加SpringBoot和Web起步依赖：
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
## 2.3 添加MyBatis依赖
```
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.1</version>
</dependency>
```
## 2.4 添加PageHelper依赖
```
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.1.3</version>
</dependency>
```
## 2.5 添加Pavilion依赖
```
<dependency>
    <groupId>cn.com.zdht</groupId>
    <artifactId>pavilion</artifactId>
    <version>1.5.2</version>
</dependency>
```

## 2.6 添加PostgreSQL JDBC和JPA依赖
```
<dependency>
    <groupId>javax.persistence</groupId>
    <artifactId>persistence-api</artifactId>
    <version>1.0.2</version>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.0.0</version>
</dependency>
```

## 2.7 创建数据表
本项目使用PostgreSQL，三个`实体类`对应三张`数据表`，其DDL如下：
```
--用户
CREATE TABLE users (
    id character varying(36) NOT NULL,
    name character varying(20) NOT NULL,
    birth_day timestamp without time zone NOT NULL,
    created_at timestamp without time zone NOT NULL,
    city_id integer
);
ALTER TABLE ONLY users ADD CONSTRAINT users_pkey PRIMARY KEY (id);
ALTER TABLE ONLY users ADD CONSTRAINT users_city_id_fkey FOREIGN KEY (city_id) REFERENCES cities(id);
--城市
CREATE TABLE cities (
    id integer NOT NULL,
    name character varying(20) NOT NULL
);
ALTER TABLE ONLY cities ADD CONSTRAINT cities_pkey PRIMARY KEY (id);
--订单
CREATE TABLE orders (
    id character varying(36) NOT NULL,
    user_id character varying(36),
    created_at timestamp without time zone NOT NULL
);
ALTER TABLE ONLY orders ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
ALTER TABLE ONLY orders ADD CONSTRAINT orders_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);
```

# 3 整合SpringBoot和MyBatis
## 3.1 配置数据库信息
配置数据库信息，在application.yml中添加：
```
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/test
    username: postgres
    password: 123456
```
配置MyBatis:
```
mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    #开启蛇形与驼峰自动转换
    map-underscore-to-camel-case: true
    default-fetch-size: 100
    default-statement-timeout: 30
```
配置PageHelper
```
#分页
pagehelper:
  helperDialect: postgresql
  reasonable: true
  supportMethodArguments: true
  params: count=countSql
```
在项目启动时，`mybatis-spring-boot-starter`自动完成整合工作。

# 4 实现CRUD
> 以`org.cdjavaer.learning.mybatis.domain.City`类为例，实现CRUD(添加、读取、更新、删除)

## 4.1 Read
> Read即查询，对应SQL的SELECT，在讨论如何实现之前，我们需要简单了解一下MyBatis的使用机制；
Mybatis通过`实体类(entity)`对应的`映射器接口(mapper)`实现`SQL与操作(method)的映射`，用户提供SQL即可实现操作（调用mappper方法）。

> 其中SQL由用户定义，分为`静态SQL`与`动态SQL”两种情况`。`静态SQL`由mapper中方法的`@Select`/`@Insert`/`@Update`/`@Delete`的`value`方法指定，将SQL字符串赋值给`value`即可。
`动态SQL`由mapper中方法的`@SelectProvider`/`@InsertProvider`/`@UpdateProvider`/`@DeleteProvider`的`type`方法和`method`方法指定，指定`SQL提供类(SqlProvider)`给`type`，指定`SQL提供类`的对应本方法的方法名给`method`。

创建[实体类](./src/main/java/org/cdjavaer/learning/mybatis/domain/City.java)，注意点如下：

### 注意点1 @Table注解
使用`javax.persistence.Table`注解实体类，并将实体类对应的表名指定给`name`方法，代码片段如下：
```
@Table(name = "cities")
public class City {
```

### 注意点2 @Id注解
使用`javax.persistence.Id`注解实体类的ID域（field，对应表的主键），代码片段如下：
```
@Id
private Integer id;
```

创建[SQL提供类](./src/main/java/org/cdjavaer/learning/mybatis/service/CitySqlProvider.java)，注意点如下：

### 注意点1 继承BaseSqlProvider类
> 小组内部实现了`cn.com.zdht.pavilion.mybatis.BaseSqlProvider`，为继承该类的SqlProvider提供`insertSelective`/`insert`/`update`/`updateSelective`/`deleteById`/`delete`/`selectById`/`selectByIds`/`count`/`countWhere`方法，提高编码效率。

继承`cn.com.zdht.pavilion.mybatis.BaseSqlProvider`，并在参数化类型中指定`实体类`及其ID类型，代码片段如下：
```
public class CitySqlProvider extends BaseSqlProvider<City, Integer>
```

创建[映射器](./src/main/java/org/cdjavaer/learning/mybatis/mapper/CityMapper.java)，注意点如下：
### 注意点1 映射器是接口类型(interface)
实体类对应的映射器是接口，不要误写成其他类型。

### 注意点2 @Mapper注解
使用`org.apache.ibatis.annotations.Mapper`注解映射器，代码片段如下：
```
@Mapper
public interface CityMapper {
```

### 注意点3 select方法及其注解
本方法根据id查询并返回对应的实体，代码片段如下：
```
//@Select(value = "SELECT id, name FROM cities WHERE id = #{id}")
@SelectProvider(type = CitySqlProvider.class, method = "selectById")
City select(Integer id);
```
第一行，使用`@Select`注解指定了SQL语句，对于简单的查询，由于效率低下，故使用`@SelectProvider`来提供SQL；

第二行，使用`@SelectProvider`指定了SQL提供类`org.cdjavaer.learning.mybatis.service.CitySqlProvider`，以及SQL提供类的方法`selectById`；

第三行，定义方法，注意方法的参数要对应方法的功能；

创建[服务类](./src/main/java/org/cdjavaer/learning/mybatis/service/CityService.java)，注意点如下：

### 注意点1 @Service注解
Spring提供@Service注解来表示一个注解类是“服务”（参见[领域驱动设计](https://en.wikipedia.org/wiki/Domain-driven_design)）；

### 注意点2 @Transactional注解
Spring提供@Transaction注解来描述方法或类上的事务属性；

### 注意点3 @Autowired注解
Spring提供@Autowired来标记一个构造函数、字段、修改器(setter方法)或者配置方法，以便注入依赖；

至此，我们可以在服务类中使用映射器提供的select方法，实现读取，例如：
```
/**
 * 返回根据指定ID对应的城市实例
 * @param id 指定ID
 * @return 对应的城市实例
 */
public City select(Integer id) {
    return cityMapper.select(id);
}
```

## 4.2 Create/Update/Delete
> Create即创建，对应SQL的INSERT；Update即更新，对应SQL的UPDATE；Delete即删除，对应SQL的DELETE。

在`映射器`中使用`SQL提供类`实现`insert`/`update`/`delete`方法，代码片段如下：
```
@InsertProvider(type = CitySqlProvider.class, method = "insertSelective")
int insert(City city);

@UpdateProvider(type = CitySqlProvider.class, method = "updateSelective")
int update(City city);

@DeleteProvider(type = CitySqlProvider.class, method = "deleteById")
int delete(String id);
```
> 注：insert/delete/update方法的返回值均是此次操作影响的数据行数。

省略在`服务类`中实现方法；

# 5 动态SQL和分页
现有`查询`需求如下：用户输入关键词、页码和每页最大结果数量，程序返回`城市名称`模糊匹配`关键词`的城市列表。

首先，根据需求实现[查询城市DTO](./src/main/java/org/cdjavaer/learning/mybatis/dto/IndexCityDto.java)，用来封装用户输入的参数，如下：
```
package org.cdjavaer.learning.mybatis.dto;

public class IndexCityDto {
    /**
     * 用于模糊查询的名称
     */
    private String name;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页最多结果数量
     */
    private Integer size;

    //省略访问器和修改器方法
}
```

其次，在`SQL提供类`中实现动态提供SQL的方法，如下：
```
/**
 * 返回根据指定的IndexCityDto实例生成的动态SQL
 *
 * @param dto 指定的IndexCityDto实例
 * @return 根据指定的IndexCityDto实例生成的动态SQL
 */
public String selectByDto(final IndexCityDto dto) {
    return new SQL() {
        {
            SELECT("id", "name");
            FROM("cities");
            if (dto.getName() != null) {
                WHERE("name LIKE '%' || #{name} || '%'");
            }
            ORDER_BY("id");
        }
    }.toString();
}
```

再次，在`映射类`中实现该查询方法，如下：
```
@SelectProvider(type = CitySqlProvider.class, method = "selectByDto")
List<City> selectByDto(IndexCityDto dto);
```

最后，在`服务类`中实现该查询方法，如下：
```
/**
 * 根据指定的查询城市DTO实例返回匹配的城市列表
 * @param dto 指定的查询城市DTO实例
 * @return 匹配的城市列表
 */
public List<City> selectByDto(IndexCityDto dto) {
    if (dto.getPage() != null && dto.getSize() != null) {
        PageHelper.startPage(dto.getPage(), dto.getSize());//分页
    }
    return cityMapper.selectByDto(dto);
}
```
### 注意点1 分页
使用`com.github.pagehelper.PageHelper`进行分页；

# 6 实现一对一映射
## 6.1 背景
[用户类](./src/main/java/org/cdjavaer/learning/mybatis/domain/User.java)中嵌套了一个城市类(city)，用户表`users`中的`city_id`列对应城市表`cities`的`id`列，外键约束；

## 6.2 实现
创建[映射器](./src/main/java/org/cdjavaer/learning/mybatis/mapper/UserMapper.java)，并添加如下方法：
```
@Results(value = {
        @Result(property = "city", column = "city_id",
                javaType = City.class, jdbcType = JdbcType.INTEGER,
                one = @One(select = "org.cdjavaer.learning.mybatis.mapper.CityMapper.select", fetchType = FetchType.EAGER))
})
@SelectProvider(type = UserSqlProvider.class, method = "selectById")
User select(String id);
```
### 注意点1 @Results注解
MyBatis提供`org.apache.ibatis.annotations.Results`注解，实现结果映射的列表；

### 注意点2 @Result注解
MyBatis提供`org.apache.ibatis.annotations.Result`注解，实现列(column)和属性(property)之间的单独结果映射；

* property - 属性，即对象中的域(field)，此处为`用户类User`的`域city`，类型是City，需要在`javaType`方法中指明；
* column - 列，即表中的列，此处为`用户表users`的`列city_id`，类型是`JdbcType.INTEGER`，需要在`jdbcType`中指明；
* javaType - 属性的类型；
* jdbcType - 列的类型，定义在`org.apache.ibatis.type.JdbcType`中；
* one - 一对一映射，此处为`用户类User`与`城市类City`的联系；

### 注意点3 @One注解
MyBatis提供`org.apache.ibatis.annotations.One`注解来实现复杂类型的单独属性值映射；
* select - 获取属性值的方法的完全限定名(fully qualified name)，例如此处获取城市的方法为org.cdjavaer.learning.mybatis.mapper.CityMapper类的select方法，应写为select = "org.cdjavaer.learning.mybatis.mapper.CityMapper.select"
* fetchType - 加载类型，分为延迟加载(LAZY)和非延迟加载(EAGER)，定义在`org.apache.ibatis.mapping.FetchType`中；

创建[服务类](/home/yuanzhen/project/learning-mybatis/src/main/java/org/cdjavaer/learning/mybatis/service/UserService.java)，并添加如下方法：
```
public User select(String id) {
    return userMapper.select(id);
}
```
与无嵌套的查询一样简单，实现一对一映射完成，但是`用户类User`内的`订单列表orders`，属于一对多映射，还没有实现，现在的查询结果中`orders`为`null`，接下来，我们实现`一对多映射`

# 7 实现一对多映射
## 7.1 背景
[用户类](./src/main/java/org/cdjavaer/learning/mybatis/domain/User.java)中嵌套了一个订单类的列表(orders)，`订单表orders`中的`user_id`列对应`用户表users`的`id`列，外键约束；

## 7.2 实现
在`映射器UserMapper`中的`方法select`的`注解@Results`中添加如下一条`注解@Result`:
```
@Result(property = "orders", column = "id",
        many = @Many(select = "org.cdjavaer.learning.mybatis.mapper.OrderMapper.selectByUserId",
                fetchType = FetchType.LAZY))
```
### 注意点1 @Result注解中的many方法
many， 一对多映射，此处为`用户类User`与`订单类Order`的联系；

### 注意点2 @Many注解
MyBatis提供`org.apache.ibatis.annotations.Many`注解来实现复杂类型中的集合属性的映射，其中的`select`和`fetchType`与`@One`相同。

无需修改`服务类UserService`，完成实现一对多映射。

# 8 参考
1) http://www.mybatis.org/mybatis-3/zh/configuration.html#typeHandlers
2) http://mybatis.co.uk/index.php/category/mybatis-annotations-examples
3) https://en.wikipedia.org/wiki/Domain-driven_design
4) https://docs.oracle.com/javase/tutorial/

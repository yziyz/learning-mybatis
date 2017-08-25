package org.cdjavaer.learning.mybatis.dto;

/**
 * 查询城市DTO
 *
 * @author 袁臻
 * 2017/08/23 16:52
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "IndexCityDto{" +
                "name='" + name + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}

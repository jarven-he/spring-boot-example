package com.jarven.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jarven.example.util.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 分页对象
 * @author: 何佳文
 * @date: 2018/11/4 18:59
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PageVo<T> implements Serializable {

    private static final long serialVersionUID = -3190765019377642623L;


    public PageVo(Page page) {
        this.page = page;
        this.pageIndex = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalSize = page.getTotalElements();
    }

    @ApiModelProperty(value = "当前页码")
    private Integer pageIndex;

    @ApiModelProperty(value = "每页多少条")
    private Integer pageSize;

    @ApiModelProperty(value = "总条数")
    private Long totalSize;

    @ApiModelProperty(value = "数据")
    private List<T> content;

    /**
     * 临时的JPA page 对象
     */
    @JsonIgnore
    private transient Page page;

    public PageVo<T> convertTo(Class<T> clazz) {
        var list = new ArrayList<T>();
        if (!CollectionUtils.isEmpty(page.getContent())) {
            for (Object object : page.getContent()) {
                list.add(BeanUtil.copyProperties(clazz, object));
            }
        }
        this.content = list;
        return this;
    }
}



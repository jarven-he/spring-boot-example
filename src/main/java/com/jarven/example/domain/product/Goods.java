package com.jarven.example.domain.product;

import com.jarven.example.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-04-27 10:37
 */

@Data
@Entity
@Accessors(chain = true)
@Table(name = "t_goods")
@EqualsAndHashCode(callSuper = true)
public class Goods extends BaseEntity {

    /**
     * 商品ID
     */
    @Id
    @GeneratedValue
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

}

package com.jarven.example.domain.product;

import com.jarven.example.domain.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @description: 商品
 * @author: 何佳文
 * @date: 2019-04-24 11:02
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "t_product")
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Product extends BaseEntity {

    /**
     * 商品ID
     */
    @Id
    @GeneratedValue
    private Integer productId;


    /**
     * 商品类型: 1:普通商品
     */
    private Short productType;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 商品规格
     */
    @Type(type = "json")
    @Column(name = "spec", columnDefinition = "json")
    private List<ProductSpec> spec;

    /**
     * 商品标签
     */
    private String tag;

    /**
     * 商品分类
     */
    private Integer category;

    /**
     * 特色介绍
     */
    private String productDesc;

    /**
     * 购买须知
     */
    private String buyDesc;

    /**
     * 已售
     */
    private Integer ticketCount;

    /**
     * 多规格关联ID
     */
    private Integer goodsId;

    /**
     * 推广佣金
     */
    private BigDecimal popularizeAmount;

    /**
     * 推广团长佣金
     */
    private BigDecimal popularizeColonelAmount;

    /**
     * 销售状态 0:下架 1:上架
     */
    private Integer saleState;

    /**
     * 标题图片
     */
    private String titleImgUrl;

    /**
     * 分享图片
     */
    private String shareImgUrl;

    /**
     * 推广图
     */
    private String propagandaImgUrl;

    /**
     * 滚动图片
     */
    @Type(type = "json")
    @Column(name = "rolling_img_url", columnDefinition = "json")
    private List<String> rollingImgUrl;

    /**
     * 排序权重
     */
    private Integer sortWeight;

    /**
     * 推荐商品
     */
    @Type(type = "json")
    @Column(name = "recommend_product", columnDefinition = "json")
    private Set<Integer> recommendProduct;

    /**
     * 可见用户
     */
    @Type(type = "json")
    @Column(name = "visible_user", columnDefinition = "json")
    private Set<String> visibleUser;

    /**
     * 是否前端列表展示 是否前端展示 0:不展示 1展示
     */
    private Integer showList;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记 0:正常 1删除
     */
    private Integer deleteFlag;

}

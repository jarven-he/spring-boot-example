package com.jarven.example.constant;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019/9/6 9:08 上午
 */
public final class RedisKey {

    private RedisKey() {
    }

    /**
     * 商品库存
     */
    public static final String PRODUCT_SALE_INVENTORY_ = "P_S_I:";

    /**
     * 商品信息
     */
    public static final String PRODUCT = "PRODUCT:";

    /**
     * 商品详情信息
     */
    public static final String PRODUCT_DETAIL = "PRODUCT_DETAIL:";

    /**
     * 积分商品信息
     */
    public static final String POINT_PRODUCT = "POINT_PRODUCT:";

    /**
     * 积分锁
     */
    public static final String USER_POINT_LOCK = "USER_POINT_LOCK:";
}

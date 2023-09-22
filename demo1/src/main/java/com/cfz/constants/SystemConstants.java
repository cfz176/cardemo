package com.cfz.constants;

public class SystemConstants {

    /**
     * 是否显示默认值 del
     */
    public final static String DEL_DEFAULT = "0";

    /**
     * 工程师状态 0在忙、1空闲、2请假
     */
    public final static String MASTER_STATUS_DEFAULT = "0";

    /**
     *  订单状态 0刚下单，1已接单，2已到达正在修，3已完成，4已评价
     */
    public final static String ORDERS_STATUS_DEFAULT = "0";
    public final static String ORDERS_STATUS_RECEIVED = "1";
    public final static String ORDERS_STATUS_ARRIVED = "2";
    public final static String ORDERS_STATUS_FINISH = "3";
    public final static String ORDERS_STATUS_EVALUATED = "4";

    /**
     * 状态 0未使用 1已使用
     */
    public final static String CUSTOMER_COUPON_STATUS_DEFAULT = "0";
}

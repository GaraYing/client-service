package com.gara.lock_demo.domain;


/**
 * @description: Order订单实体类
 * @author: GaraYing
 * @create: 2018-08-14 10:45
 **/

public class Order {

    private String orderId; //订单ID
    private String orderTime; // 订单时间
    private Long orderMoney;
    private String orderStatus; //订单状态：0未处理/1处理中/2处理失败/3处理成功/4处理完成
    private String version; // 版本

    public Order() {
    }

    public Order(String orderId, String orderTime, Long orderMoney, String orderStatus, String version) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
        this.version = version;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderMoney=" + orderMoney +
                ", orderStatus='" + orderStatus + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}

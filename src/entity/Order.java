package entity;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String cusId;
    private String orderDate;
    private String orderTime;
    private double totalCost;
    //-------------
    private ArrayList<ItemDetail> details= new ArrayList();

    public Order() {
    }

    public Order(String orderId, String cusId, String orderDate, String orderTime, double totalCost, ArrayList<ItemDetail> details) {
        this.orderId = orderId;
        this.cusId = cusId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalCost = totalCost;
        this.details = details;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<ItemDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<ItemDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", cusId='" + cusId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", totalCost=" + totalCost +
                ", details=" + details +
                '}';
    }
}

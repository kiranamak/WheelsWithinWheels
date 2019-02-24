/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.time.LocalDate;


/**
 *
 * @author asa
 */
public class Order implements Transaction {

    private final Customer customer;
    private final String brand;
    private final String level;
    private final String comment;
    private final LocalDate orderDate;
    private final LocalDate completeDate = null;
    private OrderStatus status;
    private final int orderNumber;

    private static int lastOrderNumber = 0;
    
    Order(Customer customer, LocalDate orderDate, String brand, String level, String comment){
        this.customer = customer;
        this.brand = brand;
        this.level = level;
        this.comment = comment;
        this.orderDate = orderDate;
        this.status = OrderStatus.PENDING;
        this.orderNumber = ++Order.lastOrderNumber;
        
    }

    RepairPrice getRepairPrice() {
        return RepairPriceTable.shared.getPrice(brand, level);
    }

    LocalDate getPromisedDate() {
        return orderDate.plusDays(getRepairPrice().getRepairLength());
    }

    OrderStatus getStatus(){
        return status;
    }

    void setStatus(OrderStatus status) {
        this.status = status;
    }

    int getOrderNumber() {
        return orderNumber;
    }

    TuneupLevel getRepairLevel() {
        return level;
    }

    @Override
    public String getReport() {
        //TODO: Should use StringBuilder.
        String report = "";
        report += orderDate + "\t";
        report += "#" + String.valueOf(orderNumber);
        report += status.value();
        report += customer.shortReport();
        report += brand;
        report += level + "; ";
        report += "due:" + getPromisedDate();
        if (!comment.equals("")) { report += " comment: " + comment; }
        return report;
    }
    
    Customer getCustomer(){
        return customer;
    }
    
    String getComment(){
        return comment;
    }
    
    
    @Override
    public LocalDate getDate(){
        return orderDate;
    }
    
}

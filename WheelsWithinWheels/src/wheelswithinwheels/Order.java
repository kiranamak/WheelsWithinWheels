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

    
    Order(Customer customer, String brand, String level,  LocalDate orderDate, String comment, int orderNumber){
        this.customer = customer;
        this.brand = brand;
        this.level = level;
        this.comment = comment;
        this.orderDate = orderDate;
        this.status = OrderStatus.PENDING;
        this.orderNumber = orderNumber;
    }

    RepairPriceEntry getRepairPrice(WWWEnvironment environment) {
        return environment.getRepairPriceTable().getPrice(brand, level);
    }

    LocalDate getPromisedDate(WWWEnvironment environment) {
        return orderDate.plusDays(getRepairPrice(environment).getRepairLength());
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

    String getRepairLevel() {
        return level;
    }

    @Override
    public String getReport(WWWEnvironment environment) {
        //TODO: Should use StringBuilder.
        String report = "";
        report += orderDate + "\t";
        report += "#" + String.valueOf(orderNumber);
        report += status.value();
        report += customer.shortReport();
        report += brand;
        report += level + "; ";
        report += "due:" + getPromisedDate(environment);
        if (!comment.equals("")) { report += " comment: " + comment; }
        return report;
    }
    
    public String shortReport() {
        StringBuilder report = new StringBuilder();
        report.append("Order #" + orderNumber);
        report.append(brand + ", " + level);
        return report.toString();
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

    public void complete(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}

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
    private LocalDate completeDate = null;
    private LocalDate returnedDate = null;
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

    public LocalDate getPromisedDate(WWWEnvironment environment) {
        return orderDate.plusDays(getRepairPrice(environment).getRepairLength());
    }

    public OrderStatus getStatus(){
        return status;
    }

    void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    
    public String getBrand(){
       return brand;
    }

    public String getRepairLevel() {
        return level;
    }

    @Override
    public String getReport(WWWEnvironment environment) {
        StringBuilder report = new StringBuilder();
        report.append(orderDate).append("\t");
        report.append("ORDER:  \t");
        report.append("#").append(String.valueOf(orderNumber)).append(" ");
        report.append(status.value()).append(" ");
        report.append(customer.shortReport()).append(" ");
        report.append(brand).append(" ");
        report.append(level).append("; ");
        report.append("due: ").append(getPromisedDate(environment));
        if (!comment.equals("")) { report.append(" comment: ").append(comment); }
        return report.toString();
    }
    
    public String shortReport() {
        StringBuilder report = new StringBuilder();
        report.append("Order #").append(orderNumber).append(" ");
        report.append(brand).append(", ").append(level);
        return report.toString();
    }
    
    public Customer getCustomer(){
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
        status = OrderStatus.COMPLETED;
        this.completeDate = date;
    }
    
    public void returned(LocalDate date) {
        status = OrderStatus.RETURNED;
        this.returnedDate = date;
    } 
    
    @Override
    public String getSaveText(WWWEnvironment environment){
        return "rnon " + getOrderNumber() + "\n" +
                "addo "+customer.getCustomerNumber()+" "
                +getDate()+" "
                +getBrand()+" "
                +getRepairLevel()+" "
                +comment;
    }
}

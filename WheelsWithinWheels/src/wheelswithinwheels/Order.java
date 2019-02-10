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
    private final TuneupLevel level;
    private final String comment;
    private final LocalDate orderDate;
    private final LocalDate completeDate = null;
    private OrderStatus status;
    private final int orderNumber;

    private static int lastOrderNumber = 0;
    
    Order(Customer customer, String brand, TuneupLevel level, String comment,LocalDate orderDate){
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
        throw new UnsupportedOperationException();
    }
    
    Customer getCustomer(){
        return customer;
    }
    
    String getComment(){
        return comment;
    }
    
    LocalDate getOrderDate(){
        return orderDate;
    }
    
}
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
public class Customer {

    private final String firstName;
    private final String lastName;
    private final int customerNumber;
    private OrderStatus status;
    private LocalDate dateCompleted;
    private LocalDate dateReturned;
    
    private static int lastCustomerNumber = 0;
    
    
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = ++Customer.lastCustomerNumber;
        status = OrderStatus.PENDING;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getFullName() {
        return firstName + lastName;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }
    
    static void resetCustomerNumbers(){
        lastCustomerNumber = 0;
    }

    public void complete(LocalDate date) {
        status = OrderStatus.COMPLETED;
        this.dateCompleted = date;
    }
    
    public void returned(LocalDate date) {
        status = OrderStatus.RETURNED;
        this.dateReturned = date;
    } 
    
    public String shortReport() {
        //TODO: Should use StringBuilder.
        String report = "";
        report += firstName + " " + lastName;
        report += "(" + customerNumber + ")";
        return report;
    }
}

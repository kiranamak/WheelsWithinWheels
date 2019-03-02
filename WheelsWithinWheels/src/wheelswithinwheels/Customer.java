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
    
    Customer(String firstName, String lastName,int customerNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = customerNumber;
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
    

    
    public String shortReport() {
        //TODO: Should use StringBuilder.
        String report = "";
        report += firstName + " " + lastName;
        report += "(" + customerNumber + ")";
        return report;
    }
}

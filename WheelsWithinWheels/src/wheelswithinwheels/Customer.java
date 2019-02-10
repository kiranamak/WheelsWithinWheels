/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

/**
 *
 * @author asa
 */
public class Customer {

    private final String firstName;
    private final String lastName;
    private final int customerNumber;

    private static int lastCustomerNumber = 0;
    
    
    Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = ++Customer.lastCustomerNumber;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    int getCustomerNumber() {
        return customerNumber;
    }
    
    static void resetCustomerNumbers(){
        lastCustomerNumber = 0;
    }
}

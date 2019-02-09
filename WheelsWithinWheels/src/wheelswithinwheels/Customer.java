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
    String firstName;
    String lastName;
    int customerNumber;
    Customer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    String getFirstName(){return firstName;}
    String getLastName(){return lastName;}
    int getCustomerNumber(){return customerNumber;}
}

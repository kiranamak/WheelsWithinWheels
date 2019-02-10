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
public class Payment implements Transaction {

    Customer customer;
    int amount;
    LocalDate date;

    public Payment(Customer customer, LocalDate date, int amount) {
        this.customer = customer;
        this.amount = amount;
        this.date = date;
    }

    int getCustomerNumber() {
        return customer.getCustomerNumber();
    }

    int getAmount() {
        return amount;
    }

    LocalDate getDate() {
        return date;
    }

    @Override
    public String getReport() {
        throw new UnsupportedOperationException();
    }
}
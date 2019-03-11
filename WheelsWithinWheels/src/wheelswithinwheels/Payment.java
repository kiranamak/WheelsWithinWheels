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

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getReport(WWWEnvironment environment) {
        StringBuilder report = new StringBuilder();
        report.append(date).append("\t");
        report.append("$").append(String.valueOf(amount));
        report.append(customer.shortReport());
        return report.toString();
    }

    public Customer getCustomer() {
        return customer;
    }
    
    @Override
    public String getSaveText(WWWEnvironment environment) {
        return "addp "+getCustomerNumber()+" "+getDate()+" "+getAmount();
    }
}

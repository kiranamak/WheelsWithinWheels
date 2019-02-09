/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.util.Date;

/**
 *
 * @author asa
 */
public class Payment implements Transaction{
    int customerNumber;
    int amount;
    Date date;
    Payment(int customerNumber, int amount, Date date){
        this.customerNumber = customerNumber;
        this.amount = amount;
        this.date = date;
    }
    int getCustomerNumber(){return customerNumber;}
    int getAmount(){return amount;}
    Date getDate(){return date;}
    
    @Override
    public String getReport(){
        throw new UnsupportedOperationException();
    }
}

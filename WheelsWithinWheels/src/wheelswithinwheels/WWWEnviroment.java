/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author asa
 */
public class WWWEnviroment {

    public DateTimeFormatter dateFormatter;
    public Order[] getOrders(){
        throw new UnsupportedOperationException();
    }

    public Customer[] getCustomers(){
        throw new UnsupportedOperationException();
    }
    
    public Payment[] getPayments(){
        throw new UnsupportedOperationException();
    }
    
    public Transaction[] getTransactions(){
        throw new UnsupportedOperationException();
    }
    
    public String getStatementReport(){
        throw new UnsupportedOperationException();
    }
    
    public String getAccountsReceiveableReport(){
        throw new UnsupportedOperationException();
    }
    
    public void reset(){
        throw new UnsupportedOperationException();
    }

    public void persistTo(String filename) throws Exception{
        throw new UnsupportedOperationException();
    }
    
    //New
    public void addCustomer(Customer customer){
        throw new UnsupportedOperationException();
    }

    public Customer getCustomer(int customerNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public RepairPriceTable getPricesTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

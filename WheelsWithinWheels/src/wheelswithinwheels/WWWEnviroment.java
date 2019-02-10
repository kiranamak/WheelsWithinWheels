/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author asa
 */
public class WWWEnviroment {
    
    public DateTimeFormatter dateFormatter;
    private ArrayList<Order> orders = new ArrayList<>(100);
    private ArrayList<Customer> customersByName = new ArrayList<>(100);
    private ArrayList<Customer> customersByNumber = new ArrayList<>(100);
    private ArrayList<Payment> payments = new ArrayList<>(100);
    private ArrayList<Transaction> transactions = new ArrayList<>(100);
    private RepairPriceTable repairPriceTable;
    
    public ArrayList<Order> getOrders() { return orders; }
    public ArrayList<Customer> getCustomersByName() { return customersByName; }
    public ArrayList<Customer> getCustomersByNumber() { return customersByNumber; }
    public ArrayList<Payment> getPayments() { return payments; }
    public ArrayList<Transaction> getTransactions() { return transactions; }
    public RepairPriceTable getPricesTable() { return repairPriceTable; }
  
    public void reset() {
        orders = new ArrayList<>(orders.size());
        customersByName = new ArrayList<>(customersByName.size());
        customersByNumber = new ArrayList<>(customersByNumber.size());
        payments = new ArrayList<>(payments.size());
        transactions = new ArrayList<>(transactions.size());
    }
    public void persistTo(String filename) throws Exception{
        throw new UnsupportedOperationException();
    }
    
    public void addOrder(Order order) {
        orders.add(order);
        transactions.add(order);
    }
    
    public void addPayment(Payment payment) {
        payments.add(payment);
        transactions.add(payment);
    }
    
    public void addRepairPrice(RepairPrice price) {
        repairPriceTable.addPrice(price);
    }
    
    public void addCustomer(Customer customer) {
        addCustomerByName(customer);
        customersByNumber.add(customersByNumber.size(), customer);
    }
    
    private void addCustomerByName(Customer customer) {
        for (int i = 0; i < customersByName.size(); i++) {
            Customer c = customersByName.get(i);
            int compare = customer.getFullName().compareToIgnoreCase(c.getFullName());
            if (compare <= 0) { 
                customersByName.add(i, customer); 
                return;
            }
        }
    }
    
    public Customer getCustomer(int customerNumber) {
        return customersByNumber.get(customerNumber - 1);
    }
    
    public Order getOrder(int orderNumber) {
        return orders.get(orderNumber - 1);
    }
    
    public String getStatementReport() {
        throw new UnsupportedOperationException();
    }
    
    public String getAccountsReceiveableReport(){
        throw new UnsupportedOperationException();
    }
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.time.LocalDate;

/**
 *
 * @author asa
 */
public class WWWEnvironment {
    
    public  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    private ArrayList<Order> orders = new ArrayList<>(100);
    private ArrayList<Customer> customers = new ArrayList<>(100);
    private ArrayList<Payment> payments = new ArrayList<>(100);
    private ArrayList<Transaction> transactions = new ArrayList<>(100);
    private RepairPriceTable repairPriceTable;
    
    public Order[] getOrders() { 
        Order[] ordersArray = new Order[orders.size()];
        return orders.toArray(ordersArray);
    }
    
    public Customer[] getCustomersByName() { 
        Customer[] customersByNameArray = new Customer[customers.size()];
        customersByNameArray = customers.toArray(customersByNameArray);
        Comparator<Customer> comparator = null;
        comparator = (Customer a, Customer b)->
                        (a.getFullName()).compareTo(b.getFullName());
        Arrays.sort(customersByNameArray, comparator);
        return customersByNameArray;
    }
    
    public Customer[] getCustomersByNumber() {
        Customer[] customersByNumberArray = new Customer[customers.size()];
        return customers.toArray(customersByNumberArray);
    }
    
    public int getNumberCustomers() { return customers.size(); }
    
    public Payment[] getPayments() { 
        Payment[] paymentsArray = new Payment[payments.size()];
        return payments.toArray(paymentsArray);
    }
    
    public Transaction[] getTransactions() { 
        Transaction[] transactionsArray = new Payment[transactions.size()];
        transactionsArray = payments.toArray(transactionsArray);
        Comparator<Transaction> comparator = null;
        comparator = (Transaction a, Transaction b) -> 
                (a.getDate()).compareTo(b.getDate());
        Arrays.sort(transactionsArray, comparator);
        return transactionsArray;
    }
    
    public RepairPriceTable getPricesTable() { return repairPriceTable; }
  
    public void reset() {
        orders = new ArrayList<>(orders.size());
        customers = new ArrayList<>(customers.size());
        payments = new ArrayList<>(payments.size());
        transactions = new ArrayList<>(transactions.size());
    }
    public void persistTo(String filename) throws Exception{
        throw new UnsupportedOperationException();
    }
    
    public void addOrder(Customer customer, LocalDate date, String brand, String level, String comment) {
        orders.add(new Order(customer, date, brand, level, comment));
    }
    
    public void addPayment(Payment payment) {
        payments.add(payment);
    }
    
    public void addRepairPrice(RepairPrice price) {
        repairPriceTable.addPrice(price);
    }
    
    public void addCustomer(String fName, String lName) {
        customers.add(customers.size(), new Customer(fName, lName));
    }

    public Customer getCustomer(int customerNumber) {
        return customers.get(customerNumber - 1);
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

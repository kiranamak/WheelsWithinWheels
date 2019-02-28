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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.stream.Stream;

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
    
    public void makeFile(String file_path, String fileName) throws FileNotFoundException{
        //creates the file
        File file = new File(file_path);
        //names the file
        file = new File(fileName + ".txt");
    }
    public void saveToFile(String text, String file_path, boolean addValue) throws IOException{
        FileWriter writing = new FileWriter(file_path, addValue);
        PrintWriter saveToLine = new PrintWriter(writing);
        //adds to file
        saveToLine.printf( "%s" + "%n" , text);
        //finishes the edits
        saveToLine.close();
    }
    
    public String readFromFile(String fileName) throws FileNotFoundException, IOException{
        //create method for reading from file
        FileReader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        String information = "";
        while((line = bufferedReader.readLine()) != null) {
            information += line;
        }   
        return information;
    }
    
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
        Transaction[] transactionsArray = new Transaction[orders.size() + payments.size()];
        transactionsArray = Stream.concat(Arrays.stream(getOrders()), Arrays.stream(getPayments()))
                      .toArray(Transaction[]::new);
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
    public void persistTo(String filename) throws Exception {
        //Still working on getting this to work -_- it won't accept "C:\desktop\" due to the backwards slashes "\"
        String file_path = "desktop";
        makeFile(file_path, filename+".txt");
        int index = 0;
        while (index != getCustomersByNumber().length){
            saveToFile("addc "+getCustomersByNumber()[index].getFirstName()+" "+getCustomersByNumber()[index].getLastName(), file_path + filename, true);
            index += 1;
        }
        /*
        while (index != getTransactions().length){
            saveToFile(getTransactions()[index], file_path + filename, true);
            index += 1;
        }
        saveToFile(getPricesTable(), file_path + filename, true);
*/
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
    
    public Order[] getOrderForCustomer(int customerNumber) {
        ArrayList<Order> results = new ArrayList<>(5);
        for (Order order: orders) {
            if (order.customer.getCustomerNumber == customerNumber) {
                results.add(order);
            }
        }
        Order[] resultsArray = new Order[results.size()];
        return results.toArray(resultsArray);
    }
    
    public Payment[] getPaymentForCustomer(int customerNumber) {
        ArrayList<Payment> results = new ArrayList<>(5);
        for (Payment payment: payments) {
            if (payment.customer.getCustomerNumber == customerNumber) {
                results.add(payment);
            }
        }
        Payment[] resultsArray = new Payment[results.size()];
        return results.toArray(resultsArray);
    }

    
    public String getStatementReport() {
        throw new UnsupportedOperationException();
    }
    
    public String getAccountsReceiveableReport(){
        throw new UnsupportedOperationException();
    } 
}
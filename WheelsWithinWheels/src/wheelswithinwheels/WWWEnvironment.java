/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wheelswithinwheels;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import wheelswithinwheels.wwwui.PrintReceivablesCommand;
import wheelswithinwheels.wwwui.PrintStatementsCommand;

/**
 *
 * @author asa
 */
public class WWWEnvironment {
    
    public DateTimeFormatter dateFormatter;
    private final RepairPriceTable repairPriceTable;
    
    private List<Customer> customers;
    private List<Order> orders;
    private List<Payment> payments;

    private int nextCustomerNumber;
    private int nextOrderNumber;
    
    public final String baseSavePath = "saves\\";

    public WWWEnvironment(){
        this.nextCustomerNumber = 0;
        this.nextOrderNumber = 0;
        this.payments = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customers = new ArrayList<>();
        dateFormatter = DateTimeFormatter.ofPattern("MMddyyyy");
        repairPriceTable = new RepairPriceTable();

    }
    
    /*
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
    */
    public Order[] getOrders() { 
        return orders.stream()
            .filter(Objects::nonNull)
            .toArray(Order[]::new);
    }

    public Customer[] getCustomers(){
        return customers.stream()
                .filter(Objects::nonNull)
                .toArray(Customer[]::new);
    }
    
    public Customer[] getCustomersByName() { 
        Customer[] customersByName = getCustomers();
        Arrays.sort(customersByName, (Customer a, Customer b)->
                        (a.getFullName()).compareTo(b.getFullName()));
        return customersByName;
    }
    
    public Customer[] getCustomersByNumber() {
        return getCustomers();
    }
    
    public int getNumberCustomers() {
        return customers.size();
    } 
    
    public Payment[] getPayments() { 
        return payments.stream().toArray(Payment[]::new);
    }
    
    public Transaction[] getTransactions(){
        return Stream.concat(Arrays.stream(getOrders()), Arrays.stream(getPayments()))
                      .toArray(Transaction[]::new);
        
    }
    
    public Transaction[] getTransactionsByDate() { 
        Transaction[] transactions = getTransactions();
        Arrays.sort(transactions, (Transaction a, Transaction b) -> 
                a.getDate().compareTo(b.getDate())
        );
        return transactions;
    }
    
    public RepairPriceTable getRepairPriceTable() { return repairPriceTable; }
  
    public void reset() {
        orders = new ArrayList<>(orders.size());
        customers = new ArrayList<>(customers.size());
        payments = new ArrayList<>(payments.size());
    }
    public void persistTo(String filename) throws IOException{
        try (FileWriter writer = new FileWriter(baseSavePath+filename+".txt");){
            Stream.of(
                    Stream.of(getRepairPriceTable()),
                    Arrays.stream(getCustomers()),
                    Arrays.stream(getTransactions())
                )
                .flatMap(s->s)
                .forEach((BikeShopSaveable saveable) -> {
                    try {
                        writer.write(saveable.getSaveText(this)+"\r\n");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            );
        }
    }
    
    public Order addOrder(Customer customer, String brand, String level, LocalDate orderDate, String comment) {
        int number = nextOrderNumber;
        nextOrderNumber++;
        while (orders.size()<=number) 
            orders.add(null);
        Order order = new Order(customer, brand,level,orderDate,comment,number);
        orders.set(number,order);
        return order;
    }
    
    
    public Payment addPayment(Customer customer, LocalDate date, int amount) {
        Payment payment = new Payment(customer,date,amount);
        payments.add(payment);
        return payment;
    }
    
    public void addRepairPrice(String brand,String level,int price,int returnTime) {
        repairPriceTable.addPrice(brand,level,new RepairPriceEntry(price, returnTime));
    }
    
    public Customer addCustomer(String firstName, String lastName) {
        int number = nextCustomerNumber;
        nextCustomerNumber++;
        while (customers.size()<=number) 
            customers.add(null);
        Customer customer = new Customer(firstName,lastName,number);
        customers.set(number,customer);
        return customer;
    }

    public Customer getCustomer(int customerNumber) {
        Customer customer =  customers.get(customerNumber);
        assert(customer == null || customer.getCustomerNumber()==customerNumber);
        return customer;
    }
    
    public Order getOrder(int orderNumber) {
        Order order =  orders.get(orderNumber);
        assert(order == null || order.getOrderNumber()==orderNumber);
        return order;
    }
    
    public Order[] getOrdersForCustomer(int customerNumber) {
        return orders.stream()
                .filter((Order order)->order.getCustomer().getCustomerNumber()==customerNumber)
                .toArray(Order[]::new);
    }
    
    public Payment[] getPaymentsForCustomer(int customerNumber) {
        return payments.stream()
                .filter((Payment payment)->payment.getCustomer().getCustomerNumber()==customerNumber)
                .toArray(Payment[]::new);
    }

    public void setNextCustomerNumber(int number) {
        nextCustomerNumber = number;
    }
    
    public void setNextOrderNumber(int number) {
        nextOrderNumber = number;
    }
    
    public String getStatementReport() {
        PrintStatementsCommand statementsCommand = new PrintStatementsCommand(this);
        return statementsCommand.getStatements();
    }
    
    public String getAccountsReceiveableReport(){
        PrintReceivablesCommand receivablesCommand = new PrintReceivablesCommand(this);
        return receivablesCommand.getReceivables();
    } 
}

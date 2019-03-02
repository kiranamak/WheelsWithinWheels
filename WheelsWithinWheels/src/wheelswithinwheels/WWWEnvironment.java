package wheelswithinwheels;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author asa
 */
public class WWWEnvironment {
    
    public DateTimeFormatter dateFormatter;
    private RepairPriceTable repairPriceTable;
    private List<Customer> customers;
    private List<Order> orders;
    private List<Payment> payments;
    
    private int lastCustomerNumber;
    private int lastOrderNumber;
    
    public WWWEnvironment(){
        this.lastCustomerNumber = 0;
        this.lastOrderNumber = 0;
        this.payments = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customers = new ArrayList<>();
        dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        repairPriceTable = new RepairPriceTable();
        
    }
    
    //The following methods should be reimplemented, there is a better way to do this.
    public void makeFile(String file_path, String fileName) throws FileNotFoundException{
        //creates the file
        File file = new File(file_path);
        //names the file
        file = new File(fileName);
    }
    public void saveToFile(Object text, String file_path, boolean addValue) throws IOException{
        FileWriter writing = new FileWriter(file_path, addValue);
        PrintWriter saveToLine = new PrintWriter(writing);
        //adds to file
        saveToLine.printf( "%s" + "%n" , text);
        //finishes the edits
        saveToLine.close();
    }
    
    public String readFromFile(String fileName) throws FileNotFoundException, IOException{
        //Redundant
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
        return (Order[]) orders.stream()
                .filter(Objects::nonNull)
                .toArray();
    }
    
    public Customer[] getCustomers(){
        return (Customer[]) customers.stream()
                .filter(Objects::nonNull)
                .toArray();
    }
    
    public Customer[] getCustomersByName() { 
        Customer[] customersByName = getCustomers();
        Comparator<Customer> comparator = (Customer a, Customer b)->
                        (a.getFullName()).compareTo(b.getFullName());
        Arrays.sort(customersByName, comparator);
        return customersByName;
    }
    
    public Customer[] getCustomersByNumber() {
        //TODO: Just use toArray() (no parameters)
        Customer[] customersByNumberArray = new Customer[customers.size()];
        return customers.toArray(customersByNumberArray);
    }
    
    public int getNumberCustomers() { 
        //TODO: Must check for sparseness
        return customers.size(); 
    }
    
    public Payment[] getPayments() { 
        //TODO: Just use toArray() (no parameters)
        Payment[] paymentsArray = new Payment[payments.size()];
        return payments.toArray(paymentsArray);
    }
    
    public Transaction[] getTransactions() { 
        //Should return orders as well
        return (Transaction[]) Stream.concat(orders.stream(), payments.stream())
                .collect(Collectors.toList())
                .toArray();
    }
    
    public RepairPriceTable getPricesTable() { return repairPriceTable; }
  
    public void reset() {
        orders = new ArrayList<>(orders.size());
        customers = new ArrayList<>(customers.size());
        payments = new ArrayList<>(payments.size());
    }
    
    public void persistTo(String filename) throws Exception {
        //Still working on getting this to work :/ it won't accept "C:\desktop\" due to the backwards slashes "\"
        String file_path = "desktop";
        makeFile(file_path, filename);
        int index = 0;
        while (index != getCustomersByNumber().length){
            saveToFile(getCustomersByNumber()[index], file_path + filename, true);
            index += 1;
        }
        while (index != getTransactions().length){
            saveToFile(getTransactions()[index], file_path + filename, true);
            index += 1;
        }
        saveToFile(getPricesTable(), file_path + filename, true);
    }
    
    public void addOrder(Customer customer, String brand, String level, String comment,LocalDate orderDate) {
        int number = ++lastOrderNumber;
        while (orders.size()<=number) 
            orders.add(null); 
        orders.set(number,new Order(customer, brand,level,comment,orderDate,number));
    }
    
    public void addPayment(Customer customer, LocalDate date, int amount) {
        payments.add(new Payment(customer,date,amount));
    }
    
    public void addRepairPrice(String brand,String level,int returnTime,int price) {
        repairPriceTable.addPrice(brand,level,new RepairPriceEntry(returnTime,price));
    }
    
    public void addCustomer(String firstName, String lastName) {
        System.out.println(firstName);
        System.out.println(lastName);
        int number = ++lastCustomerNumber;
        while (customers.size()<=number) 
            customers.add(null);
        customers.set(number,new Customer(firstName,lastName,number));
    }

    public Customer getCustomer(int customerNumber) {
        Customer customer =  customers.get(customerNumber);
        assert(customer.getCustomerNumber()==customerNumber);
        return customer;
    }
    
    public Order getOrder(int orderNumber) {
        return orders.get(orderNumber);
    }
    
    public String getStatementReport() {
        throw new UnsupportedOperationException();
    }
    
    public String getAccountsReceiveableReport(){
        throw new UnsupportedOperationException();
    } 
}

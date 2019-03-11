/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import commandui.tableview.HorizontalAlignDirection;
import static commandui.tableview.HorizontalAlignDirection.*;
import commandui.tableview.TableView;
import commandui.tableview.TableViewWidthOverflowException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author asa
 */
public class Customer implements BikeShopSaveable{

    private final String firstName;
    private final String lastName;
    private final int customerNumber;
    Customer(String firstName, String lastName, int customerNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }
    

  
    public Receivable receivableReport(Transaction[] transactions,WWWEnvironment environment) {
        int amountReceivable = 0;
        int amountPaid = 0;
        
        for(Transaction transaction: transactions) {
            if (transaction instanceof Order) {
                amountReceivable += ((Order) transaction).getRepairPrice(environment).getPrice();
            } else {
                amountPaid += ((Payment) transaction).getAmount();
            }
        }
        return new Receivable(amountReceivable, amountPaid);
    }
    
    private String[] orderStatement(Order t, WWWEnvironment environment, String date, String filler) {
        String description = t.shortReport();
        int amount = t.getRepairPrice(environment).getPrice();
        String[] toReturn = {date, description, Integer.toString(amount), filler};
        return toReturn;
    }
    
    private String[] paymentStatement(Payment t, String date, String filler) {
        String description = "Payment";
        int paid = ((Payment) t).getAmount();
        String[] toReturn = {date, description, filler, Integer.toString(paid)};
        return toReturn;
    }
    
    public String statementReport(Transaction[] transactions,WWWEnvironment environment) {
        TableView table = new TableView(4);
        String filler = "---";
        String[] columnHeader = {"Date", "Description", "Amount", "Paid"};
        try {
            table.addRow(columnHeader);
            for (Transaction t: transactions) {
                String date = t.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                if(t instanceof Order) {
                    String[] row = orderStatement((Order) t, environment, date, filler);
                    table.addRow(row);
                } else {
                    String[] row = paymentStatement((Payment) t, date, filler);
                    table.addRow(row);
                }
            }
        } catch (TableViewWidthOverflowException ex) {
                throw new RuntimeException("Somehow statments table has incorrect width.");
        }
        HorizontalAlignDirection[] format = {LEFT, LEFT, RIGHT, RIGHT};
        table.horizontalAlign(format);
        return table.toString();
    }
    
    public String shortReport() {
        StringBuilder report = new StringBuilder();
        report.append(firstName).append(" ").append(lastName);
        report.append("(").append(customerNumber).append(")");
        return report.toString();
    }
    
    
    @Override
    public String getSaveText(WWWEnvironment environment) {
        return "rncn " + getCustomerNumber() + "\n" 
                + "addc "+getFirstName()+" "+getLastName();
    }
    
    
}

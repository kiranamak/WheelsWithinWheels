/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import commandui.tableview.TableView;
import commandui.tableview.TableViewWidthOverflowException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author asa
 */
public class Customer {

    private final String firstName;
    private final String lastName;
    private final int customerNumber;
    private OrderStatus status;
    private LocalDate dateCompleted;
    private LocalDate dateReturned;
    
    private static int lastCustomerNumber = 0;
    
    
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = ++Customer.lastCustomerNumber;
        status = OrderStatus.PENDING;
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
    
    static void resetCustomerNumbers(){
        lastCustomerNumber = 0;
    }

    public void complete(LocalDate date) {
        status = OrderStatus.COMPLETED;
        this.dateCompleted = date;
    }
    
    public void returned(LocalDate date) {
        status = OrderStatus.RETURNED;
        this.dateReturned = date;
    } 
    
    private int[] receivable(Transaction[] transactions) {
        int amountReceivable = 0;
        int amountPaid = 0;
        
        for(Transaction transaction: transactions) {
            if (transaction instanceof Order) {
                amountReceivable += ((Order) transaction).getRepairPrice().getPrice();
            } else {
                amountPaid += ((Payment) transaction).getAmount();
            }
        }
        int[] report = {amountReceivable, amountPaid};
        return report;
    }
    
    public int[] receivableReport(Transaction[] transactions) {
        int[] data = receivable(transactions);
        int outstanding = data[0] - data[1];
        int[] report = {data[0], data[1], outstanding};
        return report;
    }
    
    public String statementReport(Transaction[] transactions) {
        TableView table = new TableView(4);
        String filler = "---";
        String[] columnHeader = {"Date", "Description", "Amount", "Paid"};
        try {
            for (Transaction t: transactions) {
                table.addRow(columnHeader);
                String date = t.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                String description = "";
                if(t instanceof Order) {
                    description = ((Order) t).shortReport();
                    int amount = ((Order) t).getRepairPrice().getPrice();
                    String[] row = {date, description, Integer.toString(amount), filler};
                    table.addRow(row);
                } else {
                    description = "Payment";
                    int paid = ((Payment) t).getAmount();
                    String[] row = {date, description, filler, Integer.toString(paid)};
                    table.addRow(row);
                }
            }
        } catch (TableViewWidthOverflowException ex) {
                throw new RuntimeException("Somehow help table has incorrect width.");
        }
        Boolean[] format = {false, false, true, true};
        return table.toStringFormatted(format);
    }
    
    public String shortReport() {
        //TODO: Should use StringBuilder.
        String report = "";
        report += firstName + " " + lastName;
        report += "(" + customerNumber + ")";
        return report;
    }
}

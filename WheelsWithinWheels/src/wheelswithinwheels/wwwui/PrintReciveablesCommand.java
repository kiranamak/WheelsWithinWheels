/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import commandui.tableview.HorizontalAlignDirection;
import static commandui.tableview.HorizontalAlignDirection.LEFT;
import static commandui.tableview.HorizontalAlignDirection.RIGHT;
import commandui.tableview.TableView;
import commandui.tableview.TableViewWidthOverflowException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wheelswithinwheels.Customer;
import wheelswithinwheels.Receivable;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author asa
 */
public class PrintReciveablesCommand extends ArgumentlessCommand<WWWEnvironment> {

    public PrintReciveablesCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public String getName() {
        return "printr";
    }

    @Override
    public void run() throws CommandUIArgumentException {
        TableView table = new TableView(5);
        int totalReceivable = 0;
        int totalPaid = 0;
        int totalOutstanding = 0;
        String[] columnHeader = {"#", "Name", "Receivable", "Paid", "Outstanding"};
        try {
            table.addRow(columnHeader);
            for(Customer c: environment.getCustomersByName()) {
                Receivable data = c.receivableReport(environment.getTransactionsByDate(),environment);
                String[] report = {Integer.toString(c.getCustomerNumber()), c.getFullName(), "$" + Integer.toString(data.getReceivable()), "$" + Integer.toString(data.getPaid()), "$" + Integer.toString(data.getOutstanding())};
                table.addRow(report);
                
                totalReceivable += data.getReceivable();
                totalPaid += data.getPaid();
                totalOutstanding += data.getOutstanding();
            }   
        } catch (TableViewWidthOverflowException ex) {
                throw new RuntimeException("Somehow receivables table has incorrect width.");
        }
        System.out.println("Total Amount Receivable: $" + totalReceivable);
        System.out.println("Total Amount Paid: $" + totalPaid);
        System.out.println("Total Amount Outstanding: $" + totalOutstanding);
        HorizontalAlignDirection[] format = {RIGHT,LEFT, RIGHT, RIGHT, RIGHT};
        table.horizontalAlign(format);
        System.out.println(table.toString());
    }
    
    
    
    @Override
    public String getHelpText(){
        return "Display a report of all accounts revievable";
    }
    
}
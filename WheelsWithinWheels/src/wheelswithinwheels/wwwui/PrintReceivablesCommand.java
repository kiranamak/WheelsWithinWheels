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
public class PrintReceivablesCommand extends ArgumentlessCommand<WWWEnvironment> {

    public PrintReceivablesCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public String getName() {
        return "printr";
    }

    @Override
    public void run() throws CommandUIArgumentException {
        System.out.println(getReceivables());
    }
    
    public String receivableTotals(int totalReceivable, int totalPaid, int totalOutstanding) { 
        String receivables = "";
            receivables = "Total Amount Receivable: $" + totalReceivable + "\n"
            + "Total Amount Paid: $" + totalPaid + "\n"
            + "Total Amount Outstanding: $" + totalOutstanding + "\n";
            
        return receivables;
    }
    
    public String getReceivables() {
        String receivables = "";
        
        TableView table = new TableView(5);
        String[] columnHeader = {"#", "Name", "Receivable", "Paid", "Outstanding"};
        
        try {
            table.addRow(columnHeader);
            for(Customer c: environment.getCustomersByName()) {
                Receivable data = c.receivableReport(environment.getTransactionsByDate(),environment);
                String[] report = {Integer.toString(c.getCustomerNumber()), c.getFullName(), "$" + Integer.toString(data.getReceivable()), "$" + Integer.toString(data.getPaid()), "$" + Integer.toString(data.getOutstanding())};
                table.addRow(report);
            }   
        } catch (TableViewWidthOverflowException ex) {
                throw new RuntimeException("Somehow receivables table has incorrect width.");
        }
        
        PrintTotalReceivablesCommand ptr = new PrintTotalReceivablesCommand(environment);
        receivables += ptr.getReceivablesTotals();
        
        HorizontalAlignDirection[] format = {RIGHT,LEFT, RIGHT, RIGHT, RIGHT};
        table.horizontalAlign(format);
        receivables += table.toString() + "\n";
        
        return receivables;
    }
    
    @Override
    public String getHelpText(){
        return "Display a report of all accounts revievable";
    }
    
}
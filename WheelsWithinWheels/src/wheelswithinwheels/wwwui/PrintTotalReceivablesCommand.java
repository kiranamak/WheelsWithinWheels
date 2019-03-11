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
import wheelswithinwheels.Customer;
import wheelswithinwheels.Receivable;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author kmak
 */
public class PrintTotalReceivablesCommand extends ArgumentlessCommand<WWWEnvironment> {
 public PrintTotalReceivablesCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public String getName() {
        return "printr";
    }

    @Override
    public void run() throws CommandUIArgumentException {
        System.out.println(getReceivablesTotals());
    }
    
    private String receivableTotals(int totalReceivable, int totalPaid, int totalOutstanding) { 
        String receivables = "";
            receivables = "Total Amount Receivable: $" + totalReceivable + "\n"
            + "Total Amount Paid: $" + totalPaid + "\n"
            + "Total Amount Outstanding: $" + totalOutstanding + "\n";
            
        return receivables;
    }

    
    public String getReceivablesTotals() {
        String receivables = "";
        
        int totalReceivable = 0;
        int totalPaid = 0;
        int totalOutstanding = 0;
 
        for(Customer c: environment.getCustomersByName()) {
            Receivable data = c.receivableReport(environment.getTransactionsByDate(),environment);

            totalReceivable += data.getReceivable();
            totalPaid += data.getPaid();
            totalOutstanding += data.getOutstanding();
        }   
       
        receivables += receivableTotals(totalReceivable, totalPaid, totalOutstanding);
        return receivables;
    }
    
    
    @Override
    public String getHelpText(){
        return "Display a report of all accounts revievable";
    }
    
}
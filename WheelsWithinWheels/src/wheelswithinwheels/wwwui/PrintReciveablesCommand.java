/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import commandui.tableview.TableView;
import commandui.tableview.TableViewWidthOverflowException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wheelswithinwheels.Customer;
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
                int[] data = c.receivableReport(environment.getTransactions());
                String[] report = {Integer.toString(c.getCustomerNumber()), c.getFullName(), "$" + Integer.toString(data[0]), "$" + Integer.toString(data[1]), "$" + Integer.toString(data[2])};
                table.addRow(report);
                totalReceivable += data[0];
                totalPaid += data[1];
                totalOutstanding += data[2];
            }   
        } catch (TableViewWidthOverflowException ex) {
                throw new RuntimeException("Somehow help table has incorrect width.");
        }
        Boolean[] format = {true, false, true, true, true};
        System.out.println("Total Amount Receivable: $" + totalReceivable);
        System.out.println("Total Amount Paid: $" + totalPaid);
        System.out.println("Total Amount Outstanding: $" + totalOutstanding);
        System.out.println(table.toStringFormatted(format));
    }
    
}
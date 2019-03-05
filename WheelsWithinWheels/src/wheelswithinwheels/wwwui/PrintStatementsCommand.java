/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;
import wheelswithinwheels.WWWEnvironment;
import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import commandui.tableview.HorizontalAlignDirection;
import static commandui.tableview.HorizontalAlignDirection.LEFT;
import static commandui.tableview.HorizontalAlignDirection.RIGHT;
import commandui.tableview.TableView;
import commandui.tableview.TableViewWidthOverflowException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import wheelswithinwheels.Customer;
import wheelswithinwheels.Receivable;
import wheelswithinwheels.Transaction;

/**
 *
 * @author asa
 */
public class PrintStatementsCommand extends ArgumentlessCommand<WWWEnvironment> {
    public PrintStatementsCommand(WWWEnvironment environment) {
        super(environment);
    }
    
    @Override
    public String getName() {
        return "prints";
    }
    
    private void statementHeader(Customer c, Transaction[] transactions, String dashedLine) {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("Wheels Within Wheels Bike Shop\nTed Smith\n57 Lambert Rd\n111-111-1111");
        System.out.println(dashedLine);
        System.out.println("Statement of Account - " + currentDate + "\n");
        System.out.println(c.getFullName() + "\n");
        System.out.println(c.statementReport(transactions, environment) + "\n");
        System.out.println(dashedLine);
    }
    
    private void totalsTable(Receivable receivable){
        TableView table = new TableView(4);
        try {
            String[] totalRow = {"Total: ", "", "$", Integer.toString(receivable.getReceivable())};
            String[] totalPaidRow = {"Total Paid: ", "", "$", Integer.toString(receivable.getPaid())};
            String[] totalDueRow = {"Total Due: ", "", "$", Integer.toString(receivable.getOutstanding())};
            HorizontalAlignDirection[] format = {LEFT, LEFT, LEFT, RIGHT};
            table.horizontalAlign(format);
            
            table.addRow(totalRow);
            table.addRow(totalPaidRow);
            table.addRow(totalDueRow);
            System.out.println(table.toString() + "\n");
        } catch (TableViewWidthOverflowException e) {
            throw new IllegalStateException("Somehow statement totals table has incorrect width.");
        }
    }
    
    @Override
    public void run() throws CommandUIArgumentException {
        int dashedLineLength = 100;
        String dashedLine = new String(new char[dashedLineLength]).replace('\0', '-');
        
        for (Customer c: environment.getCustomersByName()) {
            Transaction[] transactions = environment.getTransactionsByDate();
            statementHeader(c, transactions, dashedLine);
            
            
            Receivable totals = c.receivableReport(transactions, environment);
            totalsTable(totals);
            
            if (totals.getOutstanding() != 0) {
                System.out.println(dashedLine);
                System.out.println("Please make payments within the week");
            }
            System.out.println(dashedLine);
            System.out.println("THANK YOU FOR CHOOSING WHEELS WITHIN WHEELS BIKE SHOP!");
        }
    }
    
    @Override
    public String getHelpText(){
        return "Display statements for each customer.";
    }

}


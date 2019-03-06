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
    
    private String statementHeader(Customer c, Transaction[] transactions, String dashedLine) {
        String header = "";
        
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        header += "Wheels Within Wheels Bike Shop\nTed Smith\n57 Lambert Rd\n111-111-1111\n\n"
            + dashedLine + "\n"
            + "Statement of Account - " + currentDate + "\n\n"
            + c.getFullName() + "\n\n"
            + c.statementReport(transactions, environment) + "\n\n"
            + dashedLine + "\n";
        
        return header;
    }
    
    private String totalsTable(Receivable receivable){
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
            return table.toString() + "\n\n";
        } catch (TableViewWidthOverflowException e) {
            throw new IllegalStateException("Somehow statement totals table has incorrect width.");
        }
    }
    
    @Override
    public void run() throws CommandUIArgumentException {
        System.out.println(getStatements());
    }
    
    public String getStatements() {
        String statements = "";
        
        int dashedLineLength = 100;
        String dashedLine = new String(new char[dashedLineLength]).replace('\0', '-');
        
        for (Customer c: environment.getCustomersByName()) {
            Transaction[] transactions = environment.getTransactionsByDate();
            statements += statementHeader(c, transactions, dashedLine);
            
            
            Receivable totals = c.receivableReport(transactions, environment);
            statements += totalsTable(totals);
            
            if (totals.getOutstanding() != 0) {
                statements += dashedLine + "\n"
                    + "Please make payments within the week";
            }
            statements += dashedLine + "\n"
                + "THANK YOU FOR CHOOSING WHEELS WITHIN WHEELS BIKE SHOP!\n"
                + dashedLine + "\n";
        }
        return statements;
    }
    
    @Override
    public String getHelpText(){
        return "Display statements for each customer.";
    }

}


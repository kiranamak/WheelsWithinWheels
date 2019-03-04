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
    
    @Override
    public void run() throws CommandUIArgumentException {
        for (Customer c: environment.getCustomersByName()) {
            Transaction[] transactions = environment.getTransactionsByDate();
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            System.out.println("Statement of Account - " + currentDate + "\n");
            System.out.println(c.getFullName() + "\n");
            System.out.println(c.statementReport(transactions, environment) + "\n");
            
            int[] totals = c.receivableReport(transactions,environment);
            TableView table = new TableView(4);
            String[] totalRow = {"Total: ", "", "$", Integer.toString(totals[0])};
            String[] totalPaidRow = {"Total Paid: ", "", "$", Integer.toString(totals[1])};
            String[] totalDueRow = {"Total Due: ", "", "$", Integer.toString(totals[2])};
            HorizontalAlignDirection[] format = {LEFT, LEFT, LEFT, RIGHT};
            table.horizontalAlign(format);
            System.out.println(table.toString() + "\n");
            
            if (totals[2] != 0) {
                System.out.println("Please make payments within the week");
            }
            
            System.out.println("THANK YOU FOR CHOOSING WHEELS WITHIN WHEELS BIKE SHOP!");
        }
    }
    
    @Override
    public String getHelpText(){
        return "Display statements for each customer.";
    }

}


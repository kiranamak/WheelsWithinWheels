/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;
import wheelswithinwheels.WWWEnvironment;
import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import wheelswithinwheels.Transaction;

/**
 *
 * @author asa
 */
public class PrintTransactionsCommand extends ArgumentlessCommand<WWWEnvironment> {
    public PrintTransactionsCommand(WWWEnvironment environment) {
        super(environment);
    }
    
    @Override
    public String getName() {
        return "printt";
    }
    
    @Override
    public void run() throws CommandUIArgumentException {
        Transaction[] transactions = environment.getTransactions();
        for (Transaction transaction: transactions) {
            System.out.println(transaction.getReport());
        }
    }
}

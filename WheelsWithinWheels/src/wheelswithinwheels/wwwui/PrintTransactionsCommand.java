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
    boolean orders;
    boolean payments;
    
    public PrintTransactionsCommand(WWWEnvironment environment,boolean orders,boolean payments) {
        super(environment);
        this.orders = orders;
        this.payments = payments;
        if (!(payments || orders)) throw new IllegalArgumentException("Cannot print nothing");
    }
    
    @Override
    public String getName() {
        if (orders && payments) return "printt";
        if (orders) return "printo";
        if (payments) return "printp";
        throw new IllegalStateException();
    }
    
    @Override
    public void run() throws CommandUIArgumentException {
        Transaction[] transactions = environment.getTransactionsByDate();
        for (Transaction transaction: transactions) {
            System.out.println(transaction.getReport(environment));
        }
    }
    
    
    @Override
    public String getHelpText(){
        String base = "Displays a list of all ";
        if (orders && payments) return base+"orders and payments";
        if (orders) return base+"orders";
        if (payments) return base+"payments";
        throw new IllegalStateException("Cannot print nothing");
    }

}
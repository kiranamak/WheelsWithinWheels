/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import wheelswithinwheels.Customer;
import wheelswithinwheels.WWWEnvironment;
import static wheelswithinwheels.wwwui.PrintCustomersCommand.CustomerSort.NAME;
import static wheelswithinwheels.wwwui.PrintCustomersCommand.CustomerSort.NUMBER;

/**
 *
 * @author asa
 */
public class PrintCustomersCommand extends ArgumentlessCommand<WWWEnvironment>{
    private final CustomerSort sort;
    
    public enum CustomerSort {NAME,NUMBER;}
    
    public PrintCustomersCommand(WWWEnvironment environment,CustomerSort sort) {
        super(environment);
        this.sort = sort;
    }

    @Override
    public String getName() {
        switch (sort){
            case NAME:return "printcname";
            case NUMBER:return "printcnum";
            default:throw new IllegalStateException("Extraneous customer sort");
        }
    }

    @Override
    public void run() throws CommandUIArgumentException {
        Customer[] customers = new Customer[environment.getNumberCustomers()];
        switch (sort){
            case NAME:
                customers = environment.getCustomersByName();
                break;
            case NUMBER:
                customers = environment.getCustomersByNumber();         
        }
        for (Customer customer:customers){
            System.out.println(customer.shortReport());
        }
        
    }
    
    
    @Override
    public String getHelpText(){
        return "Display a list of customers, in order by " 
                + (sort==NAME ? "name" : sort==NUMBER ? "number" : "");
        //TODO: Should check for additional enum cases without eleinating brevity/clarity
    }
}

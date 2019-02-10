/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import java.util.Arrays;
import java.util.Comparator;
import wheelswithinwheels.Customer;
import wheelswithinwheels.WWWEnviroment;

/**
 *
 * @author asa
 */
public class PrintCustomersCommand extends ArgumentlessCommand<WWWEnviroment>{
    private final CustomerSort sort;
    
    public enum CustomerSort {NAME,NUMBER;}
    
    public PrintCustomersCommand(WWWEnviroment enviroment,CustomerSort sort) {
        super(enviroment);
        this.sort = sort;
    }

    @Override
    public String getName() {
        switch (sort){
            case NAME:return "printcname";
            case NUMBER:return "printcnum";
            default:throw new RuntimeException("Extraneous customer sort");
        }
    }

    @Override
    public void run() throws CommandUIArgumentException {
        Customer[] customers = enviroment.getCustomers();
        Comparator<Customer> comparator = null;
        switch (sort){
            case NAME:
                comparator = (Customer a,Customer b)->
                        (a.getLastName()+a.getFirstName())
                                .compareTo(b.getLastName()+b.getFirstName());
            case NUMBER:
                comparator = (Customer a,Customer b)->
                        (a.getCustomerNumber()-b.getCustomerNumber());         
        }
        Arrays.sort(customers,comparator);
        for (Customer customer:customers){
            System.out.println(customer);
        }
        
    }
    
}

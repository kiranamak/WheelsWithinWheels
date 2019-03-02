/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.CommandUIArgumentException;
import commandui.KnownLengthArgumentListCommand;
import java.time.LocalDate;
import wheelswithinwheels.Customer;
import wheelswithinwheels.Payment;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author asa
 */
public class AddPaymentCommand extends KnownLengthArgumentListCommand<WWWEnvironment>{

    public AddPaymentCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public int argumentCount() {
        return 3;
    }

    @Override
    public String getName() {
        return "addp";
    }
    
    @Override
    public void run(String[] args)throws CommandUIArgumentException{
        super.run(args);
        int customerNumber = parseIntArgument(0,args);
        Customer customer = environment.getCustomer(customerNumber);
        LocalDate date = LocalDate.parse(args[1],environment.dateFormatter);
        int amount = parseIntArgument(2,args);
        environment.addPayment(customer,date,amount);
    }
    
}

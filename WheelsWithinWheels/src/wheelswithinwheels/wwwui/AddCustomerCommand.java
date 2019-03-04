/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import wheelswithinwheels.WWWEnvironment;
import commandui.CommandUIArgumentException;
import commandui.KnownLengthArgumentListCommand;
import wheelswithinwheels.Customer;

/**
 *
 * @author asa
 */
public class AddCustomerCommand extends KnownLengthArgumentListCommand<WWWEnvironment>{

    public AddCustomerCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public int argumentCount() {
        return 2;
    }

    @Override
    public String getName() {
        return "addc";
    }
    
    @Override
    public void run(String[] args) throws CommandUIArgumentException{
        super.run(args);
        Customer addedCustomer = environment.addCustomer(args[0], args[1]);
        System.out.println("Added Customer #"+Integer.toString(addedCustomer.getCustomerNumber()));
    }
   
    @Override
    public String getHelpArguments() {
        return "<First Name> <Last Name>";
    }
    
    @Override
    public String getHelpText(){
        return "Create a new customer";
    }
}

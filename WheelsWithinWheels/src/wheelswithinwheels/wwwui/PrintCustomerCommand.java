/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.ArgumentedCommand;
import commandui.CommandUIArgumentException;
import wheelswithinwheels.Customer;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author kmak
 */
public class PrintCustomerCommand extends ArgumentedCommand<WWWEnvironment> {

    public PrintCustomerCommand(WWWEnvironment enviroment) {
        super(enviroment);
    }

    @Override
    public String getName() {
        return "printcust";
    }
    
    public String getHelpText() {
        return "Get information on a customer by name or number";
    }

    public String getHelpArguments() {
        return "<First Name> <Last Name> or <Customer Number>";
    }
    
    @Override
    public void run(String[] args) throws CommandUIArgumentException {
        Customer c = null;
        if (args.length == 0) {
            throw new CommandUIArgumentException("Expected 1 or 2 arguments, recieved 0 instead");
        }
        if (args.length == 1) {
            int customerNumber = parseIntArgument(0, args);
            c = environment.getCustomer(customerNumber);
        }
        if (args.length == 2) {
            c = environment.getCustomer(args[0], args[1]);
        }
        System.out.println(c.shortReport());
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import wheelswithinwheels.WWWEnviroment;
import commandui.CommandUIArgumentException;
import commandui.KnownLengthArgumentListCommand;
import wheelswithinwheels.Customer;

/**
 *
 * @author asa
 */
public class AddCustomerCommand extends KnownLengthArgumentListCommand<WWWEnviroment>{

    public AddCustomerCommand(WWWEnviroment enviroment) {
        super(enviroment);
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
        enviroment.addCustomer(new Customer(args[0],args[1]));
    }
}

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
import wheelswithinwheels.WWWEnviroment;

/**
 *
 * @author asa
 */
public class AddPaymentCommand extends KnownLengthArgumentListCommand<WWWEnviroment>{

    public AddPaymentCommand(WWWEnviroment enviroment) {
        super(enviroment);
    }

    @Override
    public int argumentCount() {
        return 3;
    }

    @Override
    public String getName() {
        return "addc";
    }
    
    @Override
    public void run(String[] args)throws CommandUIArgumentException{
        super.run(args);
        int customerNumber = parseIntArgument(0,args);
        Customer customer = enviroment.getCustomer(customerNumber);
        LocalDate date = LocalDate.parse(args[1],enviroment.dateFormatter);
        int amount = parseIntArgument(2,args);
        enviroment.addPayment(new Payment(customer,date,amount));
    }
    
}

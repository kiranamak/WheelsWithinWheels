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
import wheelswithinwheels.Order;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author asa
 */
public class CompleteCommand extends KnownLengthArgumentListCommand<WWWEnvironment>{

    public CompleteCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public int argumentCount() {
        return 2;
    }

    @Override
    public String getName() {
        return "comp";
    }
    
    @Override
    public void run(String[] args) throws CommandUIArgumentException{
        super.run(args);
        Order order = environment.getOrder(parseIntArgument(0,args));
        LocalDate date = LocalDate.parse(args[1],environment.dateFormatter);
        order.complete(date);
    }
    
}

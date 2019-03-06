/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.CommandUIArgumentException;
import commandui.KnownLengthArgumentListCommand;
import wheelswithinwheels.Order;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author asa
 */
public class ReturnOrderCommand extends KnownLengthArgumentListCommand<WWWEnvironment>{

    public ReturnOrderCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public int argumentCount() {
        return 2;
    }

    @Override
    public String getName() {
        return "returno";
    }
    
    @Override
    public void run(String[] args) throws CommandUIArgumentException{
        //TODO: Check command syntax
        super.run(args);
        int orderNumber = parseIntArgument(0,args);
        Order order = environment.getOrder(orderNumber);
        order.returned(parseDateArgument(1,args, environment.dateFormatter));
    }
    
    @Override
    public String getHelpArguments() {
        return "<Order Number> <Date of Return>";
    }
    
    @Override
    public String getHelpText(){
        return "Marks an order as returned to the customer";
    }
    
}

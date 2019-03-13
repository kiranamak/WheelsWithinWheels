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
 * @author kmak
 */
public class PrintOrderByNumberCommand extends KnownLengthArgumentListCommand<WWWEnvironment>{
    public PrintOrderByNumberCommand(WWWEnvironment enviroment) {
        super(enviroment);
    }

    @Override
    public String getName() {
        return "printord";
    }
    
    @Override
    public String getHelpText() {
        return "Get information on an order number";
    }

    @Override
    public String getHelpArguments() {
        return "<Order Number>";
    }
    
    public int argumentCount() {
        return 1;
    }
    
    @Override
    public void run(String[] args) throws CommandUIArgumentException {
        int orderNumber = parseIntArgument(0, args);
        Order o = environment.getOrder(orderNumber);
        System.out.println(o.getReport(environment));
        
    }
}

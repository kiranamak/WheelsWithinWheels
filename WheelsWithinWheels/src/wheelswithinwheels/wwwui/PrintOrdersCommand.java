/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;
import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import wheelswithinwheels.WWWEnvironment;
import wheelswithinwheels.Order;

/**
 *
 * @author kmak
 */
public class PrintOrdersCommand extends ArgumentlessCommand<WWWEnvironment> {
    public PrintOrdersCommand(WWWEnvironment environment) {
        super(environment);
    }
    
    @Override
    public String getName() {
        return "printo";
    }
    
    @Override
    public void run() throws CommandUIArgumentException {
        Order[] orders = environment.getOrders();
        for (Order order: orders) {
            System.out.println(order.getReport(environment));
        }
    }
}

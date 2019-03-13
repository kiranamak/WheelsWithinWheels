/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.CommandUIArgumentException;
import commandui.KnownLengthArgumentListCommand;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import wheelswithinwheels.Order;
import static wheelswithinwheels.OrderStatus.PENDING;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author kmak
 */
public class PrintOrdersDueBeforeCommand extends KnownLengthArgumentListCommand<WWWEnvironment>{
    
    public PrintOrdersDueBeforeCommand(WWWEnvironment environment) {
        super(environment);
    }
    
    @Override
    public String getName() {
        return "printincompodue";
    }
    
    @Override
    public int argumentCount() {
        return 1;
    }
    
    @Override
    public void run(String[] args) throws CommandUIArgumentException {
        LocalDate date;
        ArrayList<Order> ordersDue = new ArrayList<>(5);
        try {
            date = LocalDate.parse(args[0], environment.dateFormatter);
        } catch (Exception e) {
            if (args[0].equals("today")) {
                date = LocalDate.now();
            } else {
                throw new CommandUIArgumentException("Expected a date or 'today'");
            }
        }
        Order[] orders = environment.getOrders();
        for (Order order: orders) {
            if(order.getStatus() == PENDING && order.getPromisedDate(environment).isBefore(date)) {
                ordersDue.add(order);
            }
        }
        System.out.println(ordersDue.size() + " orders due before " + date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        for (Order order: ordersDue) {
            System.out.println(order.getReport(environment));
        }
    }
    
    
    @Override
    public String getHelpText(){
        return "prints a list of incomplete orders due before a certain date";
    }
    
    @Override
    public String getHelpArguments() {
        return "<Date> or 'today'";
    }
}

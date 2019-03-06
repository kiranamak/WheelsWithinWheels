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
import java.time.LocalDate;
import java.util.Arrays;
import wheelswithinwheels.RepairPriceTable;
/**
 *
 * @author kmak
 */
public class AddOrderCommand extends ArgumentedCommand<WWWEnvironment> {
    public AddOrderCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public String getName() {
        return "addo";
    }
    
    public int argumentCountMinimum() {
        return 5;
    }
    
    private void checkRepairPrice(String brand, String level) throws CommandUIArgumentException {
        if (environment.getRepairPriceTable().getPrice(level, brand) == null) {
            throw new CommandUIArgumentException("Invalid repair brand and level combination");
        }
    }

    @Override
    public void run(String[] args) throws CommandUIArgumentException{
        if (args.length != argumentCountMinimum()) {
            throw new CommandUIArgumentException(argumentCountMinimum(), args.length);
        }
        
        int customerNumber = parseIntArgument(0, args);
        Customer customer = environment.getCustomer(customerNumber);
        LocalDate date = parseDateArgument(1, args, environment.dateFormatter);
        checkRepairPrice(args[2], args[3]);
        String[] commentArray = Arrays.copyOfRange(args, 4, args.length);
        String comment = Arrays.toString(commentArray);
        
        environment.addOrder(customer, args[2], args[3], date, comment);
    }
    
    @Override
    public String getHelpArguments() {
        return "<Customer Number> <Order Date> <Bike Brand> <Tuneup Level> <Comment>";
    }
    
    @Override
    public String getHelpText(){
        return "Create a new order";
    }
}

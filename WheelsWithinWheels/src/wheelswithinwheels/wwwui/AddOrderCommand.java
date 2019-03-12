/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;
import commandui.Command;
import commandui.CommandUIArgumentException;
import wheelswithinwheels.Customer;
import wheelswithinwheels.WWWEnvironment;
import java.time.LocalDate;
import java.util.Arrays;
/**
 *
 * @author kmak
 */
public class AddOrderCommand extends Command<WWWEnvironment> {
    public AddOrderCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public String getName() {
        return "addo";
    }
    
    public int argumentCountMinimum() {
        return 4;
    }
    
    private void checkRepairPrice(String brand, String level) throws CommandUIArgumentException {
        if (environment.getRepairPriceTable().getPrice(level, brand) == null) {
            throw new CommandUIArgumentException("Invalid repair brand and level combination");
        }
    }

    @Override
    public void run(String args) throws CommandUIArgumentException{
        int spaces = (int) args.chars().filter(c -> c == (int)' ').count();
        if (spaces < argumentCountMinimum() - 1) {
            throw new CommandUIArgumentException(argumentCountMinimum(), spaces + 1);
        }
        String[] rawArgs = args.split("\\s+");
        String[] sepArgs = Arrays.stream(rawArgs, 0, 4)
                .filter((s) -> !s.equals(""))
                .toArray(String[]::new);
        
        int customerNumber = parseIntArgument(0, sepArgs);
        Customer customer = environment.getCustomer(customerNumber);
        LocalDate date = parseDateArgument(1, sepArgs, environment.dateFormatter);
        checkRepairPrice(sepArgs[2], sepArgs[3]);
        
        int nonCommentLength = 0;
        for (String s: sepArgs) {
            nonCommentLength += s.length();
        }
        String comment = args.substring(nonCommentLength + 1, args.length() - 1);
        
        environment.addOrder(customer, sepArgs[2], sepArgs[3], date, comment);
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

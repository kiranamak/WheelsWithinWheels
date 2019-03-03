/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;
import commandui.CommandUIArgumentException;
import commandui.KnownLengthArgumentListCommand;
import wheelswithinwheels.Customer;
import wheelswithinwheels.WWWEnvironment;
import java.time.LocalDate;
import wheelswithinwheels.RepairPriceTable;
/**
 *
 * @author kmak
 */
public class AddOrderCommand extends KnownLengthArgumentListCommand<WWWEnvironment> {
    public AddOrderCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public int argumentCount() {
        return 5;
    }

    @Override
    public String getName() {
        return "addo";
    }
    
    private void checkRepairPrice(String brand, String level) throws CommandUIArgumentException {
        if (environment.getRepairPriceTable().getPrice(level, brand) == null) {
            throw new CommandUIArgumentException("Invalid repair brand and level combination");
        }
    }

    @Override
    public void run(String[] args) throws CommandUIArgumentException{
        super.run(args);
        int customerNumber = parseIntArgument(0, args);
        Customer customer = environment.getCustomer(customerNumber);
        LocalDate date = parseDateArgument(1, args,environment.dateFormatter);
        checkRepairPrice(args[2], args[3]);
        environment.addOrder(customer, args[2], args[3], args[4], date);
    }
}

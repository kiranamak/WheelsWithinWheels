/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.CommandUIArgumentException;
import commandui.KnownLengthArgumentListCommand;
import wheelswithinwheels.RepairPriceEntry;
import wheelswithinwheels.WWWEnvironment;
import wheelswithinwheels.RepairPriceTable;

/**
 *
 * @author asa
 */
public class AddRepairPriceCommand extends KnownLengthArgumentListCommand<WWWEnvironment>{
    
    public AddRepairPriceCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public int argumentCount() {
        return 4;
    }

    @Override
    public String getName() {
        return "addrp";
    }
    
    private void checkRepairPrice(String brand, String level) throws CommandUIArgumentException {
        //TODO: This should allow overwriting repair prices
        if (environment.getRepairPriceTable().getPrice(brand, level) != null) {
            throw new CommandUIArgumentException("A repair price with brand " + brand + " and level " + level + " already exists.");
        }
    }
    
    @Override
    public void run(String[] args)throws CommandUIArgumentException{
        super.run(args);
        String brand = args[0];
        String level = args[1];
        int price = parseIntArgument(2,args);
        int days = parseIntArgument(3,args);
        checkRepairPrice(brand, level);
        environment.addRepairPrice(brand,level,price,days);
    }
    
    @Override
    public String getHelpArguments() {
        return "<Brand> <Level> <Price> <Days to Return>";
    }
    
    @Override
    public String getHelpText(){
        return "Create a new repair price table entry";
    }
}

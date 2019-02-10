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
import wheelswithinwheels.RepairPrice;
import wheelswithinwheels.TuneupLevel;
import wheelswithinwheels.WWWEnvironment;

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
    
    @Override
    public void run(String[] args)throws CommandUIArgumentException{
        super.run(args);
        String brand = args[0];
        TuneupLevel level = TuneupLevel.valueOf(args[1]);
        int price = parseIntArgument(2,args);
        int days = parseIntArgument(3,args);
        environment.addRepairPrice(new RepairPrice(brand,level,price,days));
    }
}

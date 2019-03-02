/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.Command;
import commandui.CommandUI;
import wheelswithinwheels.WWWEnvironment;
import wheelswithinwheels.wwwui.PrintCustomersCommand.CustomerSort;

/**
 *
 * @author asa
 */
public class WWWUI extends CommandUI{
    WWWEnvironment environment;
    
    
    public WWWUI(){
        super();
        this.environment = new WWWEnvironment();
        Command<?>[] commands = new Command<?>[]{
            new AddCustomerCommand(environment),
            new AddPaymentCommand(environment),
            new AddRepairPriceCommand(environment),
            new CompleteCommand(environment),
            new HelpCommand(this),
            new PrintCustomersCommand(environment,CustomerSort.NUMBER),
            new PrintCustomersCommand(environment,CustomerSort.NAME),
            new PrintReciveablesCommand(environment),
            new PrintRepairPricesCommand(environment),
            //new PrintStatementsCommand(environment),
            //new PrintTransactionsCommand(environment,true,true),
            //new PrintTransactionsCommand(environment,false,true),
            //new PrintTransactionsCommand(environment,true,false),
            //new ReadCommandsCommand(environment),
            //new RestoreBikeShopCommand(environment),
            //new ReturnOrderCommand(environment),
            //new SaveBikeShopCommand(environment)
        };
        updateCommands(commands);
        System.out.println("Initialized");
        System.out.println(getCommands());
    }
    
}

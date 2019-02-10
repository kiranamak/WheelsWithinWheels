/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.Command;
import commandui.CommandUI;
import wheelswithinwheels.WWWEnviroment;
import wheelswithinwheels.wwwui.PrintCustomersCommand.CustomerSort;

/**
 *
 * @author asa
 */
public class WWWUI extends CommandUI{
    WWWEnviroment enviroment;
    
    
    public WWWUI(){
        super();
        this.enviroment = new WWWEnviroment();
        Command<?>[] commands = new Command<?>[]{
            new AddCustomerCommand(enviroment),
            new AddPaymentCommand(enviroment),
            new AddRepairPriceCommand(enviroment),
            new CompleteCommand(enviroment),
            new HelpCommand(this),
            new PrintCustomersCommand(enviroment,CustomerSort.NUMBER),
            new PrintCustomersCommand(enviroment,CustomerSort.NAME),
            new PrintReciveablesCommand(enviroment),
            new PrintRepairPricesCommand(enviroment),
            //new PrintStatementsCommand(enviroment),
            //new PrintTransactionsCommand(enviroment,true,true),
            //new PrintTransactionsCommand(enviroment,false,true),
            //new PrintTransactionsCommand(enviroment,true,false),
            //new ReadCommandsCommand(enviroment),
            //new RestoreBikeShopCommand(enviroment),
            //new ReturnOrderCommand(enviroment),
            //new SaveBikeShopCommand(enviroment)
        };
        updateCommands(commands);
        System.out.println("Initialized");
        System.out.println(getCommands());
    }
    
}

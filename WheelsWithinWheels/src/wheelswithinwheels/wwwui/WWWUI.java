/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.HelpCommand;
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
        this(new WWWEnvironment());
    }
    
    public WWWUI(WWWEnvironment environment){
        super();
        this.environment = environment;
        Command<?>[] commands = new Command<?>[]{
            new AddCustomerCommand(this.environment),
            new AddOrderCommand(this.environment),
            new AddPaymentCommand(this.environment),
            new AddRepairPriceCommand(this.environment),
            new CompleteCommand(this.environment),
            new HelpCommand(this),
            new PrintCustomersCommand(this.environment,CustomerSort.NUMBER),
            new PrintCustomersCommand(this.environment,CustomerSort.NAME),
            new PrintReciveablesCommand(this.environment),
            new PrintRepairPricesCommand(this.environment),
            new PrintStatementsCommand(this.environment),
            new PrintTransactionsCommand(this.environment,true,true),
            new PrintTransactionsCommand(this.environment,false,true),
            new PrintTransactionsCommand(this.environment,true,false),
            new ReadCommandsCommand(this,true),
            new ReadCommandsCommand(this,false),
            new ReturnOrderCommand(this.environment),
            new TodayCommand(this.environment),
            new SaveBikeShopCommand(this.environment)
        };
        updateCommands(commands);
        System.out.println("Initialized");
        System.out.println(getCommands());
    }
    
}

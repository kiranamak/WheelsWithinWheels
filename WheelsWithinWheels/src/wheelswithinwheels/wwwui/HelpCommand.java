/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.ArgumentlessCommand;
import commandui.Command;
import commandui.CommandUIArgumentException;
import commandui.tableview.TableView;
import commandui.tableview.TableViewWidthOverflowException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wheelswithinwheels.WWWEnviroment;

/**
 *
 * @author asa
 */
public class HelpCommand extends ArgumentlessCommand<WWWUI>{

    public HelpCommand(WWWUI enviroment) {
        super(enviroment);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void run() throws CommandUIArgumentException {
        TableView helpMessages = new TableView(2);
        for (Command command: enviroment.getCommands()){
            try {
                helpMessages.addRow(new String[]{command.getName(),command.getHelpText()});
            } catch (TableViewWidthOverflowException ex) {
                throw new RuntimeException("Somehow help table has incorrect width.");
            }
        }
        System.out.println(helpMessages.toString());
    }
    
}

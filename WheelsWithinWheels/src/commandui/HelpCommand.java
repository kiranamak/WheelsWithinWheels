/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui;

import commandui.ArgumentlessCommand;
import commandui.Command;
import commandui.CommandUIArgumentException;
import commandui.tableview.TableView;
import commandui.tableview.TableViewWidthOverflowException;
import wheelswithinwheels.WWWEnvironment;
import wheelswithinwheels.wwwui.WWWUI;

/**
 *
 * @author asa
 */
public class HelpCommand extends ArgumentlessCommand<CommandUI>{

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
        for (Command command: environment.getCommands()){
            try {
                helpMessages.addRow(new String[]{
                    command.getName()+" "+command.getHelpArguments(),
                    command.getHelpText()
                });
            } catch (TableViewWidthOverflowException ex) {
                throw new IllegalStateException("Somehow help table has incorrect width.");
            }
        }
        System.out.println(helpMessages.toString());
    }
}

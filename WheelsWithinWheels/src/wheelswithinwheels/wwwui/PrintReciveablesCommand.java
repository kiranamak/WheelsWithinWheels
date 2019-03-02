/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author asa
 */
public class PrintReciveablesCommand extends ArgumentlessCommand<WWWEnvironment> {

    public PrintReciveablesCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public String getName() {
        return "printr";
    }

    @Override
    public void run() throws CommandUIArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

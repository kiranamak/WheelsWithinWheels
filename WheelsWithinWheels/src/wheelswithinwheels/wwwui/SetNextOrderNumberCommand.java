/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.CommandUIArgumentException;
import commandui.KnownLengthArgumentListCommand;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author kmak
 */
public class SetNextOrderNumberCommand extends KnownLengthArgumentListCommand<WWWEnvironment> {
    
    public SetNextOrderNumberCommand(WWWEnvironment environment) {
        super(environment);
    }
    
    @Override
    public int argumentCount() {
        return 1;
    }

    @Override
    public String getName() {
        return "rnon";
    }
    
    @Override
    public String getHelpText() {
        return "Sets next order number";
    }
    
    @Override
    public String getHelpArguments() {
        return "<#>";
    }
    
    @Override
    public boolean inHelpList() {
        return false;
    }
    
    @Override
    public void run(String args[]) throws CommandUIArgumentException {
        int number = parseIntArgument(0, args);
        environment.setNextOrderNumber(number);
    }    
}

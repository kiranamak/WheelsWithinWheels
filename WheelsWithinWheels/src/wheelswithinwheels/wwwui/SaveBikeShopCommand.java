/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.CommandUIArgumentException;
import commandui.KnownLengthArgumentListCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author asa
 */
public class SaveBikeShopCommand extends KnownLengthArgumentListCommand<WWWEnvironment>{

    public SaveBikeShopCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public int argumentCount() {
        return 1;
    }

    @Override
    public String getName() {
        return "savebs";
    }
    
    @Override
    public void run(String[] args) throws CommandUIArgumentException{
        super.run(args);
        try {
            environment.persistTo(args[0]);
        } catch (Exception ex) {
            System.out.println("Could not save the bikeshop to the specified file:\n");
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public String getHelpArguments() {
        return "<File Name>";
    }
    
    @Override
    public String getHelpText(){
        return "Saves the bike shop to a file";
    }
    
}

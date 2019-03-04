 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.KnownLengthArgumentListCommand;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author asa
 */
public class ReadCommandsCommand extends KnownLengthArgumentListCommand<WWWUI>{
    private final boolean reset;
    
    public ReadCommandsCommand(WWWUI enviroment,boolean reset){
        super(enviroment);
        this.reset = reset;
    }

    @Override
    public int argumentCount() {
        return 1;
    }

    @Override
    public String getName() {
        return reset ? "restorebs" : "readc";
    }
    
    @Override
    public void run(String[] args){
        if (reset) environment.environment.reset();
        WWWUI subUI = new WWWUI(environment.environment);
        String fileName = environment.environment.baseSavePath+args[0]+".txt";
        InputStream inStream;
        try {
            inStream = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File "+fileName+" Not Found");
            System.out.println(ex.getMessage());
            return;
        }
        subUI.useInputStream(inStream);
        subUI.run();
    }
    
    @Override
    public String getHelpArguments() {
        return "<File Name>";
    }
    
    @Override
    public String getHelpText(){
        if (reset) return "Restores the Bike Shop from a save file";
        return "Reads and executes the commands found in a file";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui;

import java.util.Arrays;


public abstract class SeparatedArgumentCommand<Enviroment> extends Command<Enviroment> {

    public SeparatedArgumentCommand(Enviroment enviroment) {
        super(enviroment);
    }

    public abstract String getName();

    public void run(String args) throws CommandUIArgumentException {
        String[] rawArgs = args.split("\\s+");
        String[] sepArgs =  Arrays.stream(rawArgs)
                .filter((s) -> !s.equals(""))
                .toArray(String[]::new);
        run(sepArgs);
    }
    
    public abstract void run(String[] args)throws CommandUIArgumentException;
    
}

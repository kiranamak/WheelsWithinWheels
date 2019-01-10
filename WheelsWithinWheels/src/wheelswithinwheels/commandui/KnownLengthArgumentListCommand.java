/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.commandui;


public abstract class KnownLengthArgumentListCommand<Enviroment> 
        extends Command<Enviroment> {
    
    
    public abstract int argumentCount();
    
    public KnownLengthArgumentListCommand(Enviroment enviroment) {
        super(enviroment);
    }

    @Override
    public void run(String[] args) throws CommandUIArgumentException {
        if (args.length != argumentCount())
            throw new CommandUIArgumentException(argumentCount(),args.length);
    }
}

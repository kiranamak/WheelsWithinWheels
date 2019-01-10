/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.commandui;


public abstract class ArgumentlessCommand<Enviroment> extends KnownLengthArgumentListCommand<Enviroment> {

    public ArgumentlessCommand(Enviroment enviroment) {
        super(enviroment);
    }

    @Override
    public int argumentCount() {
        return 0;
    }
    
    public abstract String getName();
    
}

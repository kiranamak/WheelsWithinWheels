/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.commandui;

/**
 *
 * @author asa
 */
public class QuitCommand extends Command<CommandUI>{
    
    
    public QuitCommand(CommandUI env){
        super(env);
    }
    
    @Override
    public String getName() {
        return "quit";
    }

    @Override
    public void run(String[] args) throws CommandUIArgumentException {
        enviroment.stop();
    }
    
}

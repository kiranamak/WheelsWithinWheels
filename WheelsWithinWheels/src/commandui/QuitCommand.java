/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui;

/**
 *
 * @author asa
 */
public class QuitCommand extends ArgumentlessCommand<CommandUI> {

    private String name;

    public QuitCommand(CommandUI env, String name) {
        super(env);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() throws CommandUIArgumentException {
        enviroment.stop();
    }

}

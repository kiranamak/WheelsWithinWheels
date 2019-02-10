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
public abstract class Command<Enviroment> {

    protected Enviroment enviroment;

    public Command(Enviroment enviroment) {
        this.enviroment = enviroment;
    }

    public abstract String getName();

    public abstract void run(String args) throws CommandUIArgumentException;

    protected int parseIntArgument(int argPos, String[] args) throws CommandUIArgumentException {
        try {
            return Integer.parseInt(args[argPos]);
        } catch (NumberFormatException e) {
            throw new CommandUIArgumentException(1, args[argPos], "an integer");
        }
    }

    public String getHelpText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

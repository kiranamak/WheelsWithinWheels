/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui;

public abstract class ArgumentlessCommand<Environment> extends Command<Environment> {

    public ArgumentlessCommand(Environment environment) {
        super(environment);
    }

    public abstract String getName();

    public boolean shouldFailSilently() {
        return false;
    }

    @Override
    public void run(String args) throws CommandUIArgumentException {
        if (!shouldFailSilently() && !args.equals("")) {
            throw new CommandUIArgumentException("Expected no arguments.");
        }
        run();
    }

    public abstract void run() throws CommandUIArgumentException;

}

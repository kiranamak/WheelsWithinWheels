/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui;

public abstract class KnownLengthArgumentListCommand<Environment>
        extends ArgumentedCommand<Environment> {

    public abstract int argumentCount();

    public KnownLengthArgumentListCommand(Environment environment) {
        super(environment);
    }

    @Override
    public void run(String[] args) throws CommandUIArgumentException {
        if (args.length != argumentCount()) {
            throw new CommandUIArgumentException(argumentCount(), args.length);
        }
    }
}

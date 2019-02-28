/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui.fieldwiseediting;

import commandui.Command;
import commandui.CommandUIArgumentException;

/**
 *
 * @author asa
 */
public class FieldwiseEditCommand extends Command<FieldwiseEditingEnvironment> {

    FeildwiseEditingField field;

    public FieldwiseEditCommand(FieldwiseEditingEnvironment environment, FeildwiseEditingField field) {
        super(environment);
        this.field = field;
    }

    @Override
    public String getName() {
        return field.command;
    }

    @Override
    public void run(String args) throws CommandUIArgumentException {
        field.setter.apply(args);
    }

}

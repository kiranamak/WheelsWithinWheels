/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui.fieldwiseediting;

import commandui.Command;
import commandui.CommandUI;
import commandui.tableview.TableView;
import commandui.tableview.TableViewWidthOverflowException;

/**
 *
 * @author asa
 */
public class FieldwiseEditingUI<Edited extends FieldwiseEditable> extends CommandUI {

    Edited object;

    public FieldwiseEditingUI(Edited object) {
        super(new Command[]{});
        this.object = object;
    }

    @Override
    public void setup() {
        super.setup();
        FieldwiseEditingEnvironment<Edited> environment = new FieldwiseEditingEnvironment<>(object);
        for (FeildwiseEditingField field : object.getFields()) {
            addCommand(new FieldwiseEditCommand(environment, field));
        }

        setPrompt(editVeiw());
    }

    protected String editVeiw() {
        TableView tv = new TableView(2);
        for (FeildwiseEditingField field : object.getFields()) {
            try {
                tv.addRow(new String[]{field.name, field.value});
            } catch (TableViewWidthOverflowException e) {
                throw new IllegalStateException();
            }
        }
        return tv.toString() + ">";
    }

    @Override
    protected void postCommand() {
        setPrompt(editVeiw());
    }

}

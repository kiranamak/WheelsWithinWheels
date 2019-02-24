/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;
import wheelswithinwheels.WWWEnvironment;
import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;

/**
 *
 * @author asa
 */
public class PrintStatementsCommand extends ArgumentlessCommand<WWWEnvironment> {
    public PrintStatementsCommand(WWWEnvironment environment) {
        super(environment);
    }
    
    @Override
    public String getName() {
        return "prints";
    }
    
    @Override
    public void run() throws CommandUIArgumentException {
        //purpose: show and prove to customer what their business with the company
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

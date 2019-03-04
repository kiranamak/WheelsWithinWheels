/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import java.time.LocalDate;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author asa
 */
public class TodayCommand extends ArgumentlessCommand<WWWEnvironment>{

    public TodayCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public String getName() {
        return "today";
    }

    @Override
    public void run() throws CommandUIArgumentException {
        LocalDate today = LocalDate.now();
        System.out.println(today.format(environment.dateFormatter));
    }
    
    @Override
    public String getHelpText(){
        return "Displays today's date, formatted correctly.";
    }
    
}

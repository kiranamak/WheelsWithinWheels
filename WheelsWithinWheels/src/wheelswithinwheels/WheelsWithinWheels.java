/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import wheelswithinwheels.commandui.TableView;
import wheelswithinwheels.commandui.TableViewWidthOverflowException;

/**
 *
 * @author asa
 */
public class WheelsWithinWheels {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            TableView tv = new TableView(3);
            String[] row = {"foo","bar","baz"};
            tv.addRow(row);
            String[] row2 = {"f","bs","sdkkdjksjkjsd"};
            tv.addRow(row2);
            tv.addColumns(2);
            String[] row3 = {"fred","","","","joe"};
            tv.addRow(row3);
            System.out.println(tv);
            
            System.out.println("done");
        } catch (TableViewWidthOverflowException e){
            System.out.println(e);
        }
    }
    
}

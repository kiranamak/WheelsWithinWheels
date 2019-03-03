/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui.tableview;

/**
 *
 * @author asa
 */
public class TableViewTesting {

    /**
     * @param args the command line arguments
     * @throws commandui.tableview.TableViewWidthOverflowException
     */
    public static void main(String[] args) throws TableViewWidthOverflowException {
        // TODO code application logic here
        TableView tv = new TableView(2);
        tv.addRow(new String[]{"A","b"});
        tv.addRow(new String[]{"C","D"});
        tv.addRow(new String[]{"E","F"});

        System.out.println(tv);
    }

}
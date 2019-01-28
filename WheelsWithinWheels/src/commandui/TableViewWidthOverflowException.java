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
public class TableViewWidthOverflowException extends Exception {

    public TableViewWidthOverflowException() {
        super("Tried to add row to table, but row was too large.");
    }
    
}

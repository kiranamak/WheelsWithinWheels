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
public class CommandUIArgumentException extends Exception {

    /**
     * Creates a new instance of <code>CommandUIArgumentException</code> without
     * detail message.
     */
    public CommandUIArgumentException() {
    }

    /**
     * Constructs an instance of <code>CommandUIArgumentException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CommandUIArgumentException(String msg) {
        super(msg);
    }
    
    public CommandUIArgumentException(int arg,String received,String expected){
        super("Expected "+expected+", but got \""+"\" instead. (Argument "+arg+")");
    }
    
    public CommandUIArgumentException(int expected, int recieved){
        super("Expected "+expected+" arguments, revieved "+recieved+" instead.");
                
    }
}

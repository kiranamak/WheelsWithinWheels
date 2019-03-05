/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

/**
 *
 * @author kmak
 */
public class Receivable {
    int amountReceivable;
    int amountPaid;
    int amountOutstanding;
    
    Receivable(int receivable, int paid) {
        this.amountReceivable = receivable;
        this.amountPaid = paid;
    }
    
    public int getReceivable() {
        return amountReceivable;
    }
    
    public int getPaid() {
        return amountPaid;
    }
    
    public int getOutstanding() {
        return amountReceivable - amountPaid;
    }
}

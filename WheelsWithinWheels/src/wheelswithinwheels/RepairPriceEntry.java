/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

/**
 *
 * @author asa
 */
public class RepairPriceEntry {
    private final int returnTime;
    private final int price;
    
    public RepairPriceEntry(int returnTime,int price){
        this.returnTime = returnTime;
        this.price = price;
    }

    public int getReturnTime() {
        return returnTime;
    }

    public int getPrice() {
        return price;
    }

}

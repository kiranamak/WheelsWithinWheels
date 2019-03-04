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

    int price;
    int repairLength;

    public RepairPriceEntry(int price, int repairLength) {
        this.price = price;
        this.repairLength = repairLength;
    }


    public int getPrice() {
        return price;
    }

    public int getRepairLength() {
        return repairLength;
    }
}

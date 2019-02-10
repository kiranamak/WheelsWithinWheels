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
public class RepairPrice {

    String brand;
    TuneupLevel level;
    int price;
    int repairLength;

    public RepairPrice(String brand, TuneupLevel level, int price, int repairLength) {
        this.brand = brand;
        this.level = level;
        this.price = price;
        this.repairLength = repairLength;
    }

    String getBrand() {
        return brand;
    }

    TuneupLevel getLevel() {
        return level;
    }

    public int getPrice() {
        return price;
    }

    int getRepairLength() {
        return repairLength;
    }
}

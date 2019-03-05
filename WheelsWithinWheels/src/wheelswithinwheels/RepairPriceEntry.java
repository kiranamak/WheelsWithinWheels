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
public class RepairPriceEntry implements BikeShopSaveable {

    String brand;
    String level;
    int price;
    int repairLength;

    public RepairPriceEntry(String brand, String level, int price, int repairLength) {
        this.brand = brand;
        this.level = level;
        this.price = price;
        this.repairLength = repairLength;
    }


    public int getPrice() {
        return price;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getLevel() {
        return level;
    }

    public int getRepairLength() {
        return repairLength;
    }
    
    @Override
    public String getSaveText(WWWEnvironment environment) {
        return "addrp " + brand + " " + level + " " + price + " " + repairLength;
    }
}

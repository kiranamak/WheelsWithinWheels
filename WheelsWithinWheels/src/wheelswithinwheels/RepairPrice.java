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
    int daysToRepair;
    RepairPrice(String brand, TuneupLevel level, int price, int daysToRepair){
        this.brand = brand;
        this.level = level;
        this.price = price;
        this.daysToRepair = daysToRepair;
    }
    String getBrand(){return brand;}
    TuneupLevel getLevel(){return level;}
    int getPrice(){return price;}
    int daysToRepair(){return daysToRepair;}
}

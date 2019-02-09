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
public class RepairPriceTable {
    int[] prices;
    String[] brands;
    private int getIndex(TuneupLevel level, String brand){
        int row = 0;
        int col = 0;
        int index = 0;
        while(index != brands.length){
            if(brands[index] == brand){row = index;}
            index += 1;
        }
        switch(level){
            case SILVER : col = 0;
            case GOLD : col = 1;
            case PLATINUM : col = 2;
            default : col = -1;
        }
        index = 0;
        if (row > 0){index += row * 3;}
        index += col;
        return index;
    }
    int getPrice(String brand, TuneupLevel level){return prices[getIndex(level, brand)];}
    void addPrice(RepairPrice price){
        brands[brands.length] = price.brand;
        prices[getIndex(price.level, price.brand)] = price.price;
    }
}
class Order implements Transaction {
    int customerNumber;
    String brand;
    TuneupLevel level;
    String comment;
    Date date;
    boolean completed = false;
    boolean returned = false;
    Order(int customerNumber, String brand, TuneupLevel level, String comment){
        this.customerNumber = customerNumber;
        this.brand = brand;
        this.level = level;
        this.comment = comment;
        date = new Date();
    }
    int getRepairPrice(){
        RepairPriceTable table = new RepairPriceTable();
        return table.getPrice(brand, level);
    }
    Date getPromisedDate(){}
    boolean isCompleted(){return completed;}
    void setCompleted(){completed = true;}
    int getOrderNumber(){
        //Index from orders array
    }
    boolean isReturned(){return returned;}
    void setReturned(){returned = true;}
    TuneupLevel getRepairLevel(){return level;}
    String getReport(){}
}

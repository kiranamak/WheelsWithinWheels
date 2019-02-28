/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author asa
 */
public class RepairPriceTable {

    public static RepairPriceTable shared = new RepairPriceTable();
    
    Map<String, Map<String, RepairPrice>> table = new HashMap();
    
    RepairPriceTable(){
        
    }

    public RepairPrice getPrice(String brand, String level) {
        return table.get(brand).get(level);
    }

    void addPrice(RepairPrice price) {
        if (table.get(price.brand) == null)
            table.put(price.brand,new HashMap());
        table.get(price.brand).put(price.level,price);
    }
    
    public String[] getBrands(){
        throw new UnsupportedOperationException();
    }
    
    public String[] getLevels(){
        throw new UnsupportedOperationException();
    }
}

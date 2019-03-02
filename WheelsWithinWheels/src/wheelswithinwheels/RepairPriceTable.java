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
    
    Map<String, Map<String, RepairPriceEntry>> table = new HashMap();
    
    RepairPriceTable(){
        
    }

    public RepairPriceEntry getPrice(String brand, String level) {
        return table.get(brand).get(level);
    }

    void addPrice(String brand,String level,RepairPriceEntry entry) {
        if (table.get(brand) == null)
            table.put(brand,new HashMap());
        table.get(brand).put(level,entry);
    }
    
    public String[] getBrands(){
        throw new UnsupportedOperationException();
    }
    
    public String[] getLevels(){
        throw new UnsupportedOperationException();
    }
}

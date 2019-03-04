/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author asa
 */
public class RepairPriceTable {

   
    Map<String, Map<String, RepairPriceEntry>> table = new HashMap();
    
    RepairPriceTable(){
        
    }

    public RepairPriceEntry getPrice(String brand, String level) {
        if (table.get(brand)==null) return null;
        return table.get(brand).get(level);
    }

    void addPrice(String brand, String level, RepairPriceEntry entry) {
        if (table.get(brand) == null)
            table.put(brand,new HashMap());
        table.get(brand).put(level,entry);
    }
    
    public String[] getBrands(){
        return (String[]) table.keySet().toArray();
    }
    
    public String[] getLevels(){
        return (String[]) table.entrySet().stream()
                .flatMap(
                        (Map.Entry<String,Map<String,RepairPriceEntry>> entry)->
                                (Stream<String>)((Map) entry.getValue())
                                        .keySet().stream()
                )
                .toArray();
    }
}

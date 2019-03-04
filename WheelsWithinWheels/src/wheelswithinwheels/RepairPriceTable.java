/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * @author asa
 */
public class RepairPriceTable implements BikeShopSaveable{

   
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
        return  table.keySet().stream().toArray(String[]::new);
    }
    
    public String[] getLevels(){
        return table.entrySet().stream()
                .flatMap(
                        (Map.Entry<String,Map<String,RepairPriceEntry>> entry)->
                                entry.getValue().keySet().stream()
                )
                .toArray(String[]::new);
    }
    
    @Override
    public String getSaveText(WWWEnvironment environment){
        StringBuilder save = new StringBuilder();
        for(String brand: getBrands()){
            for(String level: getLevels()){
                save.append(getSaveTextForEntry(brand, level)).append("\n");
            }
        }
        return save.toString();
    }
    
    protected String getSaveTextForEntry(String brand, String level) {
        return "addrp "+brand+" "+getPrice(brand, level).price+" "+level+" "+getPrice(brand, level).repairLength;
    }
}

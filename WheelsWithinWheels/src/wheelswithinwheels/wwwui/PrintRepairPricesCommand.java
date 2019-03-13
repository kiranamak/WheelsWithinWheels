/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.wwwui;

import commandui.ArgumentlessCommand;
import commandui.CommandUIArgumentException;
import commandui.tableview.TableView;
import commandui.tableview.TableViewWidthOverflowException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;
import wheelswithinwheels.RepairPriceEntry;
import wheelswithinwheels.RepairPriceTable;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author asa
 */
public class PrintRepairPricesCommand extends ArgumentlessCommand<WWWEnvironment>{

    public PrintRepairPricesCommand(WWWEnvironment environment) {
        super(environment);
    }

    @Override
    public String getName() {
        return "printrp";
    }
    
    @Override
    public void run() throws CommandUIArgumentException {
        RepairPriceTable pricesTable = environment.getRepairPriceTable();
        SortedSet<String> brands = new TreeSet<>(Arrays.asList(pricesTable.getBrands()));
        SortedSet<String> levels = new TreeSet<>(Arrays.asList(pricesTable.getLevels()));
        TableView table = new TableView(levels.size() + 1);
        String[] headers = Stream.concat(Stream.of("Prices"),
                levels.stream().map(Object::toString))
                .toArray(String[]::new);
        try {
            table.addRow(headers);
            for (String brand:brands){
                Stream<String> rowData = levels.stream()
                        .map((String level)->pricesTable.getPrice(brand,level))
                        .filter((RepairPriceEntry price) -> price != null)
                        .map((RepairPriceEntry price)->
                                        "$"+Integer.toString(price.getPrice())
                                        +", "+Integer.toString(price.getRepairLength())+" days");
                String[] row = Stream.concat(Stream.of(brand),rowData).toArray(String[]::new);
                table.addRow(row);
            }
        } catch (TableViewWidthOverflowException ex) {
            throw new IllegalStateException();
        }
        System.out.print(table);
    }
    
    
    @Override
    public String getHelpText(){
        return "Display the Repair Price table."; 
    }
}

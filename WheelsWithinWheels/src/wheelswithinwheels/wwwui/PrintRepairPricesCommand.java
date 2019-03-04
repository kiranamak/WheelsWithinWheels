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
        String[] brands = pricesTable.getBrands();
        String[] levels = pricesTable.getLevels();
        TableView table = new TableView(levels.length);
        String[] headers = (String[]) Stream.concat(Stream.of("Prices"),
                Arrays.stream(levels).map(Object::toString)
        ).toArray();
        try {
            table.addRow(headers);
            for (String brand:brands){
                Stream rowData = Arrays.stream(levels)
                                .map((String level)->pricesTable.getPrice(brand,level))
                                .map((RepairPriceEntry price)->Integer.toString(price.getPrice()));
                String[] row = (String[])Stream.concat(Stream.of(brand),rowData).toArray();
                table.addRow(headers);
            }
        } catch (TableViewWidthOverflowException ex) {
            throw new RuntimeException();
        }
        System.out.print(table);
    }
    
    
    @Override
    public String getHelpText(){
        return "Display the Repair Price table.";
    }
}

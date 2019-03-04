/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui.tableview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class WidenableTableView extends TableView {

    public WidenableTableView(int width) {
        super(width);
    }
    
    public void addColumns(int cols) {
        for (int i = 0; i < cols; i++) {
            ArrayList<String> col = new ArrayList<String>();
            for (int j = 0; j < maxHeight(); j++) {
                col.add("");
            }
            columns.add(col);
        }
        width += cols;
    }
    
    //Not sure if needed.
    @Override
    protected int maxHeight() {
        return Stream.of(columns)
                .mapToInt(List::size)
                .max()
                .orElse(0);
    }

    
}

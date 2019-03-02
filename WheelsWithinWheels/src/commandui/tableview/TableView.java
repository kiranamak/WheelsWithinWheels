/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui.tableview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author asa
 */
public class TableView {

    ArrayList<ArrayList<String>> columns;
    int width;
    String columnDelimiter = " ";

    public static int startingDepth = 4;

    public TableView(int width) {
        columns = new ArrayList<>(width);
        this.width = width;
        for (int i = 0; i < width; i++) {
            columns.add(new ArrayList<>());
        }
    }

    public void addRow(String[] row) throws TableViewWidthOverflowException {
        if (row.length > width) {
            throw new TableViewWidthOverflowException();
        }
        for (int i = 0; i < row.length; i++) {
            columns.get(i).add(row[i]);
        }
    }
    
    public void addRow(Object[] row) throws TableViewWidthOverflowException{
        addRow(Arrays.stream(row).map(Object::toString).toArray());
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

    @Override
    public String toString() {
        System.out.println(maxHeight());
        int[] widths = widths();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < maxHeight(); i++) {
            for (int j = 0; j < columns.size(); j++) {
                ArrayList<String> column = columns.get(j);
                String cell;
                try {
                    cell = column.get(i);
                } catch (IndexOutOfBoundsException e) {
                    cell = "";
                }
                result.append(paddedCell(cell, widths[j]));
                result.append(columnDelimiter);
            }
            result.append("\n");
        }
        return result.toString();
    }

    private int[] widths() {
        int[] widths = new int[width];
        for (int i = 0; i < widths.length; i++) {
            widths[i] = columns.get(i).stream()
                    .mapToInt(String::length)
                    .max()
                    .orElse(0);
        }
        return widths;
    }

    private String paddedCell(String cell, int width) {
        StringBuffer result = new StringBuffer(cell);
        while (result.length() < width) {
            result.append(" ");
        }
        return result.toString();
    }

    private int maxHeight() {
        IntStream a = Stream.of(columns)
                .mapToInt(ArrayList::size);
        System.out.println(Arrays.toString(a.toArray()));
//        int b = a
//                .max()
//                .orElse(0);
//        return b;
return 0;
    }


}

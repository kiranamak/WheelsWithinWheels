/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui.tableview;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author asa
 */
public class TableView {

    List<List<String>> columns;
    int width;
    String columnDelimiter = "  ";
    List<HorizontalAlignDirection> horizontalAlignDirections;

    public TableView(int width) {
        columns = new ArrayList<>(width);
        this.width = width;
        this.horizontalAlignDirections = new ArrayList<>(width);
        for (int i = 0; i < width; i++) {
            columns.add(new ArrayList<>());
            horizontalAlignDirections.add(HorizontalAlignDirection.defaultAlign);
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
        addRow((String[]) Arrays.stream(row).map(Object::toString).toArray());
    }

    @Override
    public String toString() {
        int[] widths = cellWidths();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxHeight(); i++) {
            for (int j = 0; j < columns.size(); j++) {
                List<String> column = columns.get(j);
                String cell;
                try {
                    cell = column.get(i);
                } catch (IndexOutOfBoundsException e) {
                    cell = "";
                }
                result.append(paddedCell(cell, widths[j], horizontalAlignDirections.get(j)));
                result.append(columnDelimiter);
            }
            result.append("\n");
        }
        return result.toString();
    }
    

    private int[] cellWidths() {
        int[] widths = new int[width];
        for (int i = 0; i < widths.length; i++) {
            widths[i] = columns.get(i).stream()
                    .mapToInt(String::length)
                    .max()
                    .orElse(0);
        }
        return widths;
    }

    protected String paddedCell(String cell, int width, HorizontalAlignDirection horizontalAlignDirection) {
        StringBuffer result = new StringBuffer(cell);
        while (result.length() < width) {
            if (horizontalAlignDirection == HorizontalAlignDirection.RIGHT) {
                result.insert(0, " ");
            } else {
                result.append(" ");
            }
        }
        return result.toString();
    }

    protected int maxHeight() {
        return columns.get(0).size();
        //TODO:Should check for no colmumns.
    }

    public void horizontalAlign(List<HorizontalAlignDirection> format) {
        this.horizontalAlignDirections = format;
        //TODO: Must check for width mismatch
    }
    
    public void horizontalAlign(HorizontalAlignDirection... format){
        horizontalAlign(Arrays.asList(format));
    }


}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import commandui.FeildwiseEditingField;
import commandui.FieldwiseEditable;
import commandui.FieldwiseEditingUI;
import commandui.TableView;
import commandui.TableViewWidthOverflowException;

/**
 *
 * @author asa
 */
public class WheelsWithinWheels {
    public static void main(String[] args){
        sampleEditing();
    }
    
    public static void sampleEditing(){
        FWETestModel obj = new FWETestModel();
        commandui.FieldwiseEditingUI<FWETestModel> ui;
        ui = new FieldwiseEditingUI<>(obj);
        ui.addQuitCommand("quit");
        ui.run();
        System.out.println("Object State");
        System.out.println(obj.a);
        System.out.println(obj.b);
        
    }
    
    
    

    public static void sampleTableView() {
        try {
            TableView tv = new TableView(3);
            String[] row = {"foo","bar","baz"};
            tv.addRow(row);
            String[] row2 = {"f","bs","sdkkdjksjkjsd"};
            tv.addRow(row2);
            tv.addColumns(2);
            String[] row3 = {"fred","","","","joe"};
            tv.addRow(row3);
            System.out.println(tv);
            
            System.out.println("done");
        } catch (TableViewWidthOverflowException e){
            System.out.println(e);
        }
    }
    
    static class FWETestModel implements FieldwiseEditable{
        String a="";
        Integer b=0;
        
        @Override
        public FeildwiseEditingField[] getFields() {
            return new FeildwiseEditingField[]{
                new FeildwiseEditingField("A: ","a",a, this::setA),
                new FeildwiseEditingField("B: ","b",b.toString(), this::setB)
            };
        }
        
        public Void setA(String newValue){
            a = newValue;
            return null;
        }
        
        public Void setB(String newValue){
            b = Integer.parseInt(newValue.trim());
            return null;
        }
    }
    
}
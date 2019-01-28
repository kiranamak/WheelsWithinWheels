/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui.fieldwiseediting;

import java.util.function.Function;

/**
 *
 * @author asa
 */
public class FeildwiseEditingField {
    String name;
    String command;
    String value;
    Function<String,Void> setter;
    
    public FeildwiseEditingField(String name,String command,String value,Function<String,Void> setter){
        this.name = name;
        this.command = command;
        this.value = value;
        this.setter = setter;
    }
}

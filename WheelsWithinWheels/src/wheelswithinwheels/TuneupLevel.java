/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

/**
 *
 * @author asa
 */
public enum TuneupLevel {
    SILVER("Silver"), GOLD("Gold"), PLATINUM("Platinum");
    
    private String value;
    private TuneupLevel(String value) {
        this.value = value;
    }
    public String value() { return value; }
}

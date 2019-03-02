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
public enum OrderStatus {
    PENDING("PENDING"),COMPLETED("COMPLETED"),RETURNED("RETURNED");
    private String value;
    private OrderStatus(String value) {
        this.value = value;
    }
    public String value() { return value; }
}

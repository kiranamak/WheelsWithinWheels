/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;
import java.time.LocalDate;

/**
 *
 * @author asa
 */
public interface Transaction {

    String getReport(WWWEnvironment environment);
    LocalDate getDate();
}

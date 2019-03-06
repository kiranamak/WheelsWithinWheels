/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import wheelswithinwheels.WWWEnvironment;

/**
 *
 * @author asa
 */
public abstract class Command<Environment> {

    protected Environment environment;

    public Command(Environment environment) {
        this.environment = environment;
    }

    public abstract String getName();

    public abstract void run(String args) throws CommandUIArgumentException;

    protected int parseIntArgument(int argPos, String[] args) throws CommandUIArgumentException {
        try {
            return Integer.parseInt(args[argPos]);
        } catch (NumberFormatException e) {
            throw new CommandUIArgumentException(1 + argPos, args[argPos], "an integer");
        }
    }
    
    protected LocalDate parseDateArgument(int argPos, String[] args,DateTimeFormatter formatter) throws CommandUIArgumentException {
        try {
            LocalDate date = LocalDate.parse(args[argPos], formatter);
            return date;
        } catch (Exception e) {
            throw new CommandUIArgumentException(argPos + 1, args[argPos], "MMDDYYYY");
        }
    }

    public String getHelpText() {
        return "No help text provided.";
    }

    public String getHelpArguments() {
        return "";
    }
    
    public boolean inHelpList() {
        return true;
    }
}
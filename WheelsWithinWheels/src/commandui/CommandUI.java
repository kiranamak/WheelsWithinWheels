/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author asa
 */
public class CommandUI {

    private Scanner scanner;
    private String welcomeMessage = "Welcome, please enter a command (type help for a list of available commands):";
    private String prompt = ">";
    private HashMap<String, Command<?>> commandMap;
    private boolean running = false;
    private boolean echo;

    public CommandUI(boolean echo){
        this.scanner = new Scanner(System.in);
        this.echo = echo;
    }
    
    public CommandUI(Command[] commands, boolean echo) {
        this(echo);
        updateCommands(commands);
    }

    public final void updateCommands(Command[] commands) {
        commandMap = new HashMap();
        for (Command command : commands) {
            commandMap.put(command.getName(), command);
        }
    }

    public final void addCommand(Command command) {
        commandMap.put(command.getName(), command);
    }

    public final void addQuitCommand(String name) {
        addCommand(new QuitCommand(this, name));
    }

    public final void setWelcomeMessage(String msg) {
        welcomeMessage = msg;
    }

    public final void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void run() {
        this.running = true;
        setup();
        System.out.println(welcomeMessage);
        System.out.print(">");
        while (running) {
            runPrompt();
            System.out.print(prompt);
            if (!scanner.hasNext()) running = false;
        }
    }

    protected void setup() {
    }

    private final void runPrompt() {
        String commandName = scanner.next();
        String argString = scanner.nextLine();
        Command command = commandMap.get(commandName);
        if (echo) {
            System.out.println(commandName + " " + argString);
        }
        if (command == null) {
            reportUnknownCommand();
            postCommand();
            return;
        }
        try {
            if (argString.length() > 0) {
                argString = argString.substring(1);//drop leading space
            }
            command.run(argString);
        } catch (CommandUIArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            postCommand();
        }
    }

    private void reportUnknownCommand() {
        System.out.println("Unknown Command");
    }

    public final void stop() {
        running = false;
    }

    protected void postCommand() {
    }
    
    public final Collection<Command<?>> getCommands(){
        return commandMap.values();
    }
    
    public final void useInputStream(InputStream inStream){
        this.scanner = new Scanner(inStream);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author asa
 */
public class CommandUI {

    private final Scanner scanner;
    private String welcomeMessage = "";
    private String prompt = ">";
    private HashMap<String, Command<?>> commandMap;
    private boolean running = false;

    public CommandUI(){
        this.scanner = new Scanner(System.in);
        
    }
    
    public CommandUI(Command[] commands) {
        this();
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
        while (running) {
            runPrompt();
        }
    }

    protected void setup() {
    }

    private final void runPrompt() {
        System.out.print(prompt);
        String commandName = scanner.next();
        String argString = scanner.nextLine();
        Command command = commandMap.get(commandName);
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
            System.out.println(e);
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
}

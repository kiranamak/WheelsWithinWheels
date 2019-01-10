/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels.commandui;

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
    HashMap<String,Command> commandMap;
    private boolean running = false;
    
    public CommandUI(Command[] commands){
        this.scanner = new Scanner(System.in);
        commandMap = new HashMap();
        for (Command command : commands){
            commandMap.put(command.getName(),command);
        }
        commandMap.put("quit", new QuitCommand(this));
    }
    
    public void setWelcomeMessage(String msg){
        welcomeMessage = msg;
    }
    
    public void setPrompt(String prompt){
        this.prompt = prompt;
    }
    
    public void run(){
        this.running = true;
        System.out.println(welcomeMessage);
        while (running){
            runPrompt();
        }
    }
    
    private void runPrompt(){
        System.out.print(prompt);
        String commandName = scanner.next();
        String argString = scanner.nextLine();
        Command command = commandMap.get(commandName);
        if (command==null) {
            reportUnknownCommand();
            return;
        }
        String[] args = parseArgs(argString);
        try {
            command.run(args);
        }
        catch (CommandUIArgumentException e){
            System.out.println(e);
        }
    }
    
    
    private String[] parseArgs(String argString){
        String[] rawArgs = argString.split("\\s+");
        return Arrays.stream(rawArgs)
                .filter((s) -> !s.equals(""))
                .toArray(String[]::new);
    }
    
    private void reportUnknownCommand(){
        System.out.println("Unknown Command");
    }
    
    public void stop(){
        running = false;
    }
}

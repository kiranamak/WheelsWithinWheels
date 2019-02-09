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
    HashMap<String,Command> commandMap;
    private boolean running = false;
    
    public CommandUI(Command[] commands){
        this.scanner = new Scanner(System.in);
        updateCommands(commands);
    }
    
    public void updateCommands(Command[] commands){
        commandMap = new HashMap();
        for (Command command : commands){
            commandMap.put(command.getName(),command);
        }
    }
    
    public void addCommand(Command command){
        commandMap.put(command.getName(),command);
    }
    
    public void addQuitCommand(String name){
        addCommand(new QuitCommand(this,name));
    }
    
    public void setWelcomeMessage(String msg){
        welcomeMessage = msg;
    }
    
    public void setPrompt(String prompt){
        this.prompt = prompt;
    }
    
    public void run(){
        this.running = true;
        setup();
        System.out.println(welcomeMessage);
        while (running){
            runPrompt();
        }
    }
    
    protected void setup(){}
    
    private void runPrompt(){
        System.out.print(prompt);
        String commandName = scanner.next();
        String argString = scanner.nextLine();
        Command command = commandMap.get(commandName);
        if (command==null) {
            reportUnknownCommand();
            postCommand();
            return;
        }
        try {
            if (argString.length() > 0)
                argString = argString.substring(1);//drop leading space
            command.run(argString);
        }
        catch (CommandUIArgumentException e){
            System.out.println(e);
        }
        finally{
            postCommand();
        }
    }
    
    
   
    
    private void reportUnknownCommand(){
        System.out.println("Unknown Command");
    }
    
    public void stop(){
        running = false;
    }
    
    protected void postCommand(){}
}
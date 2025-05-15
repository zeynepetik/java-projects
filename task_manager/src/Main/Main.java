package src.Main;

import java.util.Scanner;
import src.TaskManager.*;

/**
 * Main entry point for the task management program.
 * Time Complexity: O(m + log n) where m is the number of users and n is the number of tasks.
 * Accepts user commands to add tasks or execute all tasks.
 */
public class Main {
    public static void main(String[] args){
        if(args.length<1){
            System.err.println("Please provide the config file path as an argument.");
            return;
        }
        String configFile=args[0];
        TaskManager manager=new TaskManager(configFile);
        Scanner scan=new Scanner(System.in);
        String taskCommand;
        while(scan.hasNextLine()){
            taskCommand=scan.nextLine().trim();
            try {
                if(taskCommand.equalsIgnoreCase("execute")){
                    System.out.println("Executing tasks...");
                    manager.executeTasks();
                    break;
                }
    
                String[] parts=taskCommand.split("//");
                int userID=Integer.parseInt(parts[0].trim());
                manager.addTask(userID);  
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

package src.TaskManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages tasks and users based on priority. Reads user data from config file
 * and manages a priority queue of tasks.
 */
public class TaskManager {
    private ArrayList<MyUser> users;
    private MyPriorityQueue<MyTask> tasks;
    private int taskCounter;

    /**
     * Constructs a TaskManager using a configuration file to load users.
     * Time Complexity: O(n) where n is the number of lines of the configuration file.
     * @param configFile the path to the configuration file
     */
    public TaskManager(String configFile){
        users=new ArrayList<MyUser>();
        tasks=new MyHeap<MyTask>();
        taskCounter=0;
        readConfig(configFile);
    }

     /**
     * Reads users and their priorities from a config file.
     * Time Complexity: O(n) where n is the number of lines in the file.
     * @param configFile the file path containing user information
     */
    private void readConfig(String configFile){
        try {
            File file = new File(configFile);
            Scanner scan=new Scanner(file);
            int userCount=0;
            while(scan.hasNextLine()){
                String line=scan.nextLine();
                String[] parts=line.trim().split("//");
                    Integer priority=Integer.parseInt(parts[0].trim());
                    MyUser user=new MyUser(userCount,priority);
                    userCount++;
                    users.add(user);   
            }
            scan.close();            
        } catch (Exception e) {
            System.err.println("Error reading config file: " + e.getMessage());
        }
    }

    /**
     * Adds a new task for a given user ID.
     * Time Complexity: O(log n) for adding the task, O(log n) for maintaining the heap property.
     * @param userID the ID of the user to assign the task to
     * @throws IllegalArgumentException if the user ID is invalid
     */
    public void addTask(int userID){
        if(userID<0 || userID>=users.size()){
            throw new IllegalArgumentException("Invalid userID: " + userID);
        }
        MyUser user=users.get(userID);
        MyTask task=new MyTask(user,taskCounter);
        taskCounter++;
        tasks.add(task);
    }

    /**
    * Executes all tasks in order of priority.
    * Time Complexity: O(n log n) where n is the number of tasks.
    */
    public void executeTasks(){
        while(!tasks.isEmpty()){
            MyTask task=tasks.poll();
            System.out.println(task.toString());
        }
    }
}
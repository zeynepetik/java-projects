package src.TaskManager;

/**
 * Represents a task associated with a user, with a unique ID and comparable by priority.
 */
public class MyTask implements Comparable<MyTask> {
    private MyUser user;
    Integer id;

    /**
     * Constructs a task for the specified user and ID.
     * Time Complexity: O(1)
     * @param user the user who owns the task
     * @param id the unique identifier of the task
     */
    public MyTask(MyUser user, Integer id){
        this.user=user;
        this.id=id;
    }

    /**
     * Returns a string representation of the task.
     * Time Complexity: O(1)
     * @return the formatted task string
     */
    public String toString(){
        return "Request "+id+" User "+user.getId();
    }

    /**
     * Compares this task with another based on user priority and task ID.
     * Time Complexity: O(1)
     * @param other the task to compare against
     * @return a negative number, zero, or positive number as this task is less than, equal to, or greater than the other
     */
    public int compareTo(MyTask other){
        int comparison = this.user.getPriority().compareTo(other.user.getPriority());

        if(comparison==0){
            return this.id.compareTo(other.id);
        }
        return comparison;
    }

     /**
     * Returns the user who owns this task.
     * Time Complexity: O(1)
     * @return the task's user
     */
    public MyUser getUser(){
        return user;
    }

    /**
     * Returns the unique ID of the task.
     * Time Complexity: O(1)
     * @return task ID
     */
    public int getId(){
        return id;
    }
}

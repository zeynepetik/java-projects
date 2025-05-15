package src.TaskManager;

/**
 * Represents a user with an ID and a priority value.
 */
public class MyUser {
    private Integer id;
    private Integer priority;
    
    /**
     * Constructs a user with the specified ID and priority.
     * Time Complexity: O(1)
     * @param id the user's ID
     * @param priority the user's priority
     */
    public MyUser(Integer id, Integer priority) {
        this.id = id;
        this.priority = priority;
    }

    /**
     * Gets the user ID.
     * Time Complexity: O(1)
     * @return the ID of the user
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the priority of the user.
     * Time Complexity: O(1)
     * @return the user's priority
     */
    public Integer getPriority() {
        return priority;
    }
    
}

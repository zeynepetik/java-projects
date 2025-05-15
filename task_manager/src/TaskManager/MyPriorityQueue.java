package src.TaskManager;

/**
 * Interface for a generic priority queue structure.
 * @param <T> the type of elements stored in the queue
 */
interface MyPriorityQueue<T extends Comparable<T>> {
    /**
     * Adds an item to the priority queue.
     * @param item the item to be added
     */
    void add(T item);

    /**
     * Retrieves and removes the highest priority item.
     * @return the polled item, or null if the queue is empty
     */
    T poll();

    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();    
}

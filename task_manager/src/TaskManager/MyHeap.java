package src.TaskManager;

import java.util.ArrayList;

/**
 * A generic Min-Heap based implementation of the MyPriorityQueue interface.
 * @param <T> the type of elements stored in the heap, which must be Comparable
 */
public class MyHeap<T extends Comparable<T>> implements MyPriorityQueue<T> {
    private ArrayList<T> heap;

    /**
     * Constructs an empty heap.
     * Time Complexity: O(1)
     */
    public MyHeap() {
        heap = new ArrayList<T>();
    }

    /**
     * Adds an item to the heap and maintains the heap property.
     * Time Complexity: O(log n)
     * @param item the item to be added
     */
    @Override
    public void add(T item) {
        heap.add(item);
        shiftUp(heap.size()-1);
    }

    /**
     * Removes and returns the smallest item in the heap.
     * Time Complexity: O(log n)
     * @return the smallest item, or null if the heap is empty
     */
    @Override
    public T poll() {
        if(this.isEmpty()){
            return null;
        }
        T to_be_removed=heap.get(0);
        if(heap.size()==1){
            heap.remove(0);
        }else{
            heap.set(0, heap.get(heap.size()-1));
            heap.remove(heap.size()-1);
            shiftDown(0);
        }
        return to_be_removed;
    }

    /**
     * Checks if the heap is empty.
     * Time Complexity: O(1)
     * @return true if the heap is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Restores the heap property by shifting up the item at the given index.
     * Time Complexity: O(log n)
     * @param index the index of the item to shift up
     */
    private void shiftUp(int index){
        if(index<0 || index>=heap.size()){
            return;
        }
        T item=heap.get(index);
        int parent=(index-1)/2;

        if(heap.get(index).compareTo(heap.get(parent))<0){
            heap.set(index, heap.get(parent));
            heap.set(parent,item);
            shiftUp(parent);
        }
    }

    /**
     * Restores the heap property by shifting down the item at the given index.
     * Time Complexity: O(log n)
     * @param index the index of the item to shift down
     */
    private void shiftDown(int index){
        T item=heap.get(index);
        int leftChild=2*index+1;
        int rightChild=2*index+2;
        int smallest=index;
        if(heap.size()>leftChild && heap.get(smallest).compareTo(heap.get(leftChild))>0){
            smallest=leftChild;
        }
        if(heap.size()>rightChild && heap.get(smallest).compareTo(heap.get(rightChild))>0){
            smallest=rightChild;
        }
        if(smallest!=index){
            heap.set(index,heap.get(smallest));
            heap.set(smallest,item);
            shiftDown(smallest);
        }
    }

    
}

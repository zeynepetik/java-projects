package src.structures;

public class MyArrayList<T> {
    private T[] list;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 100;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.list = (T[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = initialCapacity;
        this.size = 0;
        this.list = (T[]) new Object[capacity];
    }

    public void add(T element) {
        if (size >= capacity) {
            resize();
        }
        list[size++] = element;
    }

    public void add(int index, T element){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size >= capacity) {
            resize();
        }
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        T[] newList = (T[]) new Object[capacity];
        for(int i=0;i<size;i++){
            newList[i] = list[i];
        }
        list = newList;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return list[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        list[index] = element;
    }  
    
    public int size() {
        return size;
    }
}

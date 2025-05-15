package src.structures;

public class GTUHashSet<E> {
    private static final Object present=new Object();
    private GTUHashMap<E,Object> map;
    public GTUHashSet(){
        map=new GTUHashMap<E,Object>();
    }

    public GTUHashSet(int initCap){
        map=new GTUHashMap<E,Object>(initCap);
    }

    public void add(E element){
        map.put(element, present);
    }

    public void remove(E element){
        map.remove(element);
    }

    public boolean contains(E element){
        return map.containsKey(element);
    }

    public int size(){
        return map.size();
    }
}

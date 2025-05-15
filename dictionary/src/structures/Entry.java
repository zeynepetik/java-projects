package src.structures;

public class Entry<K,V>{
        K key;
        V value;
        boolean isDeleted=false;

        Entry(K key,V value){
            this.key=key;
            this.value=value;
        }
    }

package src.structures;

public class GTUHashMap<K, V>{
    private static final int DEFAULT_CAP=11;
    private static final float DEFAULT_LOAD_FACTOR=0.75f;

    private Entry<K,V>[] table;
    private int size;
    private float load_factor;
    private int threshold;
    private int collisions;

    @SuppressWarnings("unchecked")
    public GTUHashMap(){
        table=(Entry<K,V>[]) new Entry[DEFAULT_CAP];
        this.load_factor=DEFAULT_LOAD_FACTOR;
        threshold=(int) (DEFAULT_CAP*load_factor);
        this.size=0;
        this.collisions=0;
    }

    @SuppressWarnings("unchecked")
    public GTUHashMap(int initialCap){
        table=(Entry<K,V>[]) new Entry[initialCap];
        this.load_factor=DEFAULT_LOAD_FACTOR;
        threshold=(int) (initialCap*load_factor);
        this.size=0;
        this.collisions=0;
    }

    private int hash(K key){
        return key.hashCode()% table.length;  
    }

    private int findIndex(K key){
        int index=hash(key);
        if(index<0)
            index+=table.length;
        while(table[index]!=null && (table[index].isDeleted || !table[index].key.equals(key))){
            index++;
            if(index>=table.length){
                index=0;
            }
        }
        return index;
    }

    private boolean isPrime(int n){
        if(n<=1)return false;
        if(n==2)return true;
        if(n%2==0)return false;
        for(int i=3; i<=Math.sqrt(n); i+=2){
            if(n%i==0)return false;
        }
        return true;
    }

    private int nearestPrime(int n){
        int lower=n;
        if(n>1)
            lower=n--;
        int higher=n++;
        while(!isPrime(lower) && lower>2)
            lower--;
        while(!isPrime(higher))
            higher++;
        if((n-lower)<(higher-n)){
            return lower;
        }else{
            return higher;
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        int newCap=nearestPrime(table.length*2);
        threshold=(int)(newCap*load_factor);
        Entry<K,V>[] oldTable=table;
        table=(Entry<K,V>[]) new Entry[newCap];
        size=0;

        for(int i=0;i<oldTable.length;i++){
            if(oldTable[i]!=null && !oldTable[i].isDeleted){
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }

    public void put(K key, V value){
        if(key==null){
            throw new NullPointerException("Key cannot be null");
        }
        if(size>=threshold){
            resize();
        }

        int index=findIndex(key);
        if(table[index]==null || table[index].isDeleted){
            table[index]=new Entry<>(key,value);
            size++;
        }else{
            //update existing value
            table[index].value=value;
        }
    }

    public V get(K key){
        if(key==null){
            throw new NullPointerException("Key cannot be null");
        }
        int index=findIndex(key);
        if(table[index]!=null && !table[index].isDeleted){
            return table[index].value;
        }
         return null;  
    }

    public V remove(K key){
        int index=findIndex(key);
        if(table[index]==null || table[index].isDeleted){
            return null;
        }else{
            V value=table[index].value;
            table[index].isDeleted=true;
            size--;
            return value;
        }
    }

    public boolean containsKey(K key){
        if(key==null){
            throw new NullPointerException("Key cannot be null");
        }
        int index=findIndex(key);
        return (table[index]!=null && !table[index].isDeleted && table[index].key.equals(key));
    }

    public int size(){
        return size;
    }
}

    
   
public abstract class SimpleHashSet
            extends java.lang.Object
            implements SimpleSet{

    /**the highest part of the set that can be taken*/
    protected float highestCapacity;

    /**the lowest part of the set that can be taken*/
    protected float lowestCapacity;

    /**the current capacity of the set*/
    protected int currentSize;

    /**the current taken capacity of the set*/
    protected int caughtCapacity;

    /**A superclass for implementations of hash-sets implementing the SimpleSet interface.*/
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;
    protected static final int INITIAL_CAPACITY = 16;


    /** Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
     * DEFAULT_HIGHER_CAPACITY */
    SimpleHashSet(){
        this.highestCapacity = DEFAULT_HIGHER_CAPACITY;
        this.lowestCapacity = DEFAULT_LOWER_CAPACITY;
        this.currentSize = INITIAL_CAPACITY;

    }

    /** Constructs a new hash set with capacity INITIAL_CAPACITY.
     * @param upperLoadFactor - the upper load factor before rehashing
     * @param lowerLoadFactor - the lower load factor before rehashing*/
    SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.highestCapacity = upperLoadFactor;
        this.lowestCapacity = lowerLoadFactor;
        this.currentSize = INITIAL_CAPACITY;

    }

    /**The current capacity (number of cells) of the table.*/
    public abstract int capacity();

    /** hashing indices to fit within the current table capacity.
     * @param index - the index before clamping
     * @return an index properly clamped*/
    protected int clamp(int index){
        return Math.abs(index % (this.currentSize)); //I added "abs" because of bugs in the way
    }

    /** @return The lower load factor of the table.*/
    protected float getLowerLoadFactor(){
        return this.lowestCapacity;
    }

    /** @return The higher load factor of the table.*/
    protected float getUpperLoadFactor(){
        return this.highestCapacity;
    }

    /**fills the set with strings
     * @param data - an array of strings
     * @retrun an SimpleHashSet with all the data inside*/
    void loadData(String[] data){
        for (int i = 0; i < data.length; i++){
            add(data[i]);
        }
    }


}

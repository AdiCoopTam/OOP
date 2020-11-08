public class ClosedHashSet extends SimpleHashSet{

    /**the temporary array we use in the way to the final object*/
    private String[] myArray;

    /** A default constructor. **/
    public ClosedHashSet() {
        super();
        this.myArray = new String[INITIAL_CAPACITY];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16)
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     * */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        this.myArray = new String[this.currentSize];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * @param data - an array of .*/
    public ClosedHashSet(java.lang.String[] data) {
        super();
        this.myArray = new String[this.currentSize];
        loadData(data);
    }

    /**
     Description copied from interface: supplied.SimpleSet
     Add a specified element to the set if it's not already in it.
     @param newValue - New value to add to the set
     @return False if newValue already exists in the set**/
    public boolean add(java.lang.String newValue){
        if (contains(newValue)){
            return false;
        }
        else {
            addToArray(this.myArray, newValue);
            caughtCapacity++;
            overLoad();
            return true;}
    }


    /**
     Description copied from interface: supplied.SimpleSet
     Look for a specified value in the set.
     @param  searchVal - Value to search for
     @return True if searchVal is found in the set*/
    public boolean contains(java.lang.String searchVal){
        return findMyIndex(searchVal) != -1;
    }

    /**
     Description copied from interface: supplied.SimpleSet
     Remove the input element from the set.
     @param toDelete - Value to delete
     @return true if toDelete is found and deleted
     * */
    public boolean delete(java.lang.String toDelete){
        if (!contains(toDelete)){
            return false;
        }
        else {
            int index = findMyIndex(toDelete);
            this.myArray[index] = null;
            this.caughtCapacity--;
            underLoad();
            return false;
        }
    }

    /**@return The number of elements currently in the set*/
    public int size(){
        return this.caughtCapacity;
    }

    /**
     * @implSpec capacity in class SimpleHashSet
     * @return The number of elements currently in the set*/
    public int capacity(){
        return this.currentSize;
    }

    /**checks if the set is overload. If is, resizes it*/
    private void overLoad(){
        if (this.caughtCapacity > this.currentSize*this.highestCapacity){
            String[] newArray = new String[this.currentSize*2];
            currentSize *= 2;
            replace(newArray);
        }
    }

    /**checks if the set is not full enough. If is, resizes it*/
    private void underLoad(){
        if (this.caughtCapacity < this.currentSize*this.lowestCapacity){
            String[] newArray = new String[this.currentSize/2];
            currentSize /= 2;
            replace(newArray);
        }
    }

    /**finds the index of a given candidate
     * @return an index of the candidate, if exists - -1 else.*/
    private int findMyIndex(java.lang.String searchVal){
        int index = clamp(searchVal.hashCode());
        if (myArray[index] == searchVal){
            return index;
        }
        for (int i = 1; i < currentSize; i += theNext(i)){
            int currentIndex = clamp(index+i);
            if (myArray[currentIndex] == null){
                continue;
            }
            else if (myArray[currentIndex].equals(searchVal)){
                return index;
            }
        }
        return -1;
    }

    /**
     * this method adds objects for an simple array
     * @param arr - the array we are updating
     * @return - the array with a new object
     * */
    private String[] addToArray(String[] arr, java.lang.String newValue){
        int index = clamp(newValue.hashCode());
        if (arr[index] == null){
            arr[index] = newValue;
            return arr;
        }
        else {
            for (int i = 1; i < arr.length; i += theNext(i)){
                int currentIndex = clamp(index+i);
                if (arr[currentIndex] == null){
                    arr[currentIndex] = newValue;
                    return arr;
                }
            }
        }
        return arr;
    }

    /**
     * this method replaces an array with a new one
     * @param newArray - the new array which will replace the old one
     * */
    private void replace(String[] newArray){
        for (int i = 0; i< this.myArray.length; i++){
            if (myArray[i] == null){
                continue;
            }
            else {
                addToArray(newArray, myArray[i]);
            }
        }
        myArray = newArray;
    }

    /**
     * finds the next index
     * @param index - the current index
     * @return the next delay
     * */
    private int theNext(int index){
        return (index*index + index) / 2;
    }

}





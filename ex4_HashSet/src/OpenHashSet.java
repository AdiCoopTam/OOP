import java.lang.String;
import java.util.LinkedList;


public class OpenHashSet extends SimpleHashSet {

    /**this is a special class that wraps the linked list so we can use it in an array */
    private static class LinkedWrap {

        /**this is the linked list we are going to use*/
        private LinkedList<String> linkedList;

        /**the constructor of the class*/
        LinkedWrap(){
            this.linkedList = new LinkedList<String>();
        }
        LinkedWrap(LinkedList<String> linkedList){
            this.linkedList = linkedList;
        }
    }

    /**the temporary array we use in the way to the final object*/
    private LinkedWrap[] myArray;

    /** A default constructor. **/
    public OpenHashSet() {
        super();
        this.myArray = new LinkedWrap[this.currentSize];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16)
     * @param lowerLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.*/
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        this.myArray = new LinkedWrap[this.currentSize];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * @param data - .*/
    public OpenHashSet(String[] data) {
        super();
        this.myArray = new LinkedWrap[this.currentSize];
        loadData(data);
    }

    /**
     Description copied from interface: supplied.SimpleSet
     Add a specified element to the set if it's not already in it.
     @param newValue - New value to add to the set
     @returns False if newValue already exists in the set**/
    public boolean add(java.lang.String newValue){
        if (contains(newValue)){
            return false;
        }
        else {
            addToArray(this.myArray, newValue);
            caughtCapacity++;
            overLoad();
            return true;
        }
    }


    /**
     Description copied from interface: supplied.SimpleSet
     Look for a specified value in the set.
     @param  searchVal - Value to search for
     @return True if searchVal is found in the set*/
    public boolean contains(java.lang.String searchVal){
        if (this.myArray[clamp(searchVal.hashCode())] == null){
            return false;
        }
        if (this.myArray[clamp(searchVal.hashCode())].linkedList.contains(searchVal)){
            return true;
        }
        return false;
    }

    /**
     Description copied from interface: supplied.SimpleSet
     Remove the input element from the set.
     @param toDelete - Value to delete
     @return true iff toDelete is found and deleted
     * */
    public boolean delete(java.lang.String toDelete){
        if (!contains(toDelete)){
            return false;
        }
        else {
            this.myArray[clamp(toDelete.hashCode())].linkedList.remove(toDelete);
            this.caughtCapacity--;
            underLoad();
            return true;
        }
    }

    /**@return The number of elements currently in the set*/
    public int size(){
        return caughtCapacity;
    }

    /**
     * @implSpec capacity in class SimpleHashSet
     * @return The current capacity (number of cells) of the table.
     * */
    public int capacity(){
        return currentSize;
    }

    /**
     * this method adds objects for an simple array
     * @param arr - the array we are updating
     * @return - the array with the new object
     * */
    private LinkedWrap[] addToArray(LinkedWrap[] arr, java.lang.String newValue){
        int index = clamp(newValue.hashCode());
        if (arr[index] == null){
            LinkedList<String> list = new LinkedList<>();
            list.add(newValue);
            arr[index] = new LinkedWrap(list);
        }
        arr[clamp(newValue.hashCode())].linkedList.add(newValue);
        return arr;
    }

    /**checks if the set is overload. If is, resizes it*/
    private void overLoad(){
        if (this.caughtCapacity > this.currentSize*this.highestCapacity){
            LinkedWrap[] newArray = new LinkedWrap[this.currentSize*2];
            currentSize *= 2;
            replace(newArray);
        }
    }

    /**checks if the set is not full enough. If is, resizes it*/
    private void underLoad(){
        if (this.caughtCapacity < this.currentSize*this.lowestCapacity){
            LinkedWrap[] newArray = new LinkedWrap[this.currentSize/2];
            currentSize *= 0.5;
            replace(newArray);
        }
    }

    /**
     * this method replaces an array with a new one
     * @param newArray - the new array which will replace the old one
     * */
    private void replace(LinkedWrap[] newArray){
        for (int i = 0; i < this.myArray.length; i++) {
            if (myArray[i] == null){
                continue;
            }
            while (this.myArray[i].linkedList.size() > 0) {
                    addToArray(newArray, myArray[i].linkedList.pop());
                }
            }
        myArray = newArray;
    }
}

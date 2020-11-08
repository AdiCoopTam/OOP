import oop.ex3.spaceship.Item;
import java.util.Map;

/**
 * this class his a super class that gathers "Locker" and "LongTermStorage" together.
 *
 * */
public abstract class Storage {
    /**
     * this is the max capacity of the locker
     * */
    public int capacity;

    /**this is a 2D array that includes the information about the invevntory of the storage unit*/
    protected Map<String, Integer> inventory;

    /**this parameter follows the current caught space*/
    protected int caughtSpace;

    /**
     * this method tries to add items to the storage unit.
     *
     * @param item - the type on the items we want to store;
     * @param n - the number of the items we want to store.
     *
     * @return 0 if success;
     *         -1 if there is not enough storage for the items;
     **/
    public int addItem(Item item, int n){
        int totalSpace = item.getVolume()*n;
        if (getAvailableCapacity() < totalSpace){
            return outOfSpace(item, n);
        }
        else {
            successfulAdd(item, n, totalSpace);
            return 0;
        }
    }
    /**
     * this function takes care of the attempt ton add items when there is not enough space.
     *
     * @param item - the item we have tried to add
     * @param n - the amount of the items we have tried to add.
     * @return always -1.
     * */

    protected int outOfSpace(Item item, int n){
        System.out.println("Error: Your request cannot be completed at this time. Problem: no room for" +
                " " + n + " " +"items of type" + " " + item.getType());
        return -1;
    }

    /**
     * this function takes care of a successful item-adding.
     *
     * @param item - the item we are going to add;
     * @param n - the amount of the items we are going to add;
     * @param totalSpace - the total space the item will catch
     * @return always 0
     * */
    protected void successfulAdd(Item item, int n, int totalSpace){
        this.inventory.put(item.getType(), n + getItemCount(item.getType()));
        this.caughtSpace += totalSpace;
    }

    /**
     * this method returns the number of the items that are stored for a specific type
     *
     * @param type - we are interested of the number on the items of this specific type.
     *
     * @return the number of the items of the given type.
     *
     * */
    public int getItemCount (String type){
        if (typeExists(type)){
        return inventory.get(type);}
        else {return 0;}
    }

    /**
     * this method retures map of the inventory of the locker
     * @return Map object of all the information about the inventory of the locker.
     *  */
    public Map<String, Integer> getInventory(){
        return this.inventory;
    }

    /**
     * this is a simple "getter" function that returns the total capacity of the locker.
     * */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * this function calculates the available capacity.
     *
     * @return the available capacity.
     * */
    public int getAvailableCapacity(){
        return this.capacity - this.caughtSpace;
    }

    /**
     * this function tells if an item type is already in the storage unit.
     *
     * @param type the type of the item we want to check on.
     * @return true if it inside - false otherwise.
     *
     * */
    protected boolean typeExists(String type){
        if (this.inventory == null){
            return false;
        }
        else {
            return inventory.containsKey(type);
        }

    }

}

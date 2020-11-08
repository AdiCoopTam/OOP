import oop.ex3.spaceship.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the a locker that inside a spaceship.
 * */
public class Locker extends Storage{
    /**
     * this is this locker's long-term storage
     * */
    public LongTermStorage lts;

    /**
     * this array contains array of things that can't stay together in this locker
     * */
    public Item[][] contraintes;

    /**
     * this is the constructor of the class
     *
     * @param capacity - the capacity of the Locker (overwrite the 0);
     * @param lts - the long term storage locker;
     * @param constraints - the constraints on the items;
     * */
    public Locker(LongTermStorage lts, int capacity, Item[][] constraints){
        this.lts = lts;
        this.capacity = capacity;
        this.contraintes = constraints;
        this.inventory = new HashMap<>();
        this.caughtSpace = 0;
    }

    /**
     * this method tries to add items to the locker.
     *
     * @param item - the type on the items we want to store;
     * @param n - the number of the items we want to store.
     *
     * @return 0 if success;
     *         -1 if there is not enough storage for the items;
     *         -2 if fail because of constraint item;
     *         1 if had to move part of the items had to be moved th the long-term locker.
     * */
    public int addItem(Item item, int n){
        int totalSpace = item.getVolume() * n;
        double predictedStorage = spaceItemTakes(item) + totalSpace;
        if (getConstraint(item) != null){
            if (typeExists(getConstraint(item).getType())){
            return thereIsConstraint(item);}
        }
        if (totalSpace > this.getAvailableCapacity()){
            return outOfSpace(item, n);
        }
        if (predictedStorage >= 0.5 * this.capacity){
            System.out.println(spaceItemTakes(item) + totalSpace);
                return moveToLongTerm(item, n, totalSpace);
            }
        successfulAdd(item, n, totalSpace);
        return 0;
    }

    /**
     * this method tries to remove items from the locker.
     *
     * @param item - the item type we are trying to remove;
     * @param n the number of the item that we are trying to remove;
     *
     * @return 0 if success;
     *         -1 if fail
     * */
    public int removeItem(Item item, int n){
        if (n < 0 ){
            System.out.println("Error: Your request cannot be completed at this time. Problem: cannot" +
                    "remove a negative number of items of type" + " " + item.getType());
            return -1;
        }
        else if (n > this.getItemCount(item.getType())) {
            System.out.println("Error: Your request cannot be comple ed at this time. " +
                    "Problem: the locker does not contain" +
                    " " + n + " " + "items of type" + " " + item.getType());
            return -1;
        }
        else {
            this.inventory.put(item.getType(), this.inventory.get(item.getType())-n);
            caughtSpace -= n * item.getVolume();
        return 0;
    }}

    /**
     * this function finds the constraint for an item, if exists.
     *
     * @param item the item we are inspecting.
     * @return the constraint, if exists.
     **/
    private Item getConstraint(Item item){
        if (this.contraintes.length == 0){
            return null;
        }
        else {
            for (Item[] couple:this.contraintes){
                if (couple[0] == item){
                    return couple[1];
                }
                if (couple[1] == item) {
                    return couple[0];
                }
            }
        }
        return null;
    }

    /**
     * this is a function that calculates the space a single type catches.
     * @param item - the item we are investing
     * @return the space it currently takes
     * */
    private int spaceItemTakes(Item item) {
        return this.getItemCount(item.getType()) * item.getVolume();
    }

    /**
     * this function takes care of an item type that takes 50% percents of the space or more.
     * it all depends in the space left in the Long term storage available space.
     *
     * @param item - the item we are trying to add
     * @param n - the total amount of the items we are trying to add.
     * @return 1 if could move to long term storage - -1 if could'nt.
     *
     * */
    private int moveToLongTerm(Item item, int n, int totalSpace){
        int totalAmount = n + this.getItemCount(item.getType());
        if ((findTheAllowed(item, totalAmount) * item.getVolume() > lts.getAvailableCapacity())){
            return outOfSpace(item, findTheAllowed(item, n));
        }
        else {
            successfulAdd(item, n, totalSpace);
            removeItem(item, findTheAllowed(item, n));
            lts.addItem(item, n);
            System.out.println("Warning: Action successful, but has caused items to be moved to storage");
        return 1;
    }}

    /**
     * this function finds the allowed amount of this item's type to stay in the locker.
     *
     * @param item - the item we are inspecting
     * @param total - the amount of the items we are trying to add.
     * @return - the amount of the items that have to be moved.
     * */
    private int findTheAllowed(Item item, int total){
        int allowed = 0;
        while (allowed * item.getVolume() < 0.2 * this.capacity){
            ++allowed;
        }
        return total - allowed;
    }

    /**
     * this function takes care of the case that there is constraint to the item.
     *
     * @param item the item we attempt to add i
     * @return always -2
     * */
    private int thereIsConstraint(Item item){
            System.out.println("Error: Your request cannot be completed at this time. Problem: the " +
                    "locker cannot contain items of type" + " " + item.getType() + " " + "as it contains a " +
                    "contradicting item");
            return -2;}
}



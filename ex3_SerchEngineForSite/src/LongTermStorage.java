import oop.ex3.spaceship.Item;

import java.util.HashMap;

/**
 * this class implements the */
public class LongTermStorage extends Storage {
    /**
     * the constructor of the class
     * */
    public LongTermStorage(){
        /**
         * this is a constant that presents the capacity of the long term storage.
         * */
        this.capacity = 1000;
        this.inventory = new HashMap<>();
        this.caughtSpace = 0;
    }

    /**
     *this method reset the whole object storage.
     * */
    public void resetInventory(){
        this.inventory = new HashMap<>();
        this.caughtSpace = 0;


    }


}

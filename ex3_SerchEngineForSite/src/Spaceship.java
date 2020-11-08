import oop.ex3.spaceship.*;

import java.util.Map;
import java.util.stream.IntStream;

public class Spaceship {
    /**
     * this object implements spaceship the lockers are in.
     * */

    /**
     * the name of the spaceship
     * */
    public String name;

    /**
     * the privet id numbers of the crew that in the spaceship
     * */
    public int[] crewIDS;

    /**
     * the number of lockers that in the spaceship
     * */
    public int numOfLockers;

    /**
     * an array of all the lockers that are in the spaceship.
     * */
    private Locker[] lockers;

    /**
     * the long-term storage of the spaceship
     * */
    protected LongTermStorage lts;

    /**
     * the constructor of the spaceship.
     *
     * @param name - the name of this ship.
     * @param crewIDS - the id's of the crew that on the ship.
     * @param numOfLockers - the number of the lockers that are on this spaceship.
     *
     * */
    public Spaceship(String name, int[] crewIDS, int numOfLockers){
        this.name = name;
        this.crewIDS = crewIDS;
        this.numOfLockers = numOfLockers;
        this.lts = new LongTermStorage();
        this.lockers = new Locker[numOfLockers];
    }

    /**
     * gets the long term object of this spaceship
     * @return the long term storage of the spaceship.
     * */
    public LongTermStorage getLongTermStorage() {
        return lts;
    }

    /**
     * this method creates a locker for a given ID.
     *
     * @return 0 if created successfully;
     *         -1 if ID is not valid;
     *         -2 if the capacity is not valid (by Locker condition);
     *        -3 if the spaceship has reached to her locke's capacity limitation.
     * */
    public int createLocker(int crewID, int capacity){
        if (capacity < 0){
            return -2;
        }
        else if (IntStream.of(this.crewIDS).noneMatch(x -> x == crewID)){
            return -1;
        }
        else {
            for (int i=0; i<numOfLockers; i++){
                if (lockers[i] == null){
                    Item [][] constraints = new Item[][] {{new Item("football", 4),
                            new Item("Baseball bat", 1)}};
                    lockers[i] = new Locker(lts, capacity, constraints);
                    return 0;
                }
            }
            return -3;
    }}

    /**
     * this is a simple "getter" function
     *
     * @return this.crewIDs
     * */
    public int[] getCrewIDs(){
        return this.crewIDS;
    }

    /**
     * this is a simple "getter" function
     *
     * @return this.lockers
     * */
    public Locker[] getLockers(){
        return this.lockers;
    }
}

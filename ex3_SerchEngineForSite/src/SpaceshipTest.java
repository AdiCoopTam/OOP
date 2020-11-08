import static org.junit.Assert.*;
import oop.ex3.spaceship.*;

/**
 * this class has tests for the Spaceship class.
 * */
public class SpaceshipTest {
    /**
     * this are useful parameters that are used in the test.
     * */

     /** a regular spaceship*/
    private Spaceship regShip;

    /** a spaceship with no lockers to create*/
    private Spaceship noLockersShip;

    /** a spaceship that has an empty array in the "crewIDs" parameter*/
    private Spaceship noIDShip;

    protected SpaceshipTest(Spaceship regShip,
                            Spaceship noLockersShip,
                            Spaceship noIDShip){
        this.regShip = regShip; // no special characters
        this.noIDShip = noIDShip; // noIDShip.crewIDs = {}
        this.noLockersShip = noLockersShip; // noLocker.numOfLockers = 0

    }
    /**
     * this method tests if the createLocker method of "Spaceship" works as expected.:
     *
     * that it returns 0 if the locker has created successfully;
     * that it returns -1 if the ID is not valid;
     * that it returns -2 if the capacity is not valid (by Locker condition);
     * that it returns -3 if the spaceship has reached to her locke's capacity limitation.
     * */
    private void createLockerTester(){
        assertEquals(0, regShip.createLocker(1,6)); // created as usual
        assertEquals(0, regShip.createLocker(2,0)); // checking edge - 0 capacity
        assertEquals(-1, regShip.createLocker(7,6)); // there is not such id
        assertEquals(-1, noIDShip.createLocker(1,6)); // there is'nt any IDs
        assertEquals(-2, regShip.createLocker(3,-1)); // capacity is not allowed
        regShip.createLocker(3, 4);
        assertEquals(-3, regShip.createLocker(4,5)); // reached to lockers limitation
        assertEquals(-3, noLockersShip.createLocker(1,6)); // no space for lockers

    }
    public void allTheTests(){
        createLockerTester();
    }
}

import static org.junit.Assert.*;
import oop.ex3.spaceship.*;


/**
 * This class has the tests for "Locker" section
 * */
public class LockerTest {

    /** the Items group - the items that are used for the test*/
    private Item small; // size 1
    private Item cantBeWithMedium; // size 2, is a constraint with medium
    private Item medium; // size 6
    private Item big; // size 5
    private Item bigger; // size 7

    /** few Locker object for the tests*/
    private Locker regLocker; // no special characters
    private Locker zeroLocker; // zeroLocker.capacity = 0

    public LockerTest(Item small,
                      Item cantBeWithMedium,
                      Item medium,
                      Item big,
                      Item bigger,
                      Locker regLocker,
                      Locker zeroLocker){
        this.small = small;
        this.medium = medium;
        this.cantBeWithMedium = cantBeWithMedium;
        this.big = big;
        this.bigger = bigger;
        this.regLocker = regLocker;
        this.zeroLocker = zeroLocker;

    }
    /**
     * tests the addItem function -
     * if it success -
     * return 0, item have been added
     * if not success -
     *if no space: the right "error" message, return -1, no item has been added
     * if move to storage: the right "error" message, return 1, items have been added to long term storage
     * if no space even after move to storage - go to have no space
     * */
    private void addItemTester(){
        assertEquals(-1, zeroLocker.addItem(small, 1)); //no space for any items
        assertEquals(0, regLocker.addItem(medium, 1)); //add item as usual
        assertEquals(-2, regLocker.addItem(cantBeWithMedium, 1)); //can't stay with medium
        assertEquals(1, regLocker.addItem(medium, 1)); //moves medium to long term storage
        regLocker.addItem(bigger, 1);
        assertEquals(-1, regLocker.addItem(big, 1)); //out of available capacity
        assertEquals(-1, regLocker.addItem(medium, 3)); //not enough space even if moved to lts
    }

    /**
     * the removeItem function -
     * if it success -
     * return 0,
     * if n in negative -
     * return -1, print the right "error" message.
     * if not success -
     * return -1, print the right "error" message.
     * */
    private void removeItemTester(){
        regLocker.addItem(small, 2);
        assertEquals(0, regLocker.removeItem(small, 1)); //removed as usual
        assertEquals(-1, regLocker.removeItem(cantBeWithMedium, 1)); //no item like this in there
        assertEquals(-1, regLocker.removeItem(small, 3)); //not enough Item in this type to remove
    }
    public void allTheTests(){
        addItemTester();
        removeItemTester();
    }
}

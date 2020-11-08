import oop.ex3.spaceship.Item;
import static org.junit.Assert.*;

/**
 * this class has all the tests for long term storage class functionality
 * */

public class LongTermTest {

    /** this are different LongTermStorage objects, all are new in the beginning */
    private LongTermStorage lts1;
    private LongTermStorage lts2;
    private LongTermStorage lts3;

    /** this are different Items to use in the tests */
    private Item halfHuge; // size 500
    private Item bigger; // size 7

    public LongTermTest(LongTermStorage lts1,
                        LongTermStorage lts2,
                        LongTermStorage lts3,
                        Item halfHuge,
                        Item bigger){
        this.lts1 = lts1;
        this.lts2 = lts2;
        this.lts3 = lts3;
        this.bigger = bigger;
        this.halfHuge = halfHuge;

    }

    /**
     * this function tests the addItem function of LongTermStorage
     * */
    private void addItemTest(){
        assertEquals(0, lts1.addItem(bigger, 1)); // should work fine
        lts1.addItem(halfHuge, 1);
        assertEquals(-1, lts1.addItem(bigger, 1)); // not enough available space
        assertEquals(-1, lts1.addItem(bigger, 77)); // not enough space for everyone

    }

    /**
     * this function tests the resetInventory function of LongTermStorage
     */
    private void resetInventoryTest() {
        lts2.addItem(bigger, 5);
        lts3.addItem(halfHuge, 1);
        lts2.resetInventory();
        assertEquals(lts2.capacity, lts2.getAvailableCapacity()); // all of the capacity should be available
        assertEquals(lts3.capacity, lts2.getAvailableCapacity()); // all of the capacity should be available


    }

    /**
     * this function gathers all the tests
     * */
    public void allTheTests(){
        addItemTest();
        resetInventoryTest();
    }

}

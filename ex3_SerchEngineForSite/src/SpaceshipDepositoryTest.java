import oop.ex3.spaceship.Item;

/**
 * this class gathers up all the tests for all the test's classes.
 * */

public abstract class SpaceshipDepositoryTest {

    /**
     * different kinds of Items implements
     */
    private final static Item small = new Item("small", 1);
    private final static Item cantBeWithMedium = new Item("can't be with medium", 2);
    private final static Item medium = new Item("medium", 6);
    private final static Item big = new Item("big", 5);
    private final static Item bigger = new Item("bigger", 7);
    private final static Item halfHuge = new Item("Half Huge", 500);

    /**
     * different kinds of Locker tests
     */
    private final static Item[][] condition = {{cantBeWithMedium, medium}};
    private final static LongTermStorage lts = new LongTermStorage();
    private final static Locker regLocker = new Locker(lts, 15, condition);
    private final static Locker zeroLocker = new Locker(lts, 0, condition);

    /**
     * different kinds of LongTermStorage implements
     */
    private final static LongTermStorage lts1 = new LongTermStorage();
    private final static LongTermStorage lts2 = new LongTermStorage();
    private final static LongTermStorage lts3 = new LongTermStorage();

    /**
     * different kinds of Spaceships implements
     */
    private final static Spaceship regShip = new Spaceship("Regular", new int[]{1, 2, 3, 4}, 3);
    private final static Spaceship noLockersShip = new Spaceship("NoLockers", new int[]{1, 2, 3, 4}, 0);
    private final static Spaceship noIDShip = new Spaceship("noIDShip", new int[]{}, 3);

    /**
     * constructing the tests objects
     */
    private final static SpaceshipTest spaceshipTest = new SpaceshipTest(regShip, noLockersShip, noIDShip);
    private final static LockerTest lockerTest = new LockerTest(small, cantBeWithMedium, medium, big, bigger, regLocker,
            zeroLocker);
    private final static LongTermTest longTermTest = new LongTermTest(lts1, lts2, lts3, bigger, halfHuge);

    /**
     * this is the function that runs everything
     */
    protected static void checkEverything() {
        spaceshipTest.allTheTests();
        lockerTest.allTheTests();
        longTermTest.allTheTests();
    }
}
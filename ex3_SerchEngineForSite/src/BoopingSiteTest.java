import oop.ex3.searchengine.Hotel;
import static org.junit.Assert.*;


/**
 * this class has tests the the BoopingSite class.
 * we take in thought that every function will have a function that sorts him only.
 * It will be easier to check every sort by it's own, so that what we going to do.
 * NOTE - after implementation I had to form the tests to fit the function I wrote (Hotel [][] output
 * instead of hotel [] output)
 * */
public abstract class BoopingSiteTest {

    /**the objects that we will run the tests on*/
    static final private BoopingSite orgSite = new BoopingSite("hotels_tst1.txt"); // will nor be tested
    static final private BoopingSite emptySite = new BoopingSite("hotels_tst2.txt");
    static final private BoopingSite notOrgSite = new BoopingSite("hotels_dataset.txt");

    /**coordinations to use*/
    static final double latOrLogZero = 0;

    /**
     * this method checks if the POI sorting function works
     *
     * @param site - the site we inspect
     * @param sortedArray - the array the function sorted.
     * */
    public static void POITester(BoopingSite site, Hotel[][] sortedArray){
        amIEmpty(site, site.getHotelsArray().length);
        for (int i = 0; i < sortedArray.length-10 && sortedArray[i] != null && sortedArray[i+10] != null;
             i = i+10) {
            assertTrue(sortedArray[i][0].getNumPOI() >= sortedArray[i+10][0].getNumPOI());
        }
    }

    /**
     * little method that checks if an array is empty
     * @param site - the site we inspect
     * */
    private static void amIEmpty(BoopingSite site, int len){
        if (len == 0){
            assertSame(site.getHotelsArray().length, 0);
            return;
        }
    }

    /**
     * this method checks if the distance sorting function works
     *
     * @param site - the site we inspect
     * @param sortedArray - the array the function sorted.
     * */
    public static void disTester(BoopingSite site, Hotel[][] sortedArray){
        amIEmpty(site, site.getHotelsArray().length);
        for (int i = 0; i < sortedArray.length-10 && sortedArray[i] != null && sortedArray[i+10] != null;
             i = i+10) {
            double distance = site.calculateDistance(sortedArray[i][0], 0, 0);
            double distanceNext = site.calculateDistance(sortedArray[i+10][0], 0, 0);
            assertTrue(distance <= distanceNext);
        }
    }

    /**
     * this method checks if the rating sort function works
     *
     * @param site - the site we inspect
     * @param sortedArray - the array the function sorted.
     * */
    public static void rateTester(BoopingSite site, Hotel[][] sortedArray) {
        amIEmpty(site, site.getHotelsArray().length);
        for (int i = 0; i < sortedArray.length-10 && sortedArray[i] != null && sortedArray[i+10] != null;
             i = i+10) {
            assertTrue(sortedArray[i][0].getStarRating() <= sortedArray[i+10][0].getStarRating());
        }
    }

    /**
     * this method checks if the city filter function works
     *
     * @param site - the site we inspect
     * @param sortedArray - the array the function sorted.
     * */
    public static void cityTester(BoopingSite site, Hotel[] sortedArray, String city) {
        amIEmpty(site, site.getHotelsArray().length);
        for (int i = 0; i < sortedArray.length-10; i = i+10){
            assertEquals(sortedArray[i].getCity(), city);}
    }
    /**
     * this method checks if the alphaBeit function works.
     *
     * @param site - the site we inspect
     * @param sortedArray - the array the function sorted.
     * */
    public static void alphaBeitTester(BoopingSite site, Hotel[] sortedArray){
        int len = (int) Math.ceil(site.getHotelsArray().length * 0.1); //so the test will take a short time
        amIEmpty(site, len);
        for (int i = 0; i < sortedArray.length-10 && sortedArray[i] != null && sortedArray[i+10] != null;
             i = i+10){
            assertTrue(sortedArray[i].getPropertyName().compareTo(sortedArray[i+10].getPropertyName()) <= 0 );
        }
    }

    /**
     * this fucntion gathers up the tests for a specific site
     *
     * @param site - the site we are inspecting;
     * */
    private static void testSuite(BoopingSite site){
        alphaBeitTester(site,site.alphabetSort(site.alphabetSort(site.getHotelsArray())));
        POITester(site,site.sortPOI(site.getHotelsArray()));
        rateTester(site, site.starSort(site.getHotelsArray()));
        cityTester(site, site.cityFilter(site.getHotelsArray(), "goa"), "goa");
        disTester(site, site.disSort(site.getHotelsArray(), latOrLogZero, latOrLogZero));
    }
    /** here is where the test */
    public static void checkEverything() {
        testSuite(notOrgSite);
        testSuite(orgSite);
        testSuite(emptySite);
    }
    }


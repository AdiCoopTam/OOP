import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**this class is measuring the time it takes to run some methods on different types of data structures*/
public class SimpleSetPerformanceAnalyzer {

    /**constants to use*/
    static final private OpenHashSet openHashSet = new OpenHashSet();
    static final private ClosedHashSet closedHashSet = new ClosedHashSet();
    static final private CollectionFacadeSet treeSet = new CollectionFacadeSet(new TreeSet<>());
    static final private CollectionFacadeSet linkedList = new CollectionFacadeSet(new LinkedList<>());
    static final private CollectionFacadeSet hashSet = new CollectionFacadeSet(new HashSet<>());
    static final private SimpleSet[] setFinalArray =
            {openHashSet, closedHashSet, treeSet, hashSet, linkedList};
    static final private String[] data1 = Ex4Utils.file2array("C:\\Users\\Adi Hava Tamari\\OneDrive\\מסמכים\\שנה ב'\\OOP\\ex4\\src\\data1.txt");
    static final private String[] data2 = Ex4Utils.file2array("C:\\Users\\Adi Hava Tamari\\OneDrive\\מסמכים\\שנה ב'\\OOP\\ex4\\src\\data2.txt");

    /**an array of a SimpleSets we are analyzing*/
    private SimpleSet[] simpleSetArr;

    /**an array of Strings we will analyze as data fo*/
    private String[] data;

    /**name for the analyzer (to differ between data 1 and data 2)*/
    private String name;

    /**constructor for the class*/
    SimpleSetPerformanceAnalyzer(SimpleSet[] simpleSetArr, String[] data, String name) {
        this.simpleSetArr = simpleSetArr;
        this.data = data;
        this.name = name;
    }

    /**two different analyzers to use*/
    static final private SimpleSetPerformanceAnalyzer analyzerData1 =
            new SimpleSetPerformanceAnalyzer(setFinalArray, data1, "data1");

    static final private SimpleSetPerformanceAnalyzer analyzerData2 =
            new SimpleSetPerformanceAnalyzer(setFinalArray, data2, "data 2");

    /**
     * this method checks how long it takes to add an array of Strings to a SimpleSet object.
     *
     *@param j - the index of the SimpleSet object we are currently inspecting
     * @return a long that represtnts how ong it took in milliseconds
     * */
    private long addTimer(int j) {
        long start = System.nanoTime();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null){
                continue;
            }
            this.simpleSetArr[j].add(this.data[i]);
        }
        return (long) ((System.nanoTime() - start) * (Math.pow(10, -6)));
    }

    /**
     * this method checks how long it takes to find if a specific String is in the SimpleSet object.
     *
     *@param j - the index of the SimpleSet object we are currently inspecting
     * @return a long that represtnts how ong it took in milliseconds
     * */
    private long containTimer(int j, String str) {
        int i = 0;
        long sum = 0;
        int end = times(j);
        while (i < end){
            long start = System.nanoTime();
            this.simpleSetArr[j].contains(str);
            long addition = System.nanoTime() - start;
            sum += addition;
            i++;
        }
        return sum / end;
    }

    /**
     * times we souled re-run the function
     *
     * @param j - the index of the data structure we are inspecting
     * @return 7000 in 4 (linked list) 70000 otherwise
     * */
    private int times(int j){
        if (j == 4){
            return 7000;
        }
        return 70000;
    }

    /**this function runs the relevant function 70K times as described in the description */
    private static void runner() {
        int i = 0;
        for (int j = 0; j < 4; j++) {
            analyzerData1.addTimer(j);
            analyzerData2.addTimer(j);
        }
        while (i < 70000) {
            for (int j = 0; j < 4; j++) {
                analyzerData1.simpleSetArr[j].contains("hi");
                analyzerData1.simpleSetArr[j].contains("-13170890158");
                analyzerData2.simpleSetArr[j].contains("23");
                analyzerData2.simpleSetArr[j].contains("hi");
            }
            i++;
        }
    }

    /**method that prints the result of the containTimer function*/
    private void containPrinter(String str){
        for (int i  = 0; i<5; i++){
            System.out.println(i + " " + this.name + " - contains: " + str + " " + this.containTimer(i, str));
        }
        System.out.println("\n");
    }

    /**method that prints the result of the addTimer function*/
    private void addPrinter(){
        for (int i  = 0; i<5; i++){
            System.out.println(i + " " + this.name + " - add: " + this.addTimer(i));
        }
        System.out.println("\n");

    }

    public static void main(String[] args) {
        runner();
        analyzerData1.addPrinter();
        analyzerData2.addPrinter();

        analyzerData1.containPrinter("hi");
        analyzerData1.containPrinter("-13170890158");

        analyzerData2.containPrinter("23");
        analyzerData2.containPrinter("hi");
    }
}

package me.github.michaelra;
import java.util.*;

public class MainPerformance {

    /*
    Test performance of given classes
     */
    public static void main(String[] args) {
        /*
        Create object to store collections in and also defines number of test times (create multiple to get different numberOfTests in output together)
         */
        PerformanceUtil pUtil = new PerformanceUtil(false, 100000);

        /*
        Create and store Collection objects
         */
        ArrayList<Integer> a = new ArrayList<>();
        pUtil.storeCollection(a);
        HashSet<Integer> b = new HashSet<>();
        pUtil.storeCollection(b);
        LinkedList<Integer> c = new LinkedList<>();
        pUtil.storeCollection(c);

        /*
        Run tests and print the results
         */
        pUtil.runTestsAndPrintResult();
    }

}

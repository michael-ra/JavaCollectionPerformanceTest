package me.github.michaelra;

import java.util.*;

/**
 * PerformanceUtil contains management data-storage and methods to simply test multiple Collections
 * for add,remove,contains for a given amount of tests. Uses Performance class internally.
 */
public class PerformanceUtil {

    private static LinkedList<Collection<Integer>> collectionsToTest;
    private static HashMap<String, long[]> storagePerformances;
    private static boolean isDebug;
    private final int numberOfTests;

    /**
     * PerformanceUtil contains management data-storage and methods to simply test multiple Collections
     * for add,remove,contains for a given amount of tests. Uses Performance class internally.
     * @param isDebug weather this will run in debug mode. Just to test one collection, should be turned off
     * @param numberOfTests how many times this will perform add,contains,remove for each collection
     */
    public PerformanceUtil(boolean isDebug, int numberOfTests) {
        storagePerformances = new HashMap<>();
        collectionsToTest = new LinkedList<>();
        this.isDebug = isDebug;
        this.numberOfTests = numberOfTests;
    }

    /**
     * Runs tests on collections inside collectionsToTest and saves performance long[add,contains,remove] array with Collection class as key
     */
    public void runTestsOnStoredCollections() {
        String saveone = null;
        for(Collection<Integer> coll : collectionsToTest) {
            createPerformanceObjectAndStoreIt(coll, coll.getClass().getName());
            if(isDebug) {
                saveone = coll.getClass().getName();
            }
        }
        if(isDebug) {
            System.out.println(storagePerformances.toString());
            System.out.println(Arrays.toString(storagePerformances.get(saveone)));
        }
    }

    /**
     * @param e Stored collection, will be tested when running runTestsAndPrintResult method
     */
    public void storeCollection(Collection<Integer> e) {
        collectionsToTest.add(e);
    }

    /**
     * Created performance object for each collection and runs testPerformanceAndStore on each of it
     * @param e Collection to be used
     * @param nameOfCollectionTested Name of the collection, internally Class Name will be used
     */
    private void createPerformanceObjectAndStoreIt(Collection<Integer> e, String nameOfCollectionTested) {
        Performance performanceObject = new Performance(e, numberOfTests);
        testPerformanceAndStore(performanceObject, nameOfCollectionTested);
    }

    /**
     * @param e Performance object that can run tests
     * @param nameOfCollectionInPerformance Name of the collection, internally Class Name will be used
     */
    private void testPerformanceAndStore(Performance e, String nameOfCollectionInPerformance) {
        storagePerformances.put(nameOfCollectionInPerformance,runTestsOn(e));
    }

    /**
     * @param e Performance object that can run tests
     * @return  long[add,contains,remove] with ms timings for each
     */
    private long[] runTestsOn(Performance e) {
        return new long[]{
                e.testAdd(), e.testContains(), e.testRemove()
        };
    }

    /**
     * @return storagePerformances, containing tested collections with their long[add,contains,remove] performance ms array
     */
    public static HashMap<String, long[]> getStoragePerformances() {
        return storagePerformances;
    }

    /**
     * Returns the performances as row by row stored inside List object
     * @return rows with Lists of List with Strings
     */
    public List<List<String>> givePerformances() {
        HashMap<String, long[]> performanceResults = this.getStoragePerformances();
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("CollectionType", "ADD", "CONTAINS", "REMOVE");
        rows.add(headers);
        for ( String key : performanceResults.keySet() ) {
            rows.add(Arrays.asList(key.toString(), performanceResults.get(key)[0]+"ms", performanceResults.get(key)[1]+"ms", performanceResults.get(key)[2]+"ms"));
        }
        return rows;
    }

    /**
     * Runs tests and prints results for stored collection objects inside this PerformanceUtil object
     */
    public void runTestsAndPrintResult() {
        this.runTestsOnStoredCollections();
        List<List<String>> rows = this.givePerformances();
        System.out.println("#######################################################");
        System.out.println("                     TEST RESULTS");
        System.out.println("");
        System.out.println(TableUtil.rowsToTable(rows));
        System.out.println("This is the result for >> "+ numberOfTests + " << tests.");
        System.out.println("#######################################################");
    }
}

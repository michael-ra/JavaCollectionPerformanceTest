package me.github.michaelra;

import java.util.Collection;

/**
 * Performance class used to test add,contains,remove for given collection
 */
public class Performance {

    Collection<Integer> e;
    int numberOfTests;

    /**
     * Performance class used to test add,contains,remove for given collection
     * @param someCollection collection to test with add,contains,remove
     * @param numberOfTests number of times each collection operation tested
     */
    public Performance(Collection<Integer> someCollection, int numberOfTests) {
        e = someCollection;
        this.numberOfTests = numberOfTests;
    }

    /**
     * Tests add for collection stored inside this Performance object, numberOfTests times
     * @return time difference between start and end of this operation
     */
    public long testAdd()
    {
        long startTime = System.currentTimeMillis();
        for (int n=0; n<numberOfTests; n++) {
            e.add(n);
        }
        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }

    /**
     * Tests contains for collection stored inside this Performance object, numberOfTests times
     * @return time difference between start and end of this operation
     */
    public long testContains()
    {
        long startTime = System.currentTimeMillis();
        for (int n=0; n<numberOfTests; n++) {
            boolean contains = e.contains(n);
        }
        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }

    /**
     * Tests remove for collection stored inside this Performance object, numberOfTests times
     * @return time difference between start and end of this operation
     */
    public long testRemove()
    {
        long startTime = System.currentTimeMillis();
        for (int n=0; n<numberOfTests; n++) {
            e.remove(n);
        }
        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }

}

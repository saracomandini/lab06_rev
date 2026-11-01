package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int ELEMS = 100_000;
    private static final int READS = 1000;
    private static final int START = 1000;
    private static final int END = 2000;
    private static final long AFRICA_POPULATION = 1_110_635_000L;
    private static final long AMERICAS_POPULATION = 972_005_000L;
    private static final long ANTARCTICA_POPULATION = 0L;
    private static final long ASIA_POPULATION = 4_298_723_000L;
    private static final long EUROPE_POPULATION = 742_452_000L;
    private static final long OCEANIA_POPULATION = 38_304_000L;

    private UseListsAndMaps() {
    }

    /**
     * Program entry point.
     */
    public static void main() {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final ArrayList<Integer> array = new ArrayList<>();
        for(int i = START; i<= END; i++) {
            array.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final LinkedList<Integer> linklist = new LinkedList<>(array);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        var first = array.getFirst();
        var last =array.getLast();
        array.set(array.size() - array.size(), last);
        array.set(array.size() - 1, first);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for(var n : array) {
            IO.print(n + " ");
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        testPerformance(array);
        testPerformance(linklist);
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        testRead(array);
        testRead(linklist);
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> map = new HashMap<>();
        map.put("Africa", AFRICA_POPULATION);
        map.put("Americas", AMERICAS_POPULATION);
        map.put("Antarctica", ANTARCTICA_POPULATION);
        map.put("Asia", ASIA_POPULATION);
        map.put("Europe", EUROPE_POPULATION);
        map.put("Oceania", OCEANIA_POPULATION);
        /*
         * 8) Compute the population of the world
         */
        Long population = 0L;
        for(Long h : map.values()) {
            population += h;
        }
        IO.println(population);
    }

    private static void testPerformance(List<Integer> list) {
        long time = System.nanoTime();
        for (int i = 1; i <= ELEMS; i++) {
            list.set(0, i);
        }
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        IO.println("ms: " + millis);
    }

    private static void testRead(List<Integer> list) {
        long time = System.nanoTime();
        for (int i = 1; i <= READS; i++) {
            list.get(list.size() / 2);
        }
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        IO.println("ms: " + millis);

    }
}

package prog;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cj on 2017-03-15.
 */
public class OracleArraySort {


    public OracleArraySort() {

    }


    public void testOracleArraySort(float[] array) {
        System.out.println("Starting Arrays.sort....");


        long start = System.nanoTime();
        Arrays.sort(array);
        long time = System.nanoTime() - start;
        System.out.println("loopit: time = " + time / 1.0E9);

    }

    public void testOracleArrayParallelSort(float[] array) {


        long start = System.nanoTime();
        Arrays.parallelSort(array);
        long time = System.nanoTime() - start;
        System.out.println("loopit: time = " + time / 1.0E9);

    }

}

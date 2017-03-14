

import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by cj on 2017-03-14.
 */
public class QuickSort extends RecursiveTask<double[]> {

    double[] array;
    int low, high, threshold;
    int depth = 0;

    public QuickSort(double[] array, int threshold) {
        this(array,0,array.length-1,threshold);
    }

    public QuickSort(double[] array, int low, int high, int threshold,int depth) {
        this.array = array;
        this.low = low;
        this.high = high;
        this.threshold = threshold;
        this.depth = depth;
    }
    public QuickSort(double[] array, int low, int high, int threshold) {
        this.array = array;
        this.low = low;
        this.high = high;
        this.threshold = threshold;
    }

    @Override
    protected double[] compute() {

        if (low >= high){
            return array;
        }

        int pivot = partition(array, low, high);

        double[] left = Arrays.copyOfRange(array,0,pivot);
        double[] right = Arrays.copyOfRange(array,pivot,array.length);


//        System.out.println("\ndepth:" + depth);
        depth++;
        //vänster
        QuickSort q1 = new QuickSort(left,0, left.length-1, threshold,depth);

        //höger
        QuickSort q2 = new QuickSort(right,0,right.length-1, threshold,depth);

        double[] res1 ;
        double[] res2;
        if (array.length < threshold) {
            res1 = q1.compute();
            res2 = q2.compute();

        }else {
//            System.out.println("forking");
            q1.fork();
            res2 = q2.compute();
            res1 = q1.join();
        }

        return Utilities.arrayConcatenator(res1,res2);
    }


    private int partition(double[] array, int low, int high){
        double pivot = array[high];
        int pivotIndex = low;

        for (int j = low; j < high; j++) {
            if (array[j]<= pivot){
                swap(array, pivotIndex,j);
                pivotIndex++;
            }
        }
        swap(array, pivotIndex,  high);
        return pivotIndex;
    }
    private void swap(double[] array, int index1, int index2){
        double tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }


}

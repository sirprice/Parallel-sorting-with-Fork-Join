
package prog;
import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by cj on 2017-03-14.
 */
public class QuickSort extends RecursiveTask<float[]> {

    float[] array;
    int low, high, threshold;
    int depth = 0;

    public QuickSort(float[] array, int threshold) {
        this(array,0,array.length-1,threshold);
    }

    public QuickSort(float[] array, int low, int high, int threshold,int depth) {
        this.array = array;
        this.low = low;
        this.high = high;
        this.threshold = threshold;
        this.depth = depth;
    }
    public QuickSort(float[] array, int low, int high, int threshold) {
        this.array = array;
        this.low = low;
        this.high = high;
        this.threshold = threshold;
    }

    @Override
    protected float[] compute() {

        if (low >= high){
            return array;
        }

        int pivot = partition(array, low, high);

//        float[] left = Arrays.copyOfRange(array,0,pivot);
//        float[] right = Arrays.copyOfRange(array,pivot,array.length);


//        System.out.println("\ndepth:" + depth);
        depth++;
        //vänster
        QuickSort q1 = new QuickSort(array,0, pivot-1, threshold,depth);

        //höger
        QuickSort q2 = new QuickSort(array,pivot,array.length - 1, threshold,depth);

        float[] res1 ;
        float[] res2;
        if (array.length < threshold) {
            res1 = q1.compute();
            res2 = q2.compute();

        }else {
//            System.out.println("forking");
            q1.fork();
            res1 = q1.compute();
            res2 = q2.join();
        }
        System.arraycopy(res1,0,array,low, res1.length);
        System.arraycopy(res2,pivot,array,low + res1.length, res2.length);
        return array;
    }


    private int partition(float[] array, int low, int high){
        float pivot = array[high];
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
    private void swap(float[] array, int index1, int index2){
        float tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }


}

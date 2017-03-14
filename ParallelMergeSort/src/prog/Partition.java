package prog;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by o_0 on 2017-03-14.
 */
public class Partition extends RecursiveTask<double[]> {

    private double[] array;
    private int threshold = 1000;
    public Partition(double[] array, int threshold,int low,int high) {
        this.array = array;
        this.threshold = threshold;
    }


    public Partition(double[] array, int threshold) {
        this(array,threshold,0,array.length);
    }

    private void merge(double[] result,double[] res1, double[] res2) {
        int size = res1.length + res2.length;
//        double[] result = new double[size];
        int left = 0;
        int right = 0;
        int arrayIndex = 0;
        while (left < res1.length && right < res2.length ){
            if (res1[left] < res2[right]) {
                result[arrayIndex] = res1 [left];
                left++;
                arrayIndex++;
            }else {
                result[arrayIndex] = res2 [right];
                right++;
                arrayIndex++;
            }
        }
        while (left < res1.length){
            result[arrayIndex] = res1[left];
            arrayIndex++;
            left++;
        }
        while (right < res2.length){
            result[arrayIndex] = res2[right];
            arrayIndex++;
            right++;
        }

//        return result;
    }



    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected double[] compute() {
        if (array.length < 2) {
            return array;
        }
        int middle = array.length/2;
        double[] arr1 = Arrays.copyOfRange(array,0,middle);
        double[] arr2 = Arrays.copyOfRange(array,middle,array.length);

        Partition partition1 = new Partition(arr1, threshold);
        Partition partition2 = new Partition(arr2, threshold);

        double[] res2;
        double[] res1 ;
        if (array.length < threshold) {
            res1 = partition1.compute();
            res2 = partition2.compute();
        }else {
            partition1.fork();
            res2 = partition2.compute();
            res1 = partition1.join();
        }
        merge(array,res1,res2);
        return array;
    }
}

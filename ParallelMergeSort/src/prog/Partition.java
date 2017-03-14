package prog;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by o_0 on 2017-03-14.
 */
public class Partition extends RecursiveTask<float[]> {

    private float[] array;
    private int threshold = 1000;

    float[] arr1;
    float[] arr2;
    public Partition(float[] array, int threshold,int low,int high) {
        int middle = array.length/2;
        int rightSize = array.length - middle;
        this.array = array;
        this.threshold = threshold;
//        this.arr1 = new float[middle];
//        this.arr2 = new float[rightSize];
        this.arr1 = Arrays.copyOfRange(array,0,middle);
        this.arr2 = Arrays.copyOfRange(array,middle,array.length);
//        System.arraycopy(array,0,arr1,0,middle);
//        System.arraycopy(array,middle,arr2,0,rightSize);
    }


    public Partition(float[] array, int threshold) {
        this(array,threshold,0,array.length);
    }

    private void merge(float[] result,float[] res1, float[] res2) {
        int size = res1.length + res2.length;
//        float[] result = new float[size];
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
    protected float[] compute() {
        if (array.length < 2) {
            return array;
        }



//        float[] arr1 = Arrays.copyOfRange(array,0,middle);
//        float[] arr2 = Arrays.copyOfRange(array,middle,array.length);
//
//        = Arrays.copyOfRange(array,0,middle);
//= Arrays.copyOfRange(array,middle,array.length);

        Partition partition1 = new Partition(arr1, threshold);
        Partition partition2 = new Partition(arr2, threshold);


        if (array.length < threshold) {
            arr1 = partition1.compute();
            arr2 = partition2.compute();
        }else {
            partition1.fork();
            arr2 = partition2.compute();
            arr1 = partition1.join();
        }
        merge(array,arr1,arr2);
        return array;
    }
}

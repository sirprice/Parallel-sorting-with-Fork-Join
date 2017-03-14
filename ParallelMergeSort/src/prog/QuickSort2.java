package prog;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by o_0 on 2017-03-14.
 */
public class QuickSort2 extends RecursiveTask<float[]> {
//    float[] array;

    Cont array;

    int low, high, threshold;
    int depth = 0;

    public QuickSort2(float[] array, int threshold) {
        this(array,0,array.length,threshold);
    }

    public QuickSort2(float[] array, int low, int high, int threshold,int depth) {
        this.array = new Cont(array);
        this.low = low;
        this.high = high;
        this.threshold = threshold;
        this.depth = depth;
    }
    public QuickSort2(float[] array, int low, int high, int threshold) {
        this.array = new Cont(array);;
        this.low = low;
        this.high = high;
        this.threshold = threshold;
    }

    private int partition(int left, int right) {
        float value = array.array[right - 1];
        int pivotIdx = right - 1;
        for (int i = left; i < pivotIdx; i++) {
            if (array.array[i] <= value ) {
                swap(array.array,left,i);
                left++;
            }
        }

        swap(array.array,left ,pivotIdx);
//        left++;
        return left;
    }

    private void swap(float[] inout, int index1, int index2){
        float tmp = inout[index1];
        inout[index1] = inout[index2];
        inout[index2] = tmp;
    }

    public float[] normalQuick(int left,int right) {
        if (left >= right - 1) {
            return array.array;
        }
        int pivotIdx = partition(left,right);
        normalQuick(left,pivotIdx );
        normalQuick(pivotIdx ,right);
        return array.array;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected float[] compute() {
        if (high - 1 <= low) {
            return array.array;
        }
//        Utilities.printArray(array);
        if (high - low < threshold) {
            return normalQuick(low,high);
        }

        int pivotIdx = partition(low,high);
        if (pivotIdx >= high - 1) {
            return array.array;
        }
        int left = low;
        int right = high;
        depth++;
//        System.out.println(" before depth:" + depth);
        if (depth > 2000) {
            System.out.println("\n\nleft: " + left +" pivotIdx: " + pivotIdx +" right: " + right+"\n" );
        }
        QuickSort2 quick1 = new QuickSort2(array.getSubArray(left,pivotIdx), 0, pivotIdx - left , threshold,depth);
        QuickSort2 quick2 = new QuickSort2(array.getSubArray(pivotIdx, right), 0, right - pivotIdx , threshold,depth);
//        QuickSort2 quick2 = new QuickSort2(array.getSubArray(pivotIdx, right), pivotIdx, right , threshold);
        depth--;
//        System.out.println(" after depth:" + depth);
        quick1.fork();
        array.copyInto(quick2.compute(),pivotIdx,right);


//        float[] res1 = quick1.join();
//        Utilities.printArray(array);
//        System.out.println("left: " + left +" pivotIdx: " + pivotIdx +" right: " + right );
        array.copyInto(quick1.join(),left,pivotIdx);
//        System.arraycopy(res1,0,array,left,pivotIdx - left);
//        System.arraycopy(res2,0,array,pivotIdx,high - pivotIdx);
//        Utilities.printArray(array);
//        Utilities.printArray(res1);
//        Utilities.printArray(res2);

        return array.array;
    }
}

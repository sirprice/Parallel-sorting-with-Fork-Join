package prog;

import java.util.concurrent.RecursiveTask;

/**
 * Created by o_0 on 2017-03-15.
 */
public class QuickSort3 extends RecursiveTask {
    float[] data;
    int threshold;
    int left;
    int right;
    int depth;

    public QuickSort3(float[] array, int threshold) {
        this(array,threshold,0,array.length - 1,0);
    }

    public QuickSort3(float[] array, int threshold,int left,
            int right,int depth) {
        this.data = array;
        this.left = left;
        this.right = right;
        this.threshold = threshold;
        this.depth = depth;
    }

    private boolean validate(int low,int pivot,int high) {
        boolean check1 = true;
        boolean check2 = true;
        float pivotValue = data[pivot];
        int beg = high;
        for (int idx = low; idx < pivot + 1; idx++) {
            if (pivotValue < data[idx]) {
                System.out.println("validate left failed: pivot: " + pivotValue + " idx: " + idx + " v: " + data[idx]);
                beg = (beg > idx) ? idx : beg;
                check1 = false;
            }
        }
        if (!check1) {
            System.out.println("left Failed:");
            Utilities.printArray(data,beg,pivot,true);
            System.out.println();
        }

        int end = 0;

        for (int idx = pivot; idx < high + 1; idx++) {
            if (pivotValue > data[idx]) {
                System.out.println("\nvalidate right failed: pivot: " + pivotValue + " idx: " + idx + " v: " + data[idx]);
                end = (end < idx) ? idx : end;
                check2 = false;
            }
        }
        if (!check2) {
            System.out.println("right Failed:");
            Utilities.printArray(data,pivot,end,true);
            System.out.println();
        }
        return check1 || check2;
    }


    private int partition(int low,int high) {
        int choosePivot = (low + high)/2;
        float pivotValue = data[choosePivot];
        swap(data,choosePivot,high);
        int placementIdx = low;
        for (int idx = low; idx < high; idx++) {
            if (pivotValue > data[idx]) {
                swap(data,placementIdx,idx);
                placementIdx++;
            }
        }
        swap(data,placementIdx,high);
        return placementIdx;
    }


    private void swap(float[] inout, int index1, int index2){
        if (index1 == index2) {return;}
        float tmp = inout[index1];
        inout[index1] = inout[index2];
        inout[index2] = tmp;
    }

    public float[] normalQuick(float[] array,int low,int high) {
        this.data = array;

        if (low < high) {
//            if (high - low < 2) {
//                return data;
//            }

            depth++;
//            System.out.println(" \nenter normalQuick depth:" + depth);
//            System.out.println();

            if (high >= data.length) {
                System.out.println(" \n bounce depth:" + depth);
            }
            int pivotIdx = partition(low,high);

//            validate(low,pivotIdx,high);

//            System.out.print("idx: " + pivotIdx + " val: " + (int)data[pivotIdx] + " | -> ");
//            Utilities.printArray(array,low,high ,true);

//            if (pivotIdx == low || pivotIdx == high) {
//                pivotIdx = (low + high)/2;  // to reduce the stack recursion on sorted data
//            }
            if (depth > 500) {
                System.out.println("\n\ndepth: "+ depth+  " left: " + low +" pivotIdx: " + pivotIdx +" right: " + high+"\n" + "\t value L: " + data[left]+ "\t value P: " + data[pivotIdx]+ "\t value R: " + data[high]);
//                return data;
            }
            if (high - low < 2) {
                depth--;
                return data;
            }
//            System.out.println("\n------ left side (depth: "+ depth+")----");
            if (pivotIdx != low) {
                normalQuick(data,low,pivotIdx - 1 );
            }
//            System.out.println("\n------ right side (depth: "+ depth+")----");
            normalQuick(data,pivotIdx + 1 ,high);
            depth--;
//            System.out.println(" exit normalQuick depth:" + depth);
        }
        return data;
    }

    public float[] normalQuick(int low,int high) {
        return normalQuick(this.data,low,high);
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected float[] compute() {

//        System.out.println(" before depth:" + depth);
        if (right - left < threshold) {
            return normalQuick(left,right);
        }
        if (left < right) {
            depth++;
//            if (right - left < 1) {
//                depth--;
//                return data;
//            }
//            System.out.println(" enter compute depth:" + depth);

            int pivotIdx = partition(left,right);
//            if (pivotIdx == left) {
//                pivotIdx = (left + right)/2;  // to reduce the stack recursion on sorted data
//            }


//            if (pivotIdx == left) {
//                System.out.println("\n\nleft: " + left +" pivotIdx: " + pivotIdx +" right: " + right+"\n" );
//                return data;
//            }
            if (depth > 2000) {
//                System.out.println("\n\ndepth: "+ depth+  " left: " + low +" pivotIdx: " + pivotIdx +" right: " + high+"\n" + "\t value L: " + data[left]+ "\t value P: " + data[pivotIdx]+ "\t value R: " + data[high]);
                System.out.println("\ndepth: "+ depth+" left: " + left +" pivotIdx: " + pivotIdx +" right: " + right+" value: " + data[pivotIdx] );
            }
            if (right - left < 2) {
                depth--;
                return data;
            }
            if (pivotIdx == left) {
                QuickSort3 q2 = new QuickSort3(data, threshold, pivotIdx + 1, right, depth);
                q2.compute();

            }else {
                QuickSort3 q1 = new QuickSort3(data, threshold, left, pivotIdx - 1, depth);
                QuickSort3 q2 = new QuickSort3(data, threshold, pivotIdx + 1, right, depth);
                q1.fork();
                q2.compute();
                q1.join();
            }
            depth--;
//            System.out.println(" exit compute depth:" + depth);
//            normalQuick(data,left,pivotIdx - 1);
//            normalQuick(data,pivotIdx + 1,right);
        }
        return this.data;
    }
}

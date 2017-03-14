import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by o_0 on 2017-03-14.
 */
public class Partition extends RecursiveTask<int[]> {

    private int[] array;
    private static final int THRESHOLD  = 1000;
    public Partition(int[] array) {
        this.array = array;
    }

    private int[] merge(int[] res1,int[] res2) {
        int size = res1.length + res2.length;
        int[] result = new int[size];
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

//        System.out.println("\nresult: ");
//        Main.printlnArray(result);
//        System.out.println("\n---");
        return result;
    }



    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected int[] compute() {
        if (array.length < 2) {
            return array;
        }
        int middle = array.length/2;
        int[] arr1 = Arrays.copyOfRange(array,0,middle);
        int[] arr2 = Arrays.copyOfRange(array,middle,array.length);
//        System.out.println("\ncompute ---");
//        Main.printlnArray(array);
//        Main.printlnArray(arr1);
//        Main.printlnArray(arr2);

        Partition partition1 = new Partition(arr1);
        Partition partition2 = new Partition(arr2);

        int[] res2;
        int[] res1 ;
        if (array.length < THRESHOLD) {
            res1 = partition1.compute();
            res2 = partition2.compute();
        }else {
            partition1.fork();
            res1 = partition1.join();
            res2 = partition2.compute();
        }
        return merge(res1,res2);
    }
}

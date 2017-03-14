import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by cj on 2017-03-14.
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello World!");
        int[] randomNumbers = {20, 3, 2, 34, 9, 82, 14, 10, 8, 12};
        System.out.println("Init: ");
        printArray(randomNumbers);
        ForkJoinPool pool = new ForkJoinPool(2);
        Partition partition = new Partition(randomNumbers.clone());

        pool.invoke(partition);
        int[] result = partition.get();
//        Partition partition = new Partition(randomNumbers.clone());
//        int[] result = partition.compute();
        System.out.println("Init: ");
        printArray(randomNumbers);
        System.out.println("Result: ");
        printArray(result);

        boolean testPassed = TestCase.inOrder(result);
        System.out.printf("Test inorder:" + testPassed);

    }

    public static void printArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ", ");
        }
    }

    public static void printlnArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.print("\n");
    }
}
//    public static void main(String[] args) {
//        int[] randomNumbers = {1, 3, 2, 34, 9, 82, 14, 10, 8, 12};
//
//        mergeSort(randomNumbers);
//        System.out.println();
//        for (int i = 0; i < randomNumbers.length; i++) {
//            System.out.print(randomNumbers[i]+", ");
//        }
//
//    }
//
//    private static void mergeSort(int[] array){
//        if (array.length > 1){
//            int[] leftArray = Arrays.copyOfRange(array, 0, array.length/2);
//            int[] rightArray = Arrays.copyOfRange(array, leftArray.length, array.length);
//
//            mergeSort(leftArray);
//            mergeSort(rightArray);
//            merge(array, leftArray, rightArray);
//        }
//    }
//    private static void merge(int[] array, int[] leftArray, int[] rightArray){
//        int leftIndex = 0, rightIndex = 0, arrayIndex = 0;
//        while (leftIndex < leftArray.length && rightIndex < rightArray.length ){
//            if (leftArray[leftIndex] < rightArray[rightIndex]){
//                array[arrayIndex] = leftArray[leftIndex];
//                arrayIndex++;
//                leftIndex++;
//            }else if (rightArray[rightIndex] <= leftArray[leftIndex]){
//                array[arrayIndex] = rightArray[rightIndex];
//                arrayIndex++;
//                rightIndex++;
//            }
//        }
//        while (leftIndex < leftArray.length){
//            array[arrayIndex] = leftArray[leftIndex];
//            arrayIndex++;
//            leftIndex++;
//        }
//        while (rightIndex < rightArray.length){
//            array[arrayIndex] = rightArray[rightIndex];
//            arrayIndex++;
//            rightIndex++;
//        }
//    }

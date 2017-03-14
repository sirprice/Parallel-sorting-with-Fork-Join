import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by cj on 2017-03-14.
 */
public class Main {

    private static final int TESTCYCLES = 100;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello World!");
        double[] randomNumbers = new double[100000];// = {20, 3, 2, 34, 9, 82, 14, 10, 8, 12};
        randomizeArray(randomNumbers);
//        System.out.println("Init: ");
        //printArray(randomNumbers);
//        ForkJoinPool pool = new ForkJoinPool(2);
//        Partition partition = new Partition(randomNumbers.clone());
        ForkJoinPool wPool = new ForkJoinPool(1);
        Partition wormUpPartition = new Partition(randomNumbers, 100);

        System.out.println("\nStarting real test\n");

        for (int i = 1; i < 10; i++) {
            ForkJoinPool pool = new ForkJoinPool(i);
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("Worm Up Round, ForkPoolSize: " + i);
            System.gc();
            Thread.sleep(5000);
            long wormUpStart = System.nanoTime();
            wPool.invoke(wormUpPartition);
            double[] wormUpResult = wormUpPartition.get();

            long wElapsed = System.nanoTime() - wormUpStart;
            System.out.println("Time: " + wElapsed / 1.0E9 + " s,");

            System.out.println("Test with ForkPoolSize: " + i);
            for (int k = 1; k <= 10; k+=1) {
                int threshold = 1000 * k;
                System.out.println("Threshold: "+ threshold);

                long averageTime = loopit(pool,randomNumbers,threshold);

                System.out.println("Average sortingTime: " + averageTime / 1.0E9 + " s,\n");
            }

            pool.shutdown();

        }


//        System.out.println("Result: ");
//        printArray(result);

//        boolean testPassed = TestCase.inOrder(result);
//        System.out.printf("\nTest inorder:" + testPassed);

    }

    public static void printArray(double[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ", ");
        }
    }

    public static void printlnArray(double[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.print("\n");
    }

    public static void randomizeArray(double[] randomNumbers) {
        Random rand = new Random();
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = rand.nextDouble() * 100;
        }
    }
    public static long executeTest(ForkJoinPool pool, double[] randomNumbers, int threshold) throws ExecutionException, InterruptedException {
        Partition partition = new Partition(randomNumbers, threshold);
        long start = System.nanoTime();

        pool.invoke(partition);

        double[] result = partition.get();

        return System.nanoTime() - start;
    }

    static long loopit(ForkJoinPool pool, double[] randomNumbers, int threshold) throws ExecutionException, InterruptedException {
        long sumTime = 0;
        for (int j = 0; j < TESTCYCLES; j++) {
            if (j % (TESTCYCLES / 10) == 0) {
                System.out.println("Progress:" + j + "/" + TESTCYCLES);
            }

            long elapsed = executeTest(pool,randomNumbers,threshold);

            if (j > 0)
                sumTime += elapsed;

        }
        return sumTime / TESTCYCLES;
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

//        Random rand = new Random();
//        for (int i=0;i<randomNumbers.length;i++){
//            randomNumbers[i] = rand.nextInt(100)+1;
//        }
//        for (int i = 0; i < randomNumbers.length; i++) {
//            System.out.print(randomNumbers[i]+", ");
//        }

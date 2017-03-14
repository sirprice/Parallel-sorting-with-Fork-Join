import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by cj on 2017-03-14.
 */
public class Main {

//    private static final int TESTCYCLES = 100;
//
//    public static class TimingResult  {
//        public long avgTime;
//        public int threashold;
//
//        public TimingResult(long avgTime, int threashold) {
//            this.avgTime = avgTime;
//            this.threashold = threashold;
//        }
//    }
//
//    public static class TimingResultComperator implements Comparator<TimingResult> {
//
//        @Override
//        public int compare(TimingResult o1, TimingResult o2) {
//            long diff = o1.avgTime - o2.avgTime;
//            return (int) (diff== 0 ? 0 :  diff/Math.abs(diff));
//        }
//    }
//
//
//    public static TimingResult[] runThresholdCheck(ForkJoinPool poolThreshold,double[] randomNumbers,int threasholdBase ,int threasholdTestCount) throws ExecutionException, InterruptedException {
//        TimingResult[] threasholdAvrageResult = new TimingResult[threasholdTestCount];
//        System.out.println("Threashold test: ");
//        for (int i = 0; i < threasholdTestCount; i++) {
//            int threshold = threasholdBase + threasholdBase * i;
//            long averageTime = loopit(poolThreshold, randomNumbers, threshold, 2);
//            threasholdAvrageResult[i] = new TimingResult( averageTime,threshold);
//            System.out.print(".");
//        }
//
//        Arrays.sort(threasholdAvrageResult,new TimingResultComperator());
//
//        return threasholdAvrageResult;
//    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testDD();
        System.out.println("Hello World!");
        TestForkJoin.runTest(Partition::new);
//        double[] randomNumbers = new double[100000];
//        ForkJoinPool wPool = new ForkJoinPool(1);
//        Partition wormUpPartition = new Partition(randomNumbers, 100);
//        ForkJoinPool poolThreshold = new ForkJoinPool(1);
//
//        System.gc();
//        Thread.sleep(5000);
//        long wormUpStart = System.nanoTime();
//        wPool.invoke(wormUpPartition);
//        double[] wormUpResult = wormUpPartition.get();
//        System.out.println("TestCase result: " + TestCase.inOrder(wormUpResult));
//
//        System.out.println("\nStarting real test\n");
//
//        int threasholdBase = 100;
//        int threasholdTestCount = 100;
//
//        TimingResult[] threasholdAvrageResult = runThresholdCheck(poolThreshold,randomNumbers,threasholdBase,threasholdTestCount);
//        System.out.println("\n threashold avarage results: \n");
//        for (int i = 0; i < threasholdTestCount; i++) {
//            System.out.println("threashold: " + threasholdAvrageResult[i].threashold + " avgTime: " + threasholdAvrageResult[i].avgTime / 1.0E9 );
//        }
//
//        poolThreshold.shutdown();
//
//
//        System.gc();
//        Thread.sleep(5000);
//        // choose the fastest
//        int threshold = threasholdAvrageResult[0].threashold;
//        for (int i = 1; i < 10; i++) {
//            ForkJoinPool pool = new ForkJoinPool(i);
//            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
//
//            System.out.println("Test with ForkPoolSize: " + i);
//            long averageTime = loopit(pool, randomNumbers, threshold, TESTCYCLES);
//
//            System.out.println("Average sortingTime: " + averageTime / 1.0E9 + " s,\n");
//            pool.shutdown();
//
//        }


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

//    public static long executeTest(ForkJoinPool pool, double[] randomNumbers, int threshold) throws ExecutionException, InterruptedException {
//        Partition partition = new Partition(randomNumbers, threshold);
//        long start = System.nanoTime();
//
//        pool.invoke(partition);
//
//        double[] result = partition.get();
//
//        return System.nanoTime() - start;
//    }
//
//    static long loopit(ForkJoinPool pool, double[] randomNumbers, int threshold, int numberOfCycles) throws ExecutionException, InterruptedException {
//        long sumTime = 0;
//        int checkAt = (numberOfCycles / 10);
//        checkAt = checkAt <= 0 ? 1 : checkAt;
//        for (int j = 0; j < numberOfCycles; j++) {
//
//            long elapsed = executeTest(pool, randomNumbers, threshold);
//
//            if (j > 0)
//                sumTime += elapsed;
//
//        }
//        return sumTime / numberOfCycles;
//    }
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

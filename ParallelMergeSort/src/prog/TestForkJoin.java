package prog;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import prog.Utilities.*;

/**
 * Created by o_0 on 2017-03-14.
 */
public class TestForkJoin {
//    private static final int TESTCYCLES = 100;
    private SortAlgo sortAlgo;

    public TestForkJoin(SortAlgo sortAlgo) {
        this.sortAlgo = sortAlgo;
    }

    public TimingResult[] runThresholdCheck(ForkJoinPool poolThreshold, double[] randomNumbers, int threasholdBase , int threasholdTestCount) throws ExecutionException, InterruptedException {
        TimingResult[] threasholdAvrageResult = new TimingResult[threasholdTestCount];
        System.out.println("Threashold test: ");
        for (int i = 0; i < threasholdTestCount; i++) {
            int threshold = threasholdBase + threasholdBase * i;
            long averageTime = loopit(poolThreshold, randomNumbers, threshold, 2);
            threasholdAvrageResult[i] = new TimingResult( averageTime,threshold);
            System.out.print(".");
        }

        Arrays.sort(threasholdAvrageResult,new TimingResultComperator());

        return threasholdAvrageResult;
    }

    public long executeTest(ForkJoinPool pool, double[] randomNumbers, int threshold) throws ExecutionException, InterruptedException {
//        prog.Partition partition = new prog.Partition(randomNumbers, threshold);
        RecursiveTask<double[]> partition = sortAlgo.make(randomNumbers, threshold);
        long start = System.nanoTime();

        pool.invoke(partition);

        double[] result = partition.get();

        return System.nanoTime() - start;
    }

    long loopit(ForkJoinPool pool, double[] randomNumbers, int threshold, int numberOfCycles) throws ExecutionException, InterruptedException {
        long sumTime = 0;
        int checkAt = (numberOfCycles / 10);
        checkAt = checkAt <= 0 ? 1 : checkAt;
        for (int j = 0; j < numberOfCycles; j++) {

            long elapsed = executeTest(pool, randomNumbers, threshold);

            if (j > 0)
                sumTime += elapsed;

        }
        return sumTime / numberOfCycles;
    }

    public void warmUp(double[] randomNumbers,int threashold) throws InterruptedException, ExecutionException {
        ForkJoinPool wPool = new ForkJoinPool(1);
        RecursiveTask<double[]> wormUpPartition = sortAlgo.make(randomNumbers, threashold);
        System.gc();
        Thread.sleep(5000);
        long wormUpStart = System.nanoTime();
        wPool.invoke(wormUpPartition);
        double[] wormUpResult = wormUpPartition.get();
        wPool.shutdown();
        System.out.println("prog.TestCase result: " + TestCase.inOrder(wormUpResult));
    }


    public static void runTest(SortAlgo algo,int testCycles) throws InterruptedException, ExecutionException {
        TestForkJoin test = new TestForkJoin(algo);
        System.out.println("Hello World!");
        double[] randomNumbers = new double[1000];
        Utilities.randomizeArray(randomNumbers);
//        Utilities.printArray(randomNumbers);
        ForkJoinPool poolThreshold = new ForkJoinPool(1);
        test.warmUp(randomNumbers,1000);

        System.out.println("\nStarting real test\n");

        int threasholdBase = 100;
        int threasholdTestCount = 100;

        TimingResult[] threasholdAvrageResult = test.runThresholdCheck(poolThreshold,randomNumbers,threasholdBase,threasholdTestCount);
        System.out.println("\n threashold avarage results: \n");
        for (int i = 0; i < threasholdTestCount; i++) {
            System.out.println("threashold: " + threasholdAvrageResult[i].threashold + " avgTime: " + threasholdAvrageResult[i].avgTime / 1.0E9 );
        }

        poolThreshold.shutdown();


        System.gc();
        Thread.sleep(5000);
        // choose the fastest
        int threshold = threasholdAvrageResult[0].threashold;
        System.out.println("threashold: will be " + threasholdAvrageResult[0].threashold + " avgTime: " + threasholdAvrageResult[0].avgTime / 1.0E9 );
        for (int i = 1; i < 10; i++) {
            ForkJoinPool pool = new ForkJoinPool(i);
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");

            System.out.println("Test with ForkPoolSize: " + i);
            long averageTime = test.loopit(pool, randomNumbers, threshold, testCycles);

            System.out.println("Average sortingTime: " + averageTime / 1.0E9 + " s,\n");
            pool.shutdown();

        }
    }

}

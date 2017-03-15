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

    public TimingResult[] runThresholdCheck(ForkJoinPool poolThreshold, float[] randomNumbers, int threasholdBase, int threasholdTestCount) throws ExecutionException, InterruptedException {
        TimingResult[] threasholdAvrageResult = new TimingResult[threasholdTestCount];
        System.out.println("Threashold test: ");
        for (int i = 0; i < threasholdTestCount; i++) {
            int threshold = threasholdBase + threasholdBase * i;
            long averageTime = loopit(poolThreshold, randomNumbers, threshold, 2);
            threasholdAvrageResult[i] = new TimingResult(averageTime, threshold);
            System.out.print(".");
        }

        Arrays.sort(threasholdAvrageResult, new TimingResultComperator());

        return threasholdAvrageResult;
    }

    public long executeTest(ForkJoinPool pool, float[] randomNumbers, int threshold, boolean validateResult) throws ExecutionException, InterruptedException {
//        prog.Partition partition = new prog.Partition(randomNumbers, threshold);
        RecursiveTask<float[]> partition = sortAlgo.make(randomNumbers, threshold);
        long start = System.nanoTime();

        pool.invoke(partition);

        float[] result = partition.get();

        long time =  System.nanoTime() - start;
        if (validateResult) {
            System.out.println("loopit: time = " + time + " result check: " + TestCase.inOrder(result));
        }
        return time;
    }

    long loopit(ForkJoinPool pool, float[] randomNumbers, int threshold, int numberOfCycles) throws ExecutionException, InterruptedException {
        long sumTime = 0;
        System.out.println("---------------");
        for (int j = 0; j < numberOfCycles + 1; j++) {
            Utilities.randomizeArray(randomNumbers);

            long elapsed = executeTest(pool, randomNumbers, threshold,false);
            System.out.println("loopit: time = " + elapsed );
//            sumTime += elapsed;
            if (j > 0)
                sumTime += elapsed;

        }
        System.out.println("---------------");
        return sumTime / numberOfCycles;
    }

    public void warmUp(float[] randomNumbers, int threashold) throws InterruptedException, ExecutionException {
        ForkJoinPool wPool = new ForkJoinPool(2);
        RecursiveTask<float[]> wormUpPartition = sortAlgo.make(randomNumbers, threashold);
        System.gc();
        Thread.sleep(5000);
//        long wormUpStart = System.nanoTime();
        wPool.invoke(wormUpPartition);
        float[] wormUpResult = wormUpPartition.get();
        wPool.shutdown();
        System.out.println("prog.TestCase result: " + TestCase.inOrder(wormUpResult));
    }


    public static double[] runTest(SortAlgo algo, int testCycles, int numberOfvalues,int minProcesseCount,int maxProcesseCount) throws InterruptedException, ExecutionException
    {
        return runTest(algo,testCycles,numberOfvalues,minProcesseCount,maxProcesseCount,true,0);
    }
    public static double[] runTest(SortAlgo algo, int testCycles, int numberOfvalues,int minProcesseCount,int maxProcesseCount,boolean runThreashold,int mythreashold) throws InterruptedException, ExecutionException {
        TestForkJoin test = new TestForkJoin(algo);
        System.out.println("Hello World!");
        float[] randomNumbers = new float[numberOfvalues];
        float[] warmupNumb = new float[numberOfvalues];
        float[] threashNumb = new float[numberOfvalues/100];
        Utilities.randomizeArray(randomNumbers);
        Utilities.randomizeArray(warmupNumb);
//        Utilities.printArray(randomNumbers);
        test.warmUp(warmupNumb.clone(), 3000);
        warmupNumb = new float[1];

        System.out.println("\nStarting real test\n");
        int threshold = mythreashold;

        if (runThreashold) {
            ForkJoinPool poolThreshold = new ForkJoinPool(1);
            int threasholdBase = 100;
            int threasholdTestCount = 100;

            TimingResult[] threasholdAvrageResult = test.runThresholdCheck(poolThreshold, threashNumb, threasholdBase, threasholdTestCount);
            System.out.println("\n threashold avarage results: \n");
            for (int i = 0; i < threasholdTestCount; i++) {
                System.out.println("threashold:avgTime\t" + threasholdAvrageResult[i].threashold + "\t : \t" + threasholdAvrageResult[i].avgTime / 1.0E9);
            }
//
            poolThreshold.shutdown();
            threshold = threasholdAvrageResult[0].threashold;
            System.out.println("threashold: will be " + threasholdAvrageResult[0].threashold + " avgTime: " + threasholdAvrageResult[0].avgTime / 1.0E9);
        }else {
            System.out.println("threashold: will be " + threshold);
        }


//
//        System.gc();
//        Thread.sleep(5000);
        // choose the fastest
//        int threshold = 9100;//threasholdAvrageResult[0].threashold;
//        int threshold = 10000;//threasholdAvrageResult[0].threashold;
        double[] result = new double[10];
        for (int i = minProcesseCount; i < maxProcesseCount + 1; i++) {

            ForkJoinPool pool = new ForkJoinPool(i);
            System.gc();
            Thread.sleep(2000);

            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");

            System.out.println("Test with ForkPoolSize: " + i);
            long averageTime = test.loopit(pool, randomNumbers.clone(), threshold, testCycles);
                System.out.println("Average sortingTime: " + averageTime / 1.0E9 + " s,");
            result[i - 1] = averageTime / 1.0E9;

            pool.shutdown();

        }
        return result;
    }

}

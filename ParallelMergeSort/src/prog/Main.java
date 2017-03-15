package prog;

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
//    public static TimingResult[] runThresholdCheck(ForkJoinPool poolThreshold,float[] randomNumbers,int threasholdBase ,int threasholdTestCount) throws ExecutionException, InterruptedException {
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
        // 100000000
        double[] result = TestForkJoin.runTest(QuickSort2::new, 10, 10000000,2,5);

        for (int i = 0; i < 10; i++) {
            System.out.println("" + result[i]);
        }

////        TestForkJoin.runTest(QuickSort2::new,5);
////        TestForkJoin quick = new TestForkJoin(QuickSort2::new);
////        TestForkJoin quick = new TestForkJoin(Partition::new);
//        ForkJoinPool pool = new ForkJoinPool(8);

//        float[] randomNumbers = new float[100000000];
//        Utilities.randomizeArray(randomNumbers);
//
//        System.out.println();
//
//        System.out.println();
//        QuickSort2 quickSort2 = new QuickSort2(randomNumbers, 100);
//        long startTime = System.nanoTime();
//
//        float[] res = quickSort2.normalQuick(0,randomNumbers.length);
//        long endtime = System.nanoTime();
//
//        System.out.println("Average sortingTime: " + TestCase.inOrder(res));
//        System.out.println("Average sortingTime: " + (endtime - startTime) / 1.0E9 + " s,");
//
        System.out.println();
//        Utilities.printArray(res);


//        Utilities.printArray(floats);
//        long loopit = quick.loopit(pool, randomNumbers, 80, 2);
//        System.out.println("Average sortingTime: " + loopit / 1.0E9 + " s,\n");
//        pool.shutdown();

    }

}

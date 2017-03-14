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
        double[] result = TestForkJoin.runTest(Partition::new, 2, 100000000 - 1);

        for (int i = 0; i < 10; i++) {
            System.out.println("" + result[0]);
        }

//        TestForkJoin.runTest(QuickSort::new,10);
//        TestForkJoin quick = new TestForkJoin(Partition::new);
//        TestForkJoin quick = new TestForkJoin(Partition::new);
//        float[] randomNumbers = new float[10000000];
//        ForkJoinPool pool = new ForkJoinPool(8);
//        Utilities.randomizeArray(randomNumbers);
//        long loopit = quick.loopit(pool, randomNumbers, 8, 2);
//        System.out.println("Average sortingTime: " + loopit / 1.0E9 + " s,\n");
//        pool.shutdown();

    }

}

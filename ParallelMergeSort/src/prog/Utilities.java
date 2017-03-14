package prog;

import java.util.Comparator;
import java.util.Random;

/**
 * Created by cj on 2017-03-14.
 */
public class Utilities {
    public static float[] arrayConcatenator(float[] arr1,float[] arr2) {
        float[] arr = new float[arr1.length + arr2.length];
        int base = 0;

        for (int i = 0; i < arr1.length; i++) {
            arr[base + i] = arr1[i];
        }
        base = arr1.length;
        for (int i = 0; i < arr2.length; i++) {
            arr[base + i] = arr2[i];
        }

        return arr;
    }

    public static void arrayJoin(float[] arr,float[] arr1,float[] arr2) {
//        float[] arr = new float[arr1.length + arr2.length];

        int base = 0;

        for (int i = 0; i < arr1.length; i++) {
            arr[base + i] = arr1[i];
        }
        base = arr1.length;
        for (int i = 0; i < arr2.length; i++) {
            arr[base + i] = arr2[i];
        }
    }

    public static void printArray(float[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ", ");
        }
    }

    public static void printlnArray(float[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.print("\n");
    }

    public static void randomizeArray(float[] randomNumbers) {
        Random rand = new Random();
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = rand.nextFloat() * 100;
        }
    }
    public static class TimingResult  {
        public long avgTime;
        public int threashold;

        public TimingResult(long avgTime, int threashold) {
            this.avgTime = avgTime;
            this.threashold = threashold;
        }
    }

    public static class TimingResultComperator implements Comparator<TimingResult> {

        @Override
        public int compare(TimingResult o1, TimingResult o2) {
            long diff = o1.avgTime - o2.avgTime;
            return (int) (diff== 0 ? 0 :  diff/Math.abs(diff));
        }
    }
}


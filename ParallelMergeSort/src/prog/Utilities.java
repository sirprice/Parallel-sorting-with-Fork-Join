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
            System.out.print((int)numbers[i] + ", ");
        }
    }

    public static void printArray(float[] numbers,int from,int to,boolean mark) {
        int start = 0;
        int lenght = numbers.length;
        to = (numbers.length >= to) ? to : numbers.length;
        if (mark == false) {
            start = from;
            lenght = to;
        }
        for (int i = start; i < lenght; i++) {
            if (mark && i == from) {
                System.out.print(" [ ");
            }
            System.out.print((int)numbers[i] + ", ");
            if (mark && i == to) {
                System.out.print(" ] ");
            }
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
            randomNumbers[i] = rand.nextFloat() * 10000;
        }
    }

    public static void randomizeArray(float[] randomNumbers,int scalingFactor) {
        Random rand = new Random();
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = rand.nextFloat() * scalingFactor;
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


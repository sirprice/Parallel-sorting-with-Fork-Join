/**
 * Created by cj on 2017-03-14.
 */
public class Utilities {
    public static double[] dd(double[] arr1,double[] arr2) {
        double[] arr = new double[arr1.length + arr2.length];
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
}

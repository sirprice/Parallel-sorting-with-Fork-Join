package prog;

/**
 * Created by o_0 on 2017-03-14.
 */
public class TestCase {
    public static boolean inOrder(double[] arr) {
        double lastValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (lastValue > arr[i]) {
                return false;
            }
            lastValue = arr[i];
        }
        return true;
    }

}

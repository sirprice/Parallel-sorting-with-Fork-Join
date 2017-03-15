package prog;

/**
 * Created by o_0 on 2017-03-14.
 */
public class TestCase {
    public static boolean inOrder(float[] arr) {
        float lastValue = arr[0];
        boolean res = true;
        for (int i = 1; i < arr.length; i++) {
            float current = arr[i];
            if (lastValue > current) {
                System.out.println("TestCase fail at: " +i + " lastValue: " + lastValue + " current " +current );
//                return false;
                res = false;
//                continue;
            }
            lastValue = arr[i];
        }
        return res;
    }

}

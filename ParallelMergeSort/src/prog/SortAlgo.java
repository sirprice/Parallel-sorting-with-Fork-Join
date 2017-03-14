package prog;

import java.util.concurrent.RecursiveTask;

/**
 * Created by o_0 on 2017-03-14.
 */
public interface SortAlgo <T>{
    RecursiveTask<double[]> make(double[] array, int threshold);
}

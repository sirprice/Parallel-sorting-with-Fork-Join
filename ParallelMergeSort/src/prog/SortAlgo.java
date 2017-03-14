package prog;

import java.util.concurrent.RecursiveTask;

/**
 * Created by o_0 on 2017-03-14.
 */
public interface SortAlgo <T>{
    RecursiveTask<float[]> make(float[] array, int threshold);
}

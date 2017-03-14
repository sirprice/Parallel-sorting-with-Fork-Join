package prog;

import java.util.Arrays;

/**
 * Created by o_0 on 2017-03-15.
 */
public class Cont {
    float[] array;

    public Cont(int size) {
        this.array = new float[size];
    }

    public Cont(float[] array) {
        this.array = array;
    }

    public void copyInto( float[] data,int start,int end) {
        System.arraycopy(data,0,array,start,end -start);
    }

    public float[] getSubArray(int start,int end) {
        return Arrays.copyOfRange(array,start,end);
    }
}

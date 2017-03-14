import javax.xml.bind.SchemaOutputResolver;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by cj on 2017-03-14.
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello World!");
        double[] randomNumbers = new double[10000];
        randomizeArray(randomNumbers);
        System.out.println();
        //printlnArray(randomNumbers);

        QuickSort wormUpPartition = new QuickSort(randomNumbers,1000);
        ForkJoinPool pool = new ForkJoinPool(1);
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Worm Up Round, ForkPoolSize: " + 0);
        System.gc();
        Thread.sleep(5000);
        long wormUpStart = System.nanoTime();
        pool.invoke(wormUpPartition);
        double[] wormUpResult = wormUpPartition.get();

        long wElapsed = System.nanoTime() - wormUpStart;
        System.out.println("Time: " + wElapsed / 1.0E9 + " s,");
        System.out.println(TestCase.inOrder(wormUpResult));


    }


    public static void printlnArray(double[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print((int) numbers[i] + ", ");
        }
        System.out.print("\n");
    }

    public static void randomizeArray(double[] randomNumbers) {
        Random rand = new Random();
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = rand.nextDouble() * 100;
        }
    }

}


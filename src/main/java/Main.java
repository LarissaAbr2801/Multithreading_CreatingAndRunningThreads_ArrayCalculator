import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int start = 0;
        int end = 200_000_000;
        int[] array = new int[end];
        generatingIntegerArray(array);

        //однопоточный подсчет
        long startTimeForOne = System.currentTimeMillis();
        int resultSingleThread = sumArraysElements(array);
        long endTimeForOne = System.currentTimeMillis();
        System.out.println("Сумма элементов массива: " + resultSingleThread);
        System.out.println("Затраченное время (однопоточно):" + (endTimeForOne - startTimeForOne));

        //многопоточный подсчет
        ForkJoinPool pool = new ForkJoinPool();
        ArraySumTask task = new ArraySumTask(array, start, end);
        long startTimeForMulti = System.currentTimeMillis();
        int resultMultiThreads = pool.invoke(task);
        long endTimeForMulti = System.currentTimeMillis();
        System.out.println("Сумма элементов массива: " + resultMultiThreads);
        System.out.println("Затраченное время (многопоточно):" + (endTimeForMulti - startTimeForMulti));
    }

    public static void generatingIntegerArray(int[] array) {
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
    }

    public static int sumArraysElements(int[] array) {
        return Arrays.stream(array).sum();
    }
}

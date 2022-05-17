import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int start = 0;
        int end = 6998;
        int[] array = new int[end];
        generatingIntegerArray(array);

        //однопоточный подсчет
        long startTimeForOne = System.currentTimeMillis();
        System.out.println("Сумма элементов массива: " + sumArraysElements(array));
        long endTimeForOne = System.currentTimeMillis();
        System.out.println("Затраченное время (однопоточно):" + (endTimeForOne - startTimeForOne));

        //многопоточный подсчет
        long startTimeForMulti = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ArraySumTask task = new ArraySumTask(array, start, end);
        System.out.println("Сумма элементов массива: " + pool.invoke(task));
        long endTimeForMulti = System.currentTimeMillis();
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

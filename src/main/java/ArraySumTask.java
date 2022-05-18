import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Integer> {

    private final int[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 1000;

    public ArraySumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        final int diff = end - start;
        int result = 0;
        if (diff <= THRESHOLD) {
            for (int i = start; i<end; i++) {
                result += array[i];
            }
            return result;
        }
        return forkTasksAndGetResult();

    }

    public int forkTasksAndGetResult() {
        final int middle = (end + start) / 2;
        ArraySumTask task1 = new ArraySumTask(array, middle, end);
        ArraySumTask task2 = new ArraySumTask(array, start, middle);

        invokeAll(task1, task2);

        return task1.join() + task2.join();
    }
}

import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask {
    private int start;
    private int end;
    private int[] array;
    private int minSize;

    public ArraySumTask(int start, int end, int[] array, int minSize) {
        this.start = start;
        this.end = end;
        this.array = array;
        this.minSize = minSize;
    }

    @Override
    protected Integer compute() {
        final int diff = end - start;
        switch (diff) {
            case 0:
                return 0;
            case 1:
                return array[start];
            case 2:
                return array[start] + array[start + 1];
            default:
                return forkTasksAndGetResult();
        }
    }

    private int forkTasksAndGetResult() {
        if ((end - start) / 2 <= minSize)
            return sumSingleThread();
        else {
            final int middle = (end - start) / 2 + start;
            ArraySumTask task1 = new ArraySumTask(start, middle, array, minSize);
            ArraySumTask task2 = new ArraySumTask(middle, end, array, minSize);
            invokeAll(task1, task2);
            return (int) task1.join() + (int) task2.join();
        }
    }

    private int sumSingleThread() {
        int result = 0;
        for (int i = start; i < end; i++) {
            result += array[i];
        }
        return result;
    }
}

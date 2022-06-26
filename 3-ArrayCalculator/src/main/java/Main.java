import java.util.Random;

public class Main {
    public static void main(String[] args) {

        final int SIZE = 500_000_000;
        final int minSIZE = 100_000;

        int[] array = fillArrayRandom(SIZE);
        long timeStart = System.currentTimeMillis();
        int sum = sumSingleTread(array);
        long timeEnd = System.currentTimeMillis();
        System.out.println("Время в однопотоке = " + (timeEnd - timeStart));


        ArraySumTask task = new ArraySumTask(0, array.length, array, minSIZE);
        long timeStart1 = System.currentTimeMillis();
        int resultTask = task.compute();
        long timeEnd1 = System.currentTimeMillis();
        System.out.println("Время выполнения в многопотоке = " + (timeEnd1 - timeStart1));
    }

    private static int sumSingleTread(int[] array) {
        int sum = 0;
        for (int number : array) {
            sum += number;
        }
        return sum;
    }

    private static int[] fillArrayRandom(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = random.nextInt(10);
        return array;
    }
}

package test.list;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 大数组对象遍历
 *
 * @author shannon sun
 * @date 2019/5/26 20:43
 */
public class ListTest {
    private static int LIST_LENGTH = 1000000;
    //线程数量
    private static int THREAD_NUMBER = 1000;
    //每个线程读取的list个数
    private static int SLICE_LENGTH = LIST_LENGTH / THREAD_NUMBER;

    public static void main(String[] args) throws InterruptedException {
        ArrayList<MyObject> myObjectArrayList = new ArrayList<>(LIST_LENGTH);
        for (int i = 0; i < LIST_LENGTH; i++) {
            myObjectArrayList.add(new MyObject());
        }

        function_01(myObjectArrayList);
        function_02(myObjectArrayList);
        function_03(myObjectArrayList);

    }

    public static void function_01(ArrayList<MyObject> myObjectArrayList) {
        long start = System.currentTimeMillis();
        int numberEquals2 = 0;
        for (int i = 0; i < LIST_LENGTH; i++) {
            if (myObjectArrayList.get(i).type == 2) {
                numberEquals2++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("线程数量1，线性遍历，花费的时间:" + (end - start) + " milliseconds, " + "type等于2的个数有:" + numberEquals2);
    }

    public static void function_02(ArrayList<MyObject> myObjectArrayList) throws InterruptedException {
        THREAD_NUMBER = Runtime.getRuntime().availableProcessors() * 2;
        SLICE_LENGTH = LIST_LENGTH / THREAD_NUMBER;
        long start = System.currentTimeMillis();
        int[] result2 = new int[THREAD_NUMBER];
        ExecutorService pool3 = Executors.newFixedThreadPool(THREAD_NUMBER);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            final int threadNumber = i;
            pool3.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = threadNumber * SLICE_LENGTH; j < ((threadNumber + 1) * SLICE_LENGTH); j++) {
                        if (myObjectArrayList.get(j).type == 2) {
                            result2[threadNumber]++;
                        }
                    }
                }
            });
        }
        pool3.shutdown();
        pool3.awaitTermination(1, TimeUnit.DAYS);
        int numberEquals2 = 0;
        for (int i = 0; i < THREAD_NUMBER; i++) {
            numberEquals2 += result2[i];
        }
        long end = System.currentTimeMillis();
        System.out.println("线程数量:" + THREAD_NUMBER + "(CPU核心*2)花费的时间:" + (end - start) + " milliseconds, " + "type等于2的个数有:" + numberEquals2);
    }

    public static void function_03(ArrayList<MyObject> myObjectArrayList) throws InterruptedException {
        THREAD_NUMBER = Runtime.getRuntime().availableProcessors() - 1;
        SLICE_LENGTH = LIST_LENGTH / THREAD_NUMBER;
        long start = System.currentTimeMillis();
        int[] result2 = new int[THREAD_NUMBER];
        ExecutorService pool3 = Executors.newFixedThreadPool(THREAD_NUMBER);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            final int threadNumber = i;
            pool3.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = threadNumber * SLICE_LENGTH; j < ((threadNumber + 1) * SLICE_LENGTH); j++) {
                        if (myObjectArrayList.get(j).type == 2) {
                            result2[threadNumber]++;
                        }
                    }
                }
            });
        }
        pool3.shutdown();
        pool3.awaitTermination(1, TimeUnit.DAYS);
        int numberEquals2 = 0;
        for (int i = 0; i < THREAD_NUMBER; i++) {
            numberEquals2 += result2[i];
        }
        long end = System.currentTimeMillis();
        System.out.println("线程数量:" + THREAD_NUMBER + "(CPU核心 - 1)花费的时间:" + (end - start) + " milliseconds, " + "type等于2的个数有:" + numberEquals2);
    }

}

class MyObject {
    int type;

    MyObject() {
        type = new Random().nextInt(100);
    }
}

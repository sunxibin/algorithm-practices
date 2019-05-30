package test;

/**
 * @author sunxibin
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizedTest.staticFunction();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizedTest test = new SynchronizedTest();
                test.nomalFunction();
            }
        });

        /**
         * 但是两个方法的锁类型不同，调用的静态方法实际上是类对象在调用，即这两个方法产生的并不是同一个对象锁，因此不会互斥，会并发执行。
         */
        thread1.start();
        thread2.start();
    }

    public static synchronized void staticFunction() {
        System.out.println("我是静态方法");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void nomalFunction() {
        System.out.println("我是普通方法");
    }
}

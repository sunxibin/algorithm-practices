package test;

/**
 * @author sunxibin
 */
public class OddTest {
    public static void main(String[] args) {
//        modTest(0);
//        modTest(-1);
//        modTest(-2);
//        modTest(-3);
//        modTest(-4);
//        modTest(-5);
//        modTest(-6);
//        modTest(-7);
    }

    /**
     * 判断一个数是否奇数
     */
    public static boolean isOdd(int i) {
        return (i & 1) == 1;
//        return i >> 1 << 1 != i;
    }

    /**
     * 取模
     */
    public static void modTest(int i) {
        System.out.println(i + " --> " + i % 3);
    }
}

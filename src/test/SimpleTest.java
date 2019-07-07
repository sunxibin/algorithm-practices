package test;

/**
 * @author sunxibin
 * @date 2019/6/13 23:07
 */
public class SimpleTest {
    public static void main(String[] args) {
        function001();
    }

    /**
     * 测试for循环
     */
    public static void function001() {
        int i = 0 ;
        for (; i < 1; i++) {
            System.out.println("i = " + i);
        }
    }

    /**
     * 取余
     */
    public static void function_002() {
        int i = -1;
        System.out.println(i/2);
    }

    /**
     * 位运算
     */
    public static void function_003() {
        int a = -3;
        int b = a >> 1;
        int c = a >>> 1;
        System.out.println("b = " + b + "\n" + "c = " + c);
        System.out.println("2^32 - 1 = " + (1 << 32 - 1));
    }

}

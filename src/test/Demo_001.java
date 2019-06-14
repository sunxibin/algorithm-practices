package test;

/**
 * @author sunxibin
 */
public class Demo_001 {
    public static void main(String[] args) {
        function_001();
    }

    /**
     * 位运算
     */
    public static void function_001() {
        int a = -3;
        int b = a >> 1;
        int c = a >>> 1;
        System.out.println("b = " + b + "\n" + "c = " + c);
        System.out.println("2^32 - 1 = " + (1 << 32 - 1));
    }
}

package algorithm.mathematics.math;

/**
 * 计算一个数的根号
 *
 * @author sunxibin
 */
public class Gen {
    public static void main(String[] args) {
        System.out.println(gen(6));
        System.out.println(gen2(6));
    }

    /**
     * 二分法
     */
    public static double gen(int k) {
        double left = 0;
        double right = 2;
        double middle = (left+right)/2;
        while(String.valueOf(middle).length()<=k+1) {
            if(Math.pow(middle, 2)<=2) {
                left = middle;
            }
            if(Math.pow(middle, 2)>2) {
                right = middle;
            }
            middle = (left + right)/2;
        }
        return middle;
    }

    /**
     * 牛顿迭代法
     */
    public static double gen2(int k) {
        int count = 0;
        double result = 2;
        while(String.valueOf(result).length()<=k+1) {
            result = 0.5*(result+2/result);
            count++;
        }
        result = Double.parseDouble(String.valueOf(result).substring(0, k+2));
        return result;
    }

    /**
     * 调用Math.pow(a, b)方法
     */
    public static double gen3(int k, int n) {
        if(n < 1) {
            throw new RuntimeException("...");
        }
        return Math.pow(k, 1/n);
    }

}

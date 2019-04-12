package algorithm.mathematics.number;

/**
 * @author shannon sun
 * @date 2019/4/12 21:56
 */
public class number01 {

    public static void main(String[] args) {

    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * @param x
     * @return
     */
    public static int reverse(int x) {
        /**
         ret 保存旧的翻转中间值, temp 保存新的翻转过程中间值
         依次提取 x 的末位加入 temp, 如果发生溢出则通过temp/10
         无法得到上一轮的翻转结果 ret
         **/
        int ret = 0;
        while(x != 0) {
            int temp = ret*10 + x%10;
            if(temp / 10 != ret) {
                return 0;
            }
            ret = temp;
            x /= 10;
        }
        return ret;
    }
}

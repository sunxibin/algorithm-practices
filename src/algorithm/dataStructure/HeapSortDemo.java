package algorithm.dataStructure;

import java.util.Arrays;

/**
 * 堆排序
 * 参考：https://www.cnblogs.com/chengxiao/p/6129630.html
 *
 * @author sunxibin
 * @date 2019/7/7 14:05
 */
public class HeapSortDemo {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 堆排序
     */
    public static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从最后一个非叶子节点从下至上，从右至左调整结构
        }
        //调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            //将堆顶元素与末尾元素进行交换
            swap(arr, 0, j);
            //重新对堆顶元素进行调整
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 调整大顶堆（以i为根的子树构成的大顶堆）
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //取出当前元素（子树所描述的堆顶元素）
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果左子节点小于右子节点，k指向右子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不进行交换）
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    /**
     * 元素交换
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

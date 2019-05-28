package test.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author sunxibin
 * @date 2018/12/27 14:52
 */
public class ArrayListTest {

    public static void main(String[] args) {
        //testIterator();
        //testList();
        //testParallelStream();
        testOne();
    }

    /**
     * Java ConcurrentModificationException异常原因
     * 原因 : 对Vector、ArrayList在迭代的时候如果同时对其进行修改就会抛出java.util.ConcurrentModificationException异常
     * 解决 : 单线程的情况下可通过 Iterator.remove()解决
     */
    public static void testIterator() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            Integer a = iterator.next();
            if(a < 3) {
                iterator.remove();
            }
        }

        System.out.println(list);
    }


    /**
     * 测试list为null和为空时是否会报错
     * 结果 : 为null时报错，为空时正常
     */
    public static void testList() {
        List<String> list = null;
        for(String s : list) {
            s.getBytes();
        }
    }


    /**
     * 测试parallelStream
     */
    public static void testParallelStream() {
        List<String> stringList = new ArrayList<>();

        List<String> temp = new ArrayList<>();
        temp.add("0");
        temp.add("1");temp.add("2");temp.add("3");temp.add("4");temp.add("5");temp.add("6");temp.add("7");temp.add("8");temp.add("9");temp.add("10");
        temp.add("11");temp.add("12");temp.add("13");temp.add("14");temp.add("15");temp.add("16");temp.add("17");temp.add("18");temp.add("19");temp.add("20");

//        stringList = temp.parallelStream().map(t -> {
//            String s = new String(t);
//            return s;
//        }).collect(Collectors.toList());
        temp.parallelStream().map(t -> {
            String s = new String(t);
            stringList.add(s);
            return s;
        }).forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("temp : " + temp + "\n\r");
        System.out.println("stringList : " + stringList + "\n\r");
    }

    public static void testOne() {
        List<Integer> temp = new ArrayList<>();
        Integer cnt = 0;
        temp.add(cnt++);temp.add(cnt++);temp.add(cnt++);temp.add(cnt++);temp.add(cnt++);temp.add(cnt++);

        System.out.println(temp);
    }

}

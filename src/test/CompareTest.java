package test;

import java.util.TreeSet;

/**
 * @author sunxibin
 */
public class CompareTest {
    public static void main(String[] args) {
        comparePerson();
    }

    /**
     * 比较String
     */
    public static void compareString() {
        String s1 = "abc";
        String s2 = "abcd";
        String s3 = "acc";

        TreeSet set = new TreeSet();
        set.add(s1);
        set.add(s2);
        set.add(s3);

        System.out.println(set);
    }

    /**
     * 自定义对象比较
     */
    public static void comparePerson() {
        TreeSet set = new TreeSet();
        set.add(new Person("sun", 14));
        set.add(new Person("shannon", 15));
        set.add(new Person("shannon", 14));

        System.out.println(set);
    }
}

class Person implements Comparable{
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        return this.age - p.age == 0 ? this.name.compareTo(p.name) : this.age - p.age;
    }

    @Override
    public String toString() {
        return this.name + "-" + this.age;
    }
}
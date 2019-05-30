package test;

/**
 * ${DESCRIPTION}
 *
 * @author sunxibin
 * @date 2019/2/13 10:34
 */
public class ConstructorTest {
    public static void main(String[] args) {
        new B("new B");
    }
}

class A {
    public A() {
        System.out.println("A的无参构造函数");
    }

    public A(String message) {
        System.out.println("A的有参构造函数:" + message);
    }
}

class B extends A {
    public B() {
        System.out.println("B的无参构造函数");
    }

    public B(String message) {
        System.out.println("B的有参构造函数:" + message);
    }
}
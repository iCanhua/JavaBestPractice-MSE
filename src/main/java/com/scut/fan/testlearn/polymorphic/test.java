package com.scut.fan.testlearn.polymorphic;

/**
 * Created by fan on 2016/10/30.
 */

/**
 * 静态多分派和动态单分派
 *
 * 静态类型：Human man = new Man（）;     我们把上面代码中的“Human”称为变量的静态类型，后面的“Man”称为变量的实际类型
 * 编译器（不是虚拟机，因为如果是根据静态类型做出的判断，那么在编译期就确定了）在重载时是通过参数的静态类型而不是实际类型作为判定依据的。
 * 并且静态类型是编译期可知的，所以在编译阶段，Javac编译器就根据参数的静态类型决定使用哪个重载版本。这就是静态分派最典型的应用。
 * 静态分派过程。这时候选择目标方法的依据有两点：一是方法的接受者（即调用者）的静态类型是Father还是Child，
 * 二是方法参数类型是Eat还是Drink。因为是根据两个宗量进行选择，所以Java语言的静态分派属于多分派类型。
 *
 * 再来看运行阶段虚拟机的选择，即动态分派过程。由于编译期已经了确定了目标方法的参数类型（编译期根据参数的静态类型进行静态分派），
 * 因此唯一可以影响到虚拟机选择的因素只有此方法的接受者的实际类型是Father还是Child。因为只有一个宗量作为选择依据，
 * 所以Java语言的动态分派属于单分派类型。
 */
public class test {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        System.out.println(a1.show(b));
        System.out.println(a1.show(c));
        System.out.println(a1.show(d));
        System.out.println(a2.show(b));
        /**
         * A a2 = new B();
         * 栈中的引用变量是A，堆中的实例变量是B。
         * 将子类的实例，赋值给父类的引用。就是向上转型。
         * 向上转型，在运行时，会遗忘子类对象中与父类对象中不同的方法。也会覆盖与父类中相同的方法--重写。（方法名，参数都相同）
         * 所以a2,可以调用的方法就是，A中有的，但是B中没有的方法，和B中的重写A的方法。
         */
        System.out.println(a2.show(c));
        System.out.println(a2.show(d));
        System.out.println(b.show(b));
        System.out.println(b.show(c));
        /**
         * 总结：继承子类会拿到父类的方法，而重写，父类引用会记得子类重写的方法而遗忘其他方法
         */
        System.out.println(b.show(d));
    }
}

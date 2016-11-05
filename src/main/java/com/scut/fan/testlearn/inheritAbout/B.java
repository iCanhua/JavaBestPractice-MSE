package com.scut.fan.testlearn.inheritAbout;

/**
 * Created by FAN on 2016/10/27.
 */
public class B extends A{
    protected int canhuaAge;
    static String name ="fancanhua";
    static {
        System.out.println("执行了类B的静态代码快");
    }
    {

        System.out.println("执行了类B的非静态代码快");
    }

    B(){

        super(5);//父类无无参构造方法，所以要显式调用父类有参构造！否则编译不通过

        System.out.println("B被无参实例化了，但要写super构造方法必须放在第一行");
    }
    B(int age){
      this();
        canhuaAge=age;
    }

    @Override
    public B overRide(){
        System.out.println("B重写了A的overRide: 执行B overRide method");
        return null;
    }

    public static void main(String[] args) {
        A a=new B();
        B b=new B();
        String str=b.name;
        System.out.println("实例对象成员也会被赋初始值:"+b.canhuaAge);//实例对象成员也会被赋初始值
        int i=3;
        switch (i){
            case 1:i++;
            case 2:i++;
            case 3:i++;
            case 4:{i++;System.out.println("switch 语句没有break会遇到case后继续执行下面的所有语句");}
                default:i++;
        }
        System.out.println("switch实验结果："+i);

        System.out.println("重写实验结果："+a.overRide());

    }
}



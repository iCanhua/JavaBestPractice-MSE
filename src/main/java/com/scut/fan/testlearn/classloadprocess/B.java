package com.scut.fan.testlearn.classloadprocess;

/**
 * Created by FAN on 2016/10/27.
 */
public class B extends A{
    protected int canhuaAge;
    static String name ="fancanhua";
    static {
        System.out.println("类B的静态代码快");
    }
    {
        System.out.println("类B的非静态代码快");
    }

    B(){
        super(5);//父类无无参构造方法，所以要显式调用父类有参构造！否则编译不通过
        System.out.println("B被无参实例化了");
    }
    B(int age){
      this();
        canhuaAge=age;
    }

    public static void main(String[] args) {

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

    }
}



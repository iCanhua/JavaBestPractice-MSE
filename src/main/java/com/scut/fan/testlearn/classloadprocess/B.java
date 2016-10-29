package com.scut.fan.testlearn.classloadprocess;

/**
 * Created by FAN on 2016/10/27.
 */
public class B extends A{
    public int canhua;

    static {
        System.out.println("类B的静态代码快");
    }
    {
        System.out.println("类B的非静态代码快");
    }
    B(){
        System.out.println("B被实例化了");
    }
    public static void main(String[] args) {

        B b=new B();
        System.out.println("实例对象成员也会被赋初始值:"+b.canhua);//实例对象成员也会被赋初始值
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



package com.scut.fan.testlearn.inheritAbout;

/**
 * Created by FAN on 2016/10/27.
 */
public class A {
    protected String apple="apple";

    static {
        System.out.println("执行了类A的静态代码快");
    }
    {
        System.out.println("执行了类A的非静态代码块");
    }
    A(){
        System.out.println("A被无参实例化了");
    }
    A(int A){
        System.out.println("A被有参实例化了"+A);
    }

    public A overRide(){
        System.out.println("A method");
        return null;
    }
}



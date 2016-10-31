package com.scut.fan.testlearn.classloadprocess;

/**
 * Created by FAN on 2016/10/27.
 */
public class A {
    protected String apple="apple";

    static {
        System.out.println("类A的静态代码快");
    }
    {
        System.out.println("类A的非静态代码块");
    }
    A(){
        System.out.println("A被实例化了");
    }
    A(int A){
        System.out.println("A被实例化了"+A);
    }
}



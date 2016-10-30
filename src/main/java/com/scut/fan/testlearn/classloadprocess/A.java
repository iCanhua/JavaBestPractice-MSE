package com.scut.fan.testlearn.classloadprocess;

/**
 * Created by FAN on 2016/10/27.
 */
public class A {
    A(){
        System.out.println("a");
    }
    static {
        System.out.println("A");
    }
    {
        System.out.println("Ia");
    }

}



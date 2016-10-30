package com.scut.fan.testlearn.polymorphic;

/**
 * Created by fan on 2016/10/30.
 */
public class B extends A {
    public String show(B obj){
        return ("B and B");
    }
    public String show(A obj){
        return ("B and A");
    }
}

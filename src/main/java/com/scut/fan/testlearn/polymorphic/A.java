package com.scut.fan.testlearn.polymorphic;

/**
 * Created by fan on 2016/10/30.
 */
public class A {
    public String show(D obj){
        return ("A and D");
    }
    public String show(A obj){
        return ("A and A");
    }
}

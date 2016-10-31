package com.scut.fan.testlearn.listAssignable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FAN on 2016/10/31.
 */
public class TestListAssignable {
    public static void main(String[] args) {
        List<com.scut.fan.testlearn.polymorphic.A> a=new ArrayList<com.scut.fan.testlearn.polymorphic.A>();
        List list;
        list = a;   //A对，因为List就是List<?>，代表最大的范围，A只是其中的一个点，肯定被包含在内
        List<com.scut.fan.testlearn.polymorphic.B> b=new ArrayList<com.scut.fan.testlearn.polymorphic.B>();
        //a = b;      //B错，点之间不能相互赋值
        List<?> qm;
        List<Object> o=new ArrayList<Object>();
        qm = o;     //C对，List<?>代表最大的范围，List<Object>只是一个点，肯定被包含在内
        List<D> d=new ArrayList<D>();
        List<? extends B> downB;
        downB = d;  //D对，List<? extends B>代表小于等于B的范围，List<D>是一个点，在其中
        List<?extends A> downA;
        //a = downA;  //E错，范围不能赋值给点
        //a = o;      //F错，List<Object>只是一个点
        downA = downB;  //G对，小于等于A的范围包含小于等于B的范围，因为B本来就比A小，B时A的子类嘛
    }
}

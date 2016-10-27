package com.scut.fan.testlearn.classloadprocess;

/**
 * Created by FAN on 2016/10/27.
 */
public class B extends A{
    static {
        System.out.println("B");
    }
    {
        System.out.println("Ib");
    }
    B(){
        System.out.println("b");
    }
    public static void main(String[] args) {
        B b=new B();
        int i=3;
        switch (i){
            case 1:i++;
            case 2:i++;
            case 3:i++;
            case 4:{i++;System.out.println("run");}
                default:i++;
        }
        System.out.println(i);
    }
}



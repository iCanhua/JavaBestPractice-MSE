package com.scut.fan.testlearn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by FAN on 2016/7/21.
 * hashCode与equals的区别与联系
 */
public class TestHashCodeAddEquals {

    static class Student{
        protected int age;
        protected String name;

        /**
         * 用作测试的学生类，继承默认的Equals()和HashCode()
         */
        public Student() {
        }
        public Student(int age, String name) {
            super();
            this.age = age;
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public String getName() {
            return name;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     *  一、equals方法的作用
     *  默认情况（没有覆盖equals方法）下equals方法都是调用Object类的equals方法，而Object的equals方法主要用于判断对象的内存地址引用是不是同一个地址（是不是同一个对象）。
     *  要是类中覆盖了equals方法，那么就要根据具体的代码来确定equals方法的作用了，覆盖后一般都是通过对象的内容是否相等来判断对象是否相等。
     *  没有覆盖equals方法代码如下：
     */
    public static void  EqualsTest(){
        LinkedList<Student> list = new LinkedList<Student>();
        Set<Student> set = new HashSet<Student>();
        Student stu1  = new Student(3,"张三");
        Student stu2  = new Student(3,"张三");
        System.out.println("stu1 == stu2 : "+(stu1 == stu2));
        System.out.println("stu1.equals(stu2) : "+stu1.equals(stu2));
        list.add(stu1);
        list.add(stu2);
        System.out.println("list size:"+ list.size());

        set.add(stu1);
        set.add(stu2);
        System.out.println("set size:"+ set.size());
    }
    /*
    运行结果：
    stu1 == stu2 : false
    stu1.equals(stu2) : false
    list size:2
    set size:2
    结果分析：Student类没有覆盖equals方法，stu1调用equals方法实际上调用的是Object的equals方法。所以采用对象内存地址是否相等来判断对象是否相等。
    因为是两个新对象所以对象的内存地址不相等，所以stu1.equals(stu2) 是false。
     */



    /**
     * 我们覆盖一下equals方法（age和name属性），让Student类其通过判断对象的内容是否相等来确定对象是否相等。
     * 覆盖后的Student类：
     */
    static class Student_Equals extends Student{
        public Student_Equals() {
        }
        public Student_Equals(int age, String name) {
            super();
            this.age = age;
            this.name = name;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Student other = (Student) obj;
            if (age != other.age)
                return false;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }
    }
    public static void EqualsTest_Override(){
        LinkedList<Student_Equals> list = new LinkedList<Student_Equals>();
        Set<Student_Equals> set = new HashSet<Student_Equals>();
        Student_Equals stu1  = new Student_Equals(3,"张三");
        Student_Equals stu2  = new Student_Equals(3,"张三");
        System.out.println("stu1 == stu2 : "+(stu1 == stu2));
        System.out.println("stu1.equals(stu2) : "+stu1.equals(stu2));
        list.add(stu1);
        list.add(stu2);
        System.out.println("list size:"+ list.size());

        set.add(stu1);
        set.add(stu2);
        System.out.println("set size:"+ set.size());
    }
    /*
    运行结果：
    stu1 == stu2 : false
    stu1.equals(stu2) : true
    list size:2
    set size:2
    结果分析：因为Student两个对象的age和name属性相等，而且又是通过覆盖equals方法来判断的，所示stu1.equals(stu2) 为true。注意以上几次测试list和set的size都是2
     */

    /**
     * 通过以上的代码运行，我们知道equals方法已经生效。接下来我们在覆盖一下hashCode方法（通过age和name属性来生成hashcode）并不覆盖equals方法，其中Hash码是通过age和name生成的。
     * 覆盖hashcode后的Student类：
     */
    static class Student_HashCode extends Student{
        public Student_HashCode() {
        }
        public Student_HashCode(int age, String name) {
            super();
            this.age = age;
            this.name = name;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + age;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }
    }
    public static void HashCodeTeat_Override(){
        LinkedList<Student_HashCode> list = new LinkedList<Student_HashCode>();
        Set<Student_HashCode> set = new HashSet<Student_HashCode>();
        Student_HashCode stu1  = new Student_HashCode(3,"张三");
        Student_HashCode stu2  = new Student_HashCode(3,"张三");
        System.out.println("stu1 == stu2 : "+(stu1 == stu2));
        System.out.println("stu1.equals(stu2) : "+stu1.equals(stu2));
        list.add(stu1);
        list.add(stu2);
        System.out.println("list size:"+ list.size());
        System.out.println("stu1HashCode:"+stu1.hashCode());
        System.out.println("stu2HashCode:"+stu2.hashCode());

        set.add(stu1);
        set.add(stu2);
        System.out.println("set size:"+ set.size());
    }
    /*
    运行结果：
    stu1 == stu2 : false
    stu1.equals(stu2) : false
    list size:2
    hashCode :775943
    hashCode :775943
    set size:2
    结果分析：我们并没有覆盖equals方法只覆盖了hashCode方法，两个对象虽然hashCode一样，但在将stu1和stu2放入set集合时由于equals方法比较的两个对象是false，
    所以就没有在比较两个对象的hashcode值。
     */

    /**
     * 我们覆盖一下equals方法和hashCode方法
     */
    static class Student_Equals_HashCode extends Student_Equals{
        public Student_Equals_HashCode() {
        }
        public Student_Equals_HashCode(int age, String name) {
            super();
            this.age = age;
            this.name = name;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + age;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }
    }

    public static void EqualsAddHashCodeTeat_Override(){
        LinkedList<Student_Equals_HashCode> list = new LinkedList<Student_Equals_HashCode>();
        Set<Student_Equals_HashCode> set = new HashSet<Student_Equals_HashCode>();
        Student_Equals_HashCode stu1  = new Student_Equals_HashCode(3,"张三");
        Student_Equals_HashCode stu2  = new Student_Equals_HashCode(3,"张三");
        System.out.println("stu1 == stu2 : "+(stu1 == stu2));
        System.out.println("stu1.equals(stu2) : "+stu1.equals(stu2));
        list.add(stu1);
        list.add(stu2);
        System.out.println("list size:"+ list.size());
        System.out.println("stu1HashCode:"+stu1.hashCode());
        System.out.println("stu2HashCode:"+stu2.hashCode());

        set.add(stu1);
        set.add(stu2);
        System.out.println("set size:"+ set.size());
    }
    /*
    运行结果：
    stu1 == stu2 : false
    stu1.equals(stu2) :true
    list size:2
    hashCode :775943
    hashCode :775943
    set size:1
    结果分析：stu1和stu2通过equals方法比较相等，而且返回的hashCode值一样，所以放入set集合中时只放入了一个对象。
     */


    /**
     * 总结：
     * 1、equals方法用于比较对象的内容是否相等（覆盖以后）
     * 2、hashcode方法只有在集合中用到
     * 3、当覆盖了equals方法时，比较对象是否相等将通过覆盖后的equals方法进行比较（判断对象的内容是否相等）。
     * 4、将对象放入到集合中时，首先判断要放入对象的hashcode值与集合中的任意一个元素的hashcode值是否相等，如果不相等直接将该对象放入集合中。
     * 如果hashcode值相等，然后再通过equals方法判断要放入对象与集合中的任意一个对象是否相等，如果equals判断不相等，直接将该元素放入到集合中，否则不放入。
     */

    public static void main(String[] args) {
        EqualsTest();
        EqualsTest_Override();
        HashCodeTeat_Override();
        EqualsAddHashCodeTeat_Override();
    }





}

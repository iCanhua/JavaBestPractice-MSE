package com.scut.fan.bestpractice;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FAN on 2016/10/24.
 */
public class worsepractice {
    public static void main(String[] args) {
        stringlink();
        stringlink_best();
    }
    /**
     * 字符串链接误用
     */
    public static void stringlink(){
        String s="";
        for (int name = 0; name < 10; name++) {
            s+=","+name;
        }
        s=s.substring(1);
        System.out.println("字符串链接："+s);
    }
    /**
     * 最佳实践
     */
    public static void stringlink_best(){
        StringBuffer s=new StringBuffer();
        for (int i = 0; i < 10; i++) {
            if(0<s.length()) s.append(",");
            s.append(i);
        }
        System.out.println("字符串链接："+s);
    }
    /**
     * 错误的使用StringBuffer
     * 错在第三行，append char 比 appendString 性能要好
     */
    public static void useStrbuffer(){
        StringBuffer sb=new StringBuffer();
        sb.append("Name:canhua");
        sb.append(sb.toString()+'\n');
        sb.append("!");
        String str=sb.toString();
    }
    /**
     * 测试字符串相等性
     */
    public static void testEqual(){
        String name="fancanhua";
        if (name.compareTo("John") == 0)
        if (name == "John")
        if (name.equals("John"))
        if ("".equals(name)) {
        }
    }

    /**
     * 正确写法
     */
    public static void testEqual_best(){
        String name="fancanhua";
        if ("John".equals(name))
        if (name.length() == 0)
        if (name.isEmpty());
    }
    /**
     * 数字转为字符串
     */
    public static void numToSring(){
        Set set=new HashSet();
        String string="" + set.size();
        new Integer(set.size()).toString();
        //正确
        String.valueOf(set.size());
    }
    /**
     * 不必要的初始化
     */
    class A{
        private int count=0;
        private String nema=null;
        private boolean important =false;
    }
    /**
     * 正确写法，所以基本数据类型可以不初始化使用
     */
    class B {
        private int count;
        private String name;
        private boolean important;
    }
    /**
     * charCode一般需要指定,这样的代码不具有移植性，因为不同平台默认可能不同字符编码
     */
    public void charCode(byte[] byteArray,String file,InputStream inputStream,OutputStream outputStream) throws IOException {
        Reader r = new FileReader(file);
        Writer w = new FileWriter(file);
        Reader rr = new InputStreamReader(inputStream);
        Writer ww = new OutputStreamWriter(outputStream);
        String s = new String(byteArray); // byteArray is a byte[]
        byte[] a = file.getBytes();
    }
    public void charCode_best(byte[] byteArray,String file,InputStream inputStream,OutputStream outputStream) throws IOException {
        Reader r = new InputStreamReader(new FileInputStream(file), "ISO-8859-1");
        Writer w = new OutputStreamWriter(new FileOutputStream(file), "ISO-8859-1");
        Reader rr = new InputStreamReader(inputStream, "UTF-8");
        Writer ww = new OutputStreamWriter(outputStream, "UTF-8");
        String s = new String(byteArray, "ASCII");
        byte[] a = file.getBytes("ASCII");
    }

    /**
     * 未对数据流进行缓存
     */
    public void dataRead() throws IOException {
        InputStream in = new FileInputStream("path");
        int b;
        while ((b = in.read()) != -1) {

        }
    }
    /**
     * 上面的代码是一个byte一个byte的读取,
     * 导致频繁的本地JNI文件系统访问, 非常低效, 因为调用本地方法是非常耗时的. 最好用BufferedInputStream包装一下.
     * 曾经做过一个测试, 从/dev/zero下读取1MB, 大概花了1s, 而用BufferedInputStream包装之后只需要60ms,
     * 性能提高了94%! 这个也适用于output stream操作以及socket操作.
     */
    public void dataRead_best() throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream("path"));
        int b;
        while ((b = in.read()) != -1) {
        }
    }
    /**
     * Socket不指定超时时间
     * 这种情况在工作中已经碰到不止一次了. 个人经验一般超时不要超过20s. 这里有一个问题,
     * connect可以指定超时时间, 但是read无法指定超时时间. 但是可以设置阻塞(block)时间.
     */
    public void socketTimeOut(SocketAddress remote) throws IOException {
        Socket socket =new Socket();
        socket.connect(remote);
        InputStream in = socket.getInputStream();
        int i = in.read();
    }
    /**
     * 考虑自我保护能力，超时处理
     * @param remote
     * @throws IOException
     */
    public void socketTimeOut_best(SocketAddress remote) throws IOException {
        Socket socket = new Socket();
        socket.connect(remote, 20000); // fail after 20s
        InputStream in = socket.getInputStream();
        socket.setSoTimeout(15000);
        int i = in.read();
    }









}

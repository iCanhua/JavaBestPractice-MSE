package com.scut.fan.concurrent.dbcp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * 模拟一个数据驱动，使用动态代理返回Connection代理对象
 * Created by FAN on 2016/7/1.
 */
public class ConnectionDriver {
    /**
     * 链接对象connection的动态代理类的方法调用管理器，该管理器抽取动态生成代理类的class字节码时所有有关业务逻辑的二进制代码，由用户去实现！
     * 注意，代理类的所有方法调用都通过Handler的invoke方法
     */
    static class ConnectionHandler implements InvocationHandler{
        Connection connection;
        public ConnectionHandler(Connection con){
            connection=con;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")){
                //这里应该实现接口实现的调用
                connection.commit();
                TimeUnit.MILLISECONDS.sleep(100);
                return null;
            }else{
                System.out.println("代理开始调用方法");
                method.invoke(connection,null);
                System.out.println("代理调用结束");
                return null;
            }

        }
    }
    //        创建一个Connection代理，在commit时休眠100毫秒
    public  static final Connection createConnection(){
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),ConnectionImpl.class.getInterfaces(),new ConnectionHandler(new ConnectionImpl()));
    }
}

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
            }
            if (method.getName().equals("close")){
                connection.close();
            }
            return null;
        }
    }
    //        创建一个Connection代理，在commit时休眠100毫秒
    public  static final Connection createConnection(){
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),ConnectionImpl.class.getInterfaces(),new ConnectionHandler(new ConnectionImpl()));
    }
}

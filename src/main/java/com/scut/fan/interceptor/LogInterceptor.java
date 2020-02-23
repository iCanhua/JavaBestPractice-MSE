package com.scut.fan.interceptor;

public class LogInterceptor implements Interceptor {


  public Response intercept(TargetInvocation targetInvocation) {
    System.out.println("logging begin");
    Response response = targetInvocation.invoke();
    System.out.println("logging end");
    return response;
  }
}

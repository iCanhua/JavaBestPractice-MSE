package com.scut.fan.interceptor;

public class InterceptorDemo {
  public static void main(String[] args) {
    TargetInvocation targetInvocation = new TargetInvocation();
    targetInvocation.addInterceptor(new LogInterceptor());
    targetInvocation.addInterceptor(new AuditInterceptor());
    targetInvocation.setRequest(new Request());
    targetInvocation.addInterceptor(new LaodiInterceptor());
    targetInvocation.setTarget(request->{
      System.out.println("调用了一次target");
      return new Response();
    });
    targetInvocation.invoke();
  }
}

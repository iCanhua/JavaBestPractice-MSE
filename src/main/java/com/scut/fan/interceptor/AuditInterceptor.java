package com.scut.fan.interceptor;

public class AuditInterceptor implements Interceptor {
  public Response intercept(TargetInvocation targetInvocation) {
    if(targetInvocation.getTarget()==null){
      throw new RuntimeException("不存在Target");
    }
    System.out.println("Audit succeeded");
    return targetInvocation.invoke();
  }
}

package com.scut.fan.interceptor;

public class LaodiInterceptor implements Interceptor {
  @Override
  public Response intercept(TargetInvocation targetInvocation) {
    System.out.println("灿华打死老弟");
    Response response= targetInvocation.invoke();
    return response;
  }
}

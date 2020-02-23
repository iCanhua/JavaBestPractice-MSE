package com.scut.fan.interceptor;

public interface Interceptor {
  public Response intercept(TargetInvocation targetInvocation);
}

package com.scut.fan.interceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TargetInvocation {
  private List<Interceptor> interceptorList = new ArrayList<Interceptor>();
  private Iterator<Interceptor> interceptors;
  private Target target;
  private Request request;

  public Target getTarget() {
    return target;
  }

  public void setTarget(Target target) {
    this.target = target;
  }

  public Request getRequest() {
    return request;
  }

  public void setRequest(Request request) {
    this.request = request;
  }

  public Response invoke(){
    if (interceptors.hasNext()){
      Interceptor interceptor = interceptors.next();
      interceptor.intercept(this);
    }
    return target.execute(request);
  }

  public void addInterceptor(Interceptor interceptor){
    interceptorList.add(interceptor);
    interceptors = interceptorList.iterator();
  }

}

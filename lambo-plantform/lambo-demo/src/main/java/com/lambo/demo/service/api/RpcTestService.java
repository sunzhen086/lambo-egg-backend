package com.lambo.demo.service.api;


import java.util.Map;

public interface RpcTestService {

    public Map sayHello(String name);

    public void updateSometing(String name);
}

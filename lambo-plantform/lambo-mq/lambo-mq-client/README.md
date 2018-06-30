#MQ客户端

MQ客户端负责对MQ消息的推送和拉取，包含两个工具类：


### MQProducterUtil 消息推送工具

```java
//模拟一个订单
Order order = new Order();
order.setOrderId = "OrderID188";
//生成一个消息对象
Message message = MQProducterUtil.geneMessage("OrderCreate","OrderID188",JSON.toJSONString(order));
//发送到MQ
MQProducterUtil.send(message);
```



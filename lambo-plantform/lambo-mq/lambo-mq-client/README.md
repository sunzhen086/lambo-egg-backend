#MQ客户端

MQ客户端负责对MQ消息的推送和拉取，包含两个工具类：


### MQProducterUtil 消息生产者工具

```java
public Object sendMessage(@RequestParam(value = "data") String data){
    //当消息丢失时为了方便查找，请尽量使用数据的主键，这里仅为模拟
    String id = IdGenerate.nextId();
    //组织消息
    Message message = MQProducterUtil.geneMessage("TestTag",id,data);
    //发送
    MQProducterUtil.send(message);

    return new BaseResult(BaseResultConstant.SUCCESS,null);
}
```


### MQConsumerUtil 消息消费者工具

```java
public void ConsumeMessage(){
    logger.info("MQ消费者示例方法-------开始");
    DefaultMQPushConsumer consumer  = MQConsumerUtil.initComsumer();
    try{
        consumer.subscribe("LamboDemo", "TestTag");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                for (MessageExt message : messages) {
                    try{
                        String msg = new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.out.println(" Receive New Messages: " + msg);
                    }catch(Exception e){
                        logger.error("消息解码异常",e);
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }catch (Exception e){
        logger.error("消费消息异常",e);
    }
    logger.info("MQ消费者示例方法-------结束");
}
```
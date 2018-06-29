# ehcache+redis缓存

本缓存模块使用ehcache+redis实现了二级缓存，并与spring做了集成，支持注解缓存。
使用缓存的方法，调用时首先会从本地读缓存，有的话直接返回，没有的话会到redis里读缓存
如果redis里也没有则执行方法，同时将结果写入本地缓存和redis
如果reids里有，则写到本地缓存同时返回结果

### 使用说明

* @Cacheable
表明所修饰的方法是可以缓存的：当第一次调用这个方法时，它的结果会被缓存下来，在缓存的有效时间内，以后访问这个方法都直接返回缓存结果，不再执行方法中的代码段。 
这个注解可以用condition属性来设置条件，如果不满足条件，就不使用缓存能力，直接执行方法。 
可以使用key属性来指定key的生成规则。
@Cacheable 支持如下几个参数：
 > * value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name（具体见下边缓存名称的说明）, 指明将值缓存到哪个Cache中
 key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL，如果要引用参数值使用井号加参数名，如：#userId，
 
 > * 一般来说，我们的更新操作只需要刷新缓存中某一个值，所以定义缓存的key值的方式就很重要，最好是能够唯一，因为这样可以准确的清除掉特定的缓存，而不会影响到其它缓存值 ， 
 本例子中使用模块加功能再加ID组合成键的名称，如”demo-user-1”、”co-order-223123”等
 
 > * condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
 
```java

// 将缓存保存到名称为CacheForEver中，键为"demo-user-1"字符串加上userId值，如 'demo-user-1'
@Cacheable(value="CacheForEver", key="'demo-user-' + #userId")    
public User findById(String userId) {    
    return (User) new User("1", "mengdee");           
}    

// 将缓存保存进UserCache中，并当参数userId的长度小于12时才保存进缓存，默认使用参数值及类型作为缓存的key
// 保存缓存需要指定key，value， value的数据类型，不指定key默认和参数名一样如："1"
@Cacheable(value="UserCache", condition="#userId.length() < 12")    
public boolean isReserved(String userId) {    
    System.out.println("UserCache:"+userId);    
    return false;    
}

```

* @CachePut
与@Cacheable不同，@CachePut不仅会缓存方法的结果，还会执行方法的代码段。它支持的属性和用法都与@Cacheable一致。

* @CacheEvict
与@Cacheable功能相反，@CacheEvict表明所修饰的方法是用来删除失效或无用的缓存数据。
@CacheEvict 支持如下几个参数：
> * value：缓存位置名称，不能为空，同上
> * key：缓存的key，默认为空，同上
> * condition：触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL
> * allEntries：true表示清除value中的全部缓存，默认为false

### 缓存名称

系统内置了一系列的不同生命周期的缓存，根据实际情况选择：

* CacheForEver 永久缓存，除非应用重启或者通过@CachePut、@CacheEvict更新或删除
* Cache10Seconds 缓存十秒
* Cache60Seconds 缓存一分钟
* Cache600Seconds 缓存十分钟
* Cache6000Seconds 缓存一百分钟
* Cache30000Seconds 缓存八个半小时



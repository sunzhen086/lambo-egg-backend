# 数据字典模块

依赖lambo-dict-client组件,在组件的pom.xml文件添加如下配置：
```java
<dependency>
    <groupId>com.lambo</groupId>
    <artifactId>lambo-dict-client</artifactId>
    <version>1.0.0</version>
    <type>jar</type>
</dependency>
```
数据存在于lambo_dict中，根据dict_type分为两种（type=0 为普通的字典值；type=1 为sql类型，根据dict_sql查询字典值
### 示例参考：
```java
       /**
            *
            * @Title: getDictDataList
            * @Description: 获取字典List
            * @param:dictId
            * @return  List<Map<String, Object>>
            * @throws
            */
        List<Map<String,Object>> dictList=DictCacheUtil.getDictDataList("CO_STATUS");
        
        /**
             *
             * @Title: getDictMap
             * @Description: 获取字典map
             * @param:dictId
             * @return LinkedHashMap<String,String>
             * @throws
             */
         LinkedHashMap<String, String>  dictMap=DictCacheUtil.getDictMapJson("PLM_ITEM");
        
        /**
             *
             * @Title: getDictMapJson
             * @Description: 获取枚举map对应的json格式
             * @param dictId
             * @return String
             * @throws
             */
        String strDict=DictCacheUtil.getDictMapJson("CO_STATUS");
         /**
             *
             * @Title: getDictValue
             * @Description: 获取枚举值
             * @param：dictId
             * @param：key
             * @return String
             * @throws
             */
            String strDict=DictCacheUtil.getDictValue("CO_STATUS", "12") 
       
```
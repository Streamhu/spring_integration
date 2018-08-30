#### 目录
- 一、MVC框架<br>
[1.springMVC](#springMVC)
<a href="#springMVC"></a>
- 二、持久层框架<br>
[1.mybatis](#mybatis)<br>
[2.hibernate](#hibernate)
- 三、缓存<br>
[1.redis](#redis)<br>
[2.ehcache](#ehcache)<br>
- 四、定时任务<br>
[1.spring-task](#spring-task)<br>
[2.quartz](#quartz)<br>
- 五、校验框架<br>
[1.hibernate validator](#validator)<br>


### 一、MVC框架
#### <span id="springMVC">1.springMVC</span>
##### 1）引入相关jar包
```
<!-- spring -->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>4.2.5.RELEASE</version >
</dependency>

<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-web</artifactId>
  <version>4.2.5.RELEASE</version>
</dependency>

<!-- spring-mvc -->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-webmvc</artifactId>
  <version>4.2.5.RELEASE</version>
</dependency>	
```
#####  2）resource目录下新建spring.xml文件 
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xmlns:context="http://www.springframework.org/schema/context"
   xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd ">


</beans> 

```
##### 3）web.xml配置
```
<!-- 监听器启动spring容器 -->
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

<!-- springMVC -->
<servlet>
	<servlet-name>spring-mvc</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
	  	<param-value>classpath*:spring-mvc.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
	<servlet-name>spring-mvc</servlet-name>
	<url-pattern>/*</url-pattern>
</servlet-mapping> 	
```
#####  4）resource目录下新建spring-mvc.xml文件,新增如下配置

```
<!-- 自动扫描的包名 -->
<context:component-scan base-package="com.hh" ></context:component-scan>

<!-- 默认的注解映射的支持 -->
<mvc:annotation-driven />

<!-- 设置默认的静态资源处理器，不设置配了视图解析器会报404错误 -->
<mvc:default-servlet-handler/>

<!-- 配置视图解析器，项目中用不到也可以不配 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/html/" />
    <property name="suffix" value=".html" />
</bean>	
```
#####  5）新建测试类
```
@Controller
@RequestMapping(value="/springMVC")
public class SpringmvcController {

    @RequestMapping(value="/test")
    public String test(){
        System.out.println("springMVC配置成功");
        return "springMVC";
    }
}
```


<hr>

### 二、持久层框架
#### <span id="mybatis">1.mybatis</span>
##### 1）引入相关jar包
```
<!-- mybatis -->
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.3.0</version>
</dependency>
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-spring</artifactId>
  <version>1.2.3</version>
</dependency>
<!-- spring jdbc -->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-jdbc</artifactId>
  <version>4.2.5.RELEASE</version>
</dependency>
<!-- msyql的驱动 -->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>5.1.25</version>
</dependency>
```
##### 2）新建spring-mybatis.xml配置文件
```
<!-- 引入数据库配置参数文件jdbc.properties -->
<context:property-placeholder location="classpath:jdbc.properties" ignore-unresolvable="true"/>

<!-- 数据源，加载jdbc驱动参数 -->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="${jdbc.driverClassName}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>

<!-- mybatis文件配置,mapper.xml文件扫描 -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <!-- 自动扫描mapper.xml文件 -->
    <property name="mapperLocations" value="classpath:mapper/*.xml"></property>
    <!-- mybatis的config对象，一定要有 -->
    <property name="configLocation" value="classpath:mybatis-config.xml"></property>
</bean>

<!-- Mapper接口所在包名，Spring会自动查找其下的类 -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	<!-- 和映射文件关联的dao层 -->
    <property name="basePackage" value="com.hh.mybatis.dao" />
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
</bean>
```
##### 3）新建jdbc.properties
```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/hh?characterEncoding=utf-8
jdbc.username=root
jdbc.password=
```
##### 4）新建mybatis-config.xml 
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>


</configuration>
```
##### 5）spring.xml文件中新增	
```
<!-- 引入mybatis配置 -->
<import resource="classpath*:spring-mybatis.xml"/>
```
##### 6）新建mapper.xml文件示例
```
<!-- namespace是和dao接口关联的地方 -->
<mapper namespace="com.hh.mybatis.dao.UserDao">

    <!-- 定义好返回类型 -->
    <resultMap type="com.hh.mybatis.User" id="userResultMap" >
        <!-- 映射数据库的字段 -->
        <id property="id" column="id"></id>
        <result property="name" column="name"></result>
        <result property="address" column="address"></result>
        <result property="city" column="city"></result>
    </resultMap>

    <!-- 查找所有记录 -->
    <select id="selectAll" resultMap="userResultMap">
        select * from user
    </select>


</mapper>
	    	
```

<hr>

#### <span id="hibernate">2.hibernate</span>
##### 1)引入相关jar包	
```
<!-- hibernate核心包 -->
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-core</artifactId>
  <version>4.2.5.Final</version>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-orm</artifactId>
  <version>4.2.5.RELEASE</version>
</dependency>	
```
##### 2）新建spring-hibernate文件题
```
<!-- 引入数据库配置参数文件jdbc.properties -->
<context:property-placeholder location="classpath:jdbc.properties" ignore-unresolvable="true"/>

<!-- 数据源，加载jdbc驱动参数 -->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="${jdbc.driverClassName}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>

<bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean" lazy-init="false">
    <!-- 注入datasource，给sessionfactoryBean内setdatasource提供数据源 -->
    <property name="dataSource" ref="dataSource" />
    <property name="configLocation" value="classpath:hibernate.cfg.xml"></property>
    <!-- 加载实体类的映射文件位置及名称 -->
    <property name="mappingLocations" value="classpath:hibernate/*.hbm.xml"></property>
</bean>

<!-- 配置sessionFactory, 这个配合jpa， 在entity层加jpa注解配合使用，从而不需要映射文件了-->
<!--    <bean id="sessionFactory" class="org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
    &lt;!&ndash; hibernate自动扫描 实体类&ndash;&gt;
    <property name="packagesToScan">
        <list>
            <value>com.hh.hibernate</value>
        </list>
    </property>
    &lt;!&ndash; hibernate属性 &ndash;&gt;
    <property name="hibernateProperties">
        <value>
            hibernate.hbm2ddl.auto=${jdbc.hibernate.hbm2ddl.auto}
            hibernate.dialect=${jdbc.hibernate.dialect}
            hibernate.show_sql=${jdbc.hibernate.show_sql}
        </value>
    </property>
</bean>-->	  
```
##### 3）新建hibernate.cfg.xml文件
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- 这里也可以配数据库相关配置 -->
        <!-- 数据库方法配置，hibernate在运行的时候，会根据不同的方言生成符合当前数据库语法的sql -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

        <!-- 2. 其他相关配置 -->
        <!-- 2.1  显示hibernate在运行时候执行的sql语句 -->
        <property name="hibernate.show_sql">true</property>
        <!-- 2.2 格式化sql -->
        <property name="hibernate.format_sql">true</property>
        <!-- 2.3 自动建表 -->
        <property name="hibernate.hbm2ddl.auto">update</property>

    </session-factory>
</hibernate-configuration>
```
##### 4）spring.xml文件中新增	
```
<!-- 引入hibernate配置 -->
<import resource="classpath*:spring-hibernate.xml"/>
```
##### 5）新建示例hbm.xml文件
```
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<!-- Generated 2016-3-15 16:30:05 by Hibernate Tools 3.4.0.CR1 -->
<hibernate-mapping>
    <class name="com.hh.hibernate.User" table="user">
        <id name="id" type="java.lang.Integer">
            <column name="ID" />
            <generator class="native" />
        </id>
        <property name="name" type="java.lang.String">
            <column name="name" />
        </property>
        <property name="address" type="java.lang.String">
            <column name="address" />
        </property>
        <property name="city" type="java.lang.String">
            <column name="city" />
        </property>
    </class>
</hibernate-mapping>
```
##### 6) 测试类
```
@Repository
public class HibernateUserDao {

    @Resource
    SessionFactory sessionFactory;

    public List<User> getUserList(){
        Session session = sessionFactory.openSession();
        String hsql = "from User";
        Query query = session.createQuery(hsql);
        List<User> userList= query.list();
        return userList;
    }
}
```



<hr>

### 三、缓存
#### <span id="redis">1.redis(有3种方式，目前用的是redisTemplate)</span>
##### 1) 引入相关jar包
```
<!-- redis -->
<dependency>
  <groupId>redis.clients</groupId>
  <artifactId>jedis</artifactId>
  <version>2.9.0</version>
</dependency>
<dependency>
  <groupId>org.springframework.data</groupId>
  <artifactId>spring-data-redis</artifactId>
  <version>1.6.1.RELEASE</version>
</dependency>	
```
##### 2）新建spring-redis.xml 
```
<!-- 引入数据库配置参数文件jdbc.properties -->
<context:property-placeholder location="classpath:redis.properties" ignore-unresolvable="true"/>

<!-- redis 的配置信息 -->
<bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
    <!-- 最大连接数 -->
    <property name="maxTotal" value="${redis.pool.maxTotal}"></property>
    <!-- 最大空闲数：空闲链接数大于maxIdle时，将进行回收 -->
    <property name="maxIdle" value="${redis.pool.maxIdle}"/>
    <!-- 最大等待时间：单位ms -->
    <property name="maxWaitMillis" value="${redis.pool.maxWait}"></property>
    <!-- 逐出连接的最小空闲时间 ：单位ms -->
    <property name="minEvictableIdleTimeMillis" value="${redis.pool.minEvictableIdleTimeMillis}"></property>
    <!-- 每次逐出检查时 逐出的最大数目 -->
    <property name="numTestsPerEvictionRun" value="${redis.pool.numTestsPerEvictionRun}"></property>
    <!-- 逐出扫描的时间间隔(毫秒) -->
    <property name="timeBetweenEvictionRunsMillis" value="${redis.pool.timeBetweenEvictionRunsMillis}"></property>
</bean>

<!-- redis 服务器中心  -->
<bean id="jedisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory" destroy-method="destroy">
    <property name="poolConfig" ref="jedisPoolConfig"></property>
    <!-- Redis服务器地址 -->
    <property name="hostName" value="${redis.host}"></property>
    <!-- 服务端口 -->
    <property name="port" value="${redis.port}"></property>
    <!-- 授权密码 -->
    <property name="password" value=""></property>
    <!-- 超时时间：单位ms -->
    <property name="timeout" value="${redis.timeout}"></property>
    <!--启用用户线程池 -->
    <property name="usePool" value="true"></property>
</bean>

<!-- redis操作模板，面向对象的模板 -->
<bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate">
    <property name="connectionFactory" ref="jedisConnectionFactory" />
</bean>  
```
##### 3) 新建redis.properties
```
# redis 服务器 IP
redis.host=127.0.0.1
# redis 服务器端口
redis.port=6379
# 超时时间
redis.timeout=3000
#redis.password=123//没有密码就不用设置
# 连接池中最大连接数。高版本：maxTotal，低版本：maxActive
redis.pool.maxTotal=200
# 连接池中最大空闲的连接数，控制一个 pool 最多有多少个状态为 idle 的jedis实例
redis.pool.maxIdle=20
# 连接池中最少空闲的连接数
redis.pool.minIdle=5
# 当连接池资源耗尽时，调用者最大阻塞的时间，超时将抛出异常。单位，毫秒数;默认为-1.表示永不超时。高版本：maxWaitMillis，低版本：maxWait
# 当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
redis.pool.maxWait=15000
# 连接空闲的最小时间，达到此值后空闲连接将可能会被移除。负值(-1)表示不移除
redis.pool.minEvictableIdleTimeMillis=30000
# 对于“空闲链接”检测线程而言，每次检测的链接资源的个数。默认为3
redis.pool.numTestsPerEvictionRun=3
#“空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1
redis.pool.timeBetweenEvictionRunsMillis=60000
# 向调用者输出“链接”资源时，是否检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取。默认为false。建议保持默认值
# 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
redis.pool.testOnBorrow=true

```
##### 4) 在spring.xml中新增
```
<!-- 引入redis配置 -->
<import resource="classpath*:spring-redis.xml" />	
```
##### 5）测试类
```
@Controller
@RequestMapping(value="redis")
public class RedisTemplateController {

    @Autowired
    protected RedisTemplate redisTemplate;

    @RequestMapping(value="template/test")
    public String test(){

/*
        RedisTemplate提供的常用方法
        opsForValue方法：操作具有简单值的条目
        opsForList方法：操作具有list值的条目
        opsForSet方法：操作具有set值的条目
        opsForZSet方法：操作具有ZSet值（排序的set）的条目
        opsForHash方法：操作具有hash值的条目
        boundValueOps方法：以绑定指定key的形式，操作具有简单值的条目
        boundListOps方法：以绑定指定key的形式，操作具有list值的条目
        boundSetOps方法：以绑定指定key的形式，操作具有set值的条目
        boundZSet方法：以绑定指定key的形式，操作具有ZSet值（排序的set）条目
        boundHashOps方法：以绑定指定key的形式，操作具有hash值得条目

*/

        redisTemplate.boundValueOps("zhangsan").set("张三");

        redisTemplate.boundValueOps("hh").set("hello world");
        String str = (String) redisTemplate.boundValueOps("hh").get();
        System.out.println(str);
        System.out.println("redisTemplate配置成功");
        return "redisTemplate";
    }
}	
```

<hr>

#### <span id="ehcache">2. ehcache</span>
##### 1) 引入相关jar包
```
<!-- ehcache 相关依赖  -->
<dependency>
  <groupId>net.sf.ehcache</groupId>
  <artifactId>ehcache</artifactId>
  <version>2.8.2</version>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context-support</artifactId>
  <version>4.2.5.RELEASE</version>
</dependency>	
```
##### 2）新建spring-ehcache文件
```
<!-- 启用缓存注解开关 -->
<cache:annotation-driven />

<!-- 声明cacheManager -->
<bean id="cacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager">
    <property name="cacheManager" ref="ehcache"></property>
</bean>

<!-- cacheManager工厂类，指定ehcache.xml的位置 -->
<bean id="ehcache" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean">
    <property name="configLocation" value="classpath:ehcache-setting.xml"></property>
</bean>  
```
##### 3）新建ehcache-setting.xml文件  
```
<?xml version="1.0" encoding="UTF-8"?>
<ehcache>
    <!-- 磁盘缓存位置  -->
    <diskStore path="java.io.tmpdir"/>

    <!-- 设定缓存的默认数据过期策略 -->
    <!-- maxElementsInMemory：内存中最大缓存对象数 -->
    <defaultCache
            maxElementsInMemory="10000"
            eternal="false"
            overflowToDisk="true"
            timeToIdleSeconds="10"
            timeToLiveSeconds="20"
            diskPersistent="false"
            diskExpiryThreadIntervalSeconds="120"/>



    <!-- 测试缓存 -->
    <cache name="cacheTest"
           maxElementsInMemory="1000"
           eternal="true"
           overflowToDisk="true"
           timeToIdleSeconds="10"
           timeToLiveSeconds="20"/>

</ehcache>  
```
##### 4）spring.xml中新增如下配置	  
```
<!-- 引入ehcache配置 -->
<import resource="classpath*:spring-ehcache.xml" />
```
##### 5）测试类  
```

@Service
public class EhcacheTestServiceImpl implements EhCacheTestService{

    @Override
    @Cacheable(value="cacheTest", key="#param")
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

}


@Controller
@RequestMapping(value="/ehcache")
public class EhcacheController {

    @Autowired
    private EhCacheTestService ehCacheTestService;

    @RequestMapping(value="/test")
    public String test() throws InterruptedException {
        System.out.println("第一次调用：" + ehCacheTestService.getTimestamp("param"));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + ehCacheTestService.getTimestamp("param"));
        Thread.sleep(11000);
        System.out.println("再过11秒之后调用：" + ehCacheTestService.getTimestamp("param"));
        return "ehcache";
    }
}


```

<hr>

### 四、定时任务
#### <span id="spring-task">1.spring-task</span>
##### 1）不需要引用jar包，spring直接有的，新建spring-task.xml文件
```
<!-- 自动扫描的包名 -->
<context:component-scan base-package="com.hh.springTask" ></context:component-scan>

<!-- 不是必配项，但是Spring scheduled-tasks默认是串行执行，时常发生task任务太多，而导致执行任务排队等待，此时就需要配置并行执行 -->
<task:executor id="executor" pool-size="5" />
<task:scheduler id="scheduler" pool-size="10" />

<!-- 开启任务注解 -->
<task:annotation-driven executor="executor" scheduler="scheduler" />

<!-- 任务列表(这是xml配置，注解配置是直接在类中写corn表达式) -->
<bean id="springTaskController" class="com.hh.springTask.SpringTaskController" />

<task:scheduled-tasks>
    <task:scheduled ref="springTaskController" method="firstCron" cron="0 0 0 * * ?"/>
</task:scheduled-tasks>
```
##### 2）spring.xml中引入
```
<!-- 引入springTask配置 -->
<import resource="classpath*:spring-task.xml" />
```
##### 3）注解方式代码示例：
```
@Component("TaskJob")
public class SpringTaskAnnotationController {

    @Scheduled(cron = "0 0 0 * * ?")
    public void job1() {
        System.out.println("spring-task注解方式定时任务进行中。。。");
    }
}
```

<hr>

#### <span id="quartz">2. quartz</span>
##### 1)引入相关jar包
```
<!-- quartz相关依赖-->
<dependency>
  <groupId>org.quartz-scheduler</groupId>
  <artifactId>quartz</artifactId>
  <version>2.2.1</version>
</dependency>

```
##### 2）新建spring-quartz.xml文件
```
<!-- 要执行任务的任务类。 -->
<bean id="testQuartz" class="com.hh.quartz.QuartzController"></bean>

<!-- 将需要执行的定时任务注入JOB中。 -->
<bean id="testJob" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
    <property name="targetObject" ref="testQuartz"></property>
    <!-- 任务类中需要执行的方法 -->
    <property name="targetMethod" value="doSomething"></property>
    <!-- 上一次未执行完成的，要等待有再执行。 -->
    <property name="concurrent" value="false"></property>
</bean>

<!-- 基本的定时器，会绑定具体的任务。 -->
<bean id="testTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
    <property name="jobDetail" ref="testJob"></property>
    <property name="cronExpression" value="0 0 0 * * ?"/>
</bean>

<!--配置调度工厂-->
<bean id="schedulerFactoryBean" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
    <property name="triggers">
        <list>
            <ref bean="testTrigger"></ref>
        </list>
    </property>
</bean>  
```
##### 3）spring.xml中引入
```
<!-- 引入quartz配置 -->
<import resource="classpath*:spring-quartz.xml" />  
```
##### 4）测试类
```
public class QuartzController {

    public void doSomething() {
        
        System.out.println("quartz定时任务执行中");
        
    }
}
```



<hr>

### 五、校验框架
##### <span id="validator">1.hibernate validator</span>
##### 1）不需要jar包，直接在springMVC.xml文件中添加如下配置
```
<!-- 校验器 -->
<bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
    <!-- hibernate校验器-->
    <property name="providerClass" value="org.hibernate.validator.HibernateValidator" />
    <!-- 指定校验使用的资源文件，在文件中配置校验错误信息，如果不指定则默认使用classpath下的ValidationMessages.properties -->
    <property name="validationMessageSource" ref="messageSource" />
</bean>

<!-- 校验错误信息配置文件 -->
<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
    <!-- 资源文件名， 可以不配用默认的，也可以配用自己自定义的 -->
<!--        <property name="basenames">
        <list>
            <value></value>
        </list>
    </property>-->
    <!-- 资源文件编码格式 -->
    <property name="fileEncodings" value="utf-8" />
    <!-- 对资源文件内容缓存时间，单位秒 -->
    <property name="cacheSeconds" value="120" />
</bean>

<!-- 校验器注入到处理器适配器中 -->
<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean"/>

<mvc:annotation-driven conversion-service="conversionService" validator="validator"></mvc:annotation-driven>
```
##### 2）测试实体类  
```
public class Items {

    private int id;

    @Size(min=1,max=30,message="name字符串必须在1到30之间")
    private String name;

    @NotNull(message="生产日期不能为空")
    private Date createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}

```
##### 3）测试controller
```
@Controller
@RequestMapping(value="/validator")
public class ValidatorController {

    @RequestMapping(value="/test")
    public String test(@Validated Items items, BindingResult bindingResult){
        System.out.println("validator配置成功");
        // 获取检验错误信息
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for(ObjectError objectError : allErrors){
                System.out.println(objectError.getDefaultMessage());
            }
            return "validator_error";
        }
        return "validator_success";
    }

}

```

<hr>





#### **参考网址**
[架构之路之spring+shiro的集成](https://blog.csdn.net/tomcat_2014/article/details/56282727)

[spring整合redis（sping-data-redis）](https://blog.csdn.net/ya_1249463314/article/details/79599694)

[java之redis篇(spring-data-redis整合)](https://www.cnblogs.com/tankaixiong/p/3660075.html)

[Spring 极速集成注解 redis 实践](https://www.cnblogs.com/java-class/p/7112541.html)

[spring集成redis，集成redis集群(3种方式都有)](http://chentian114.iteye.com/blog/2292323)

[Spring+EhCache缓存实例](http://www.cnblogs.com/mxmbk/articles/5162813.html)

[使用Spring整合Quartz轻松完成定时任务](https://www.cnblogs.com/hafiz/p/6159280.html)

[【SpringMVC学习06】SpringMVC中的数据校验](https://www.cnblogs.com/shanheyongmu/p/5871312.html)


<font color=#FF4500 size=3>注：文章是经过参考其他的文章然后自己整理出来的，有可能是小部分参考，也有可能是大部分参考，但绝对不是直接转载，觉得侵权了我会删，我只是把这个用于自己的笔记，顺便整理下知识的同时，能帮到一部分人。
ps : 有错误的还望各位大佬指正,小弟不胜感激</font>

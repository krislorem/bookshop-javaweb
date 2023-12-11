# listener
> `Listener`监听器是*JavaWeb*三大组件之一  
> *JavaWeb*三大组件分别是: `Servlet`程序, `Listener`监听器, `Filter`过滤器  
> `Listener`是*JavaEE*的规范, 就是接口  
> 监听器的作用是, 监听某种变化(一般就是 对象创建/销毁, 属性变化), 触发对应方法完成相应的任务  
> JavaWeb中的监听器共八个, 目前最常用的是 `ServletContextListener `
### `ServletContextListener`监听器
- 作用: 监听ServletContext创建或销毁(当我们Web应用启动时, 就会创建ServletContext), 即生命周期监听.  
- 应用场景: 
> (1) 加载初始化的配置文件  
> (2) 任务调度(配合定时器Timer/TimerTask)
- 相关方法
>  `void contextInitialized(ServletContextEvent sce)` 创建ServletContext时触发  
> `contextDestroyed(ServletContextEvent sce)` 销毁ServletContext时触发


    在 Servlet API 中有一个 ServletContextListener 接口，  
    它能够监听 ServletContext 对象的生命周期，实际上就是监听 Web 应用的生命周期。

    当Servlet 容器启动或终止Web 应用时，会触发ServletContextEvent 事件，该事件由ServletContextListener 来处理。
    在 ServletContextListener 接口中定义了处理ServletContextEvent 事件的两个方法。

- `ServletContext` 对象是一个为整个 web 应用提供共享的内存，任何请求都可以访问里面的内容
- 如何实现在服务启动的时候就动态的加入到里面的内容：我们需要做的有：
> 实现 `servletContextListerner` 接口 并将要共享的通过 `setAttribute(name,data)`方法提交到内存中去  
> 应用项目通过 `getAttribute(name)` 将数据取到 。
### `HttpSessionListener`监听器
监听 *Session* 创建或销毁, 即生命周期监听(可用于监护用户上线, 离线)  
- 相关方法
> `void sessionCreated(HttpSessionEvent se)` 创建session时调用  
> `sessionDestroyed(HttpSessionEvent se)` 销毁session时调用

    对每一个正在访问的用户，J2EE应用服务器会为其建立一个对应的HttpSession对象。  
    当一个浏览器第一次访问网站的时候，J2EE应用服务器会新建一个HttpSession对象 ，并触发 HttpSession创建事件 ，
    如果注册了HttpSessionListener事件监听器，则会调用HttpSessionListener事件监听器的 sessionCreated方法。
    相反，当这个浏览器访问结束超时的时候，J2EE应用服务器会销毁相应的HttpSession对象，触发 HttpSession销毁事件，
    同时调用所注册HttpSessionListener事件监听器的sessionDestroyed方法。

`HttpSessionAttributeListener`[^1] 能监测到有人正在往HttpSession里添加属性。你可以采取相应的措施。  



[^1]:监听Session属性的变化
# filter
![](https://img-blog.csdnimg.cn/c97f0d6fa96b408bb87f9e3ef632d56d.png)
## `EncodeFilter` `'/*'`
`request.setCharacterEncoding()`：用来确保发往服务器的参数的编码格式，设置从request中取得的值或从数据库中取出的值。  
指定后可以通过`request.getParameter()`获取自己想要的字符串,如果没有提前指定，则会按照服务器端默认的`“iso-8859-1”`来进行编码；  
**注意**：该方法只对`post`请求有效，对`get`请求无效；对于`get`请求，应该在 *server.xml* 中指定：`URIEncoding=utf-8；`

在执行`request.setCharacterEncoding()`之前不能执行`request.getParameter()`方法；  
因为在执行第一个`getParameter()`的时候，*java* 将会按照编码分析所有的提交内容，而后续的`getParameter()`不再进行分析，`所以setCharacterEncoding()`无效。  
而对于`GET`方法提交表单时，提交的内容在 **URL** 中，一开始就已经按照编码分析提交内容，`setCharacterEncoding()`自然就无效。

## `AdminFilter`  `'/admin/*'`
在 **javaweb** 项目中，用到 `request.getSession()`一般是存储信息到 *session* 中或者从 *session* 中获取信息。

一般有三种参数设置方式：  
> 1.`request.getSession()`  
>> 这是常用的方式，从当前*request*中获取*session*，如果获取不到*session*，则会自动创建一个*session*，并返回新创建的*session*；如果获取到，则返回获取到的*session*;
> 
> 2.`request.getSession(true)`  
>> 这种方法和第一种一样，只是增家了一个`true`参数，告诉它在获取不到的时候自动创建*session*;
> 
> 3.`request.getSession(false)`
>> 这种方法与上两种的区别，就是在获取不到*session*的时候，不会自动创建*session*，而是会返回`null`。

在使用过程中，一般  
- 想要存储到 *session* 中时，使用`request.getSession()  `
- 想要获取 *session* 中的信息时，使用`request.getsession(false)`，并在获取后对 *session* 变量进行是否为` null`的判断，再进行下一步操作。



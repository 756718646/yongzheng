### 资料链接

http://developer.51cto.com/art/201104/255127.htm

### 简介

Stub对象用来提供测试时所需要的测试数据，可以对各种交互设置相应的回应。例如我们可以设置方法调用的返回值等等。Mockito中 when(…).thenReturn(…) 这样的语法便是设置方法调用的返回值。另外也可以设置方法在何时调用会抛异常等。Mock对象用来验证测试中所依赖对象间的交互是否能够达到预期。 Mockito中用 verify(…).methodXxx(…) 语法来验证 methodXxx方法是否按照预期进行了调用。

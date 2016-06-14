### 基础知识

资料:http://blog.csdn.net/androidzhaoxiaogang/article/details/6856201

intent-filter的data属性详述

一、定义 
scheme, host, port, path, pathPrefix, pathPattern 是用来匹配 Intent 中的 Data Uri 的。具体规则如下:
　　scheme://host:port/path or pathPrefix or pathPattern
　　
　Intent.setType()， Intent.setData，Intent.setDataAndType() 这三个方法！

 
setType 调用后设置 mimeType，然后将 data 置为 null；

setData 调用后设置 data，然后将 mimeType 置为 null；

setDataAndType 调用后才会同时设置 data 与 mimeType。


二、区别
　　这里主要说的区别是 path、pathPrefix、pathPattern 之间的区别
 
path 用来匹配完整的路径，如：http://example.com/blog/abc.html，这里将 path 设置为 /blog/abc.html 才能够进行匹配；
pathPrefix 用来匹配路径的开头部分，拿上来的 Uri 来说，这里将 pathPrefix 设置为 /blog 就能进行匹配了；
pathPattern 用表达式来匹配整个路径，这里需要说下匹配符号与转义。
匹配符号：
“*” 用来匹配0次或更多，如：“a*” 可以匹配“a”、“aa”、“aaa”...
“.” 用来匹配任意字符，如：“.” 可以匹配“a”、“b”，“c”...
因此 “.*” 就是用来匹配任意字符0次或更多，如：“.*html” 可以匹配 “abchtml”、“chtml”，“html”，“sdf.html”...
转义：因为当读取 Xml 的时候，“/” 是被当作转义字符的（当它被用作 pathPattern 转义之前），因此这里需要两次转义，读取 Xml 是一次，在 pathPattern 中使用又是一次。如：“*” 这个字符就应该写成 “//*”，“/” 这个字符就应该写成 “////”。


 <activity android:name=".mainui.PeopleHomeActivity" >
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data
                    android:host="my.com"
                    android:scheme="c"
                    android:port="44"/>
            </intent-filter>
        </activity>
        
        
        
### 打开app

资料:http://www.cnblogs.com/yejiurui/p/3413796.html



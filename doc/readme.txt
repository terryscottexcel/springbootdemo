
使用jsp时候，需要配置如下信息：
1)在application.properties文件里:
# 页面默认前缀目录
spring.mvc.view.prefix=/WEB-INF/jsp/
# 响应页面默认后缀
spring.mvc.view.suffix=.jsp

2)在pom.xml文件里
增加解析jsp需要的jar包，否则会出现Springboot访问jsp页面但是却变成下载该页面。
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-jsp-api</artifactId>
</dependency>

3)保存后,pom.xml会出现报错
在打开pom.xml文件时候，有错误提示如下：
Failed to read artifact descriptor for xxx:jar:版本号
参考：
在eclipse导入一个Maven项目后，会pom.xml提示有错误。报：Failed to read artifact descriptor for xxx:jar:版本号。处理办法为：

1、选中项目，然后点击菜单：project->clean...

2、右键选择项目，在出来的菜单中选择Maven->Update Project,

3、出来的对话框注意勾选：Force Update Of Snapshots/Releases

4、点击 OK。

等待更新即可





<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查找用户信息</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<!--
	<center>
		<form action="/findUser" method="post">
			请输入用户ID:<input type="text" name="id">
			 <input type="submit" value="getUser">  
		</form>
		<a>sssssssss<a/>
		<a>${ssss}<a/>
	</center>
	<body>
	<a>新增用户</a>
    <form action="/save" method="post" >
        <input type="text" id="username" name="username"/>
        <input type="password" id="password" name="password"/>
        <input type="text" id="nickname" name="nickname"/>
        <input type="text" id="face" name="face"/>
        <input type="submit" value="save"  />
    </form>
    -->
    
    <a>####################################################</a>
    	<a>新增config</a>
    	
    <form action="dataconfig/add" method="post" >
         <a>son:<a/><input type="text" id="son" name="son"/><br />
         <a>parent_id:<a/><input type="text" id="parent_id" name="parent_id" maxlength="20" onKeyUp="value=value.replace(/[^\d]/g,'')" /><br />
         <a>description:<a/><input type="text" id="description" name="description"/><br />
         <a>son:<a/><input type="submit" value="save"  />
    </form>
            	<a>####################################################</a>
        	<a>删除config</a>
    <form action="dataconfig/delete" method="post" >
         <a>删除节点的id:<a/><input type="text" id="id" name="id" maxlength="10" onKeyUp="value=value.replace(/[^\d]/g,'')" /><br />
   		  <input type="submit" value="删除一项配置数据"  />
    </form>
    
        <a>####################################################</a>
        <a>修改config</a>
    	
    <form action="dataconfig/update" method="post" >
         <a>要修改的id:<a/><input type="text" id="id" name="id" maxlength="12" onKeyUp="value=value.replace(/[^\d]/g,'')" /><br />
         <a>son:<a/><input type="text" id="son" name="son"/><br />
         <a>description:<a/><input type="text" id="description" name="description"/><br />
         <input type="submit" value="保存"  />
    </form>
         <a>####################################################</a>
        <a>查询下级目录</a>
    	
    <form action="dataconfig/listnext" method="post" >
         <a>父级目录id:<a/><input type="text" id="id" name="id" maxlength="12" onKeyUp="value=value.replace(/[^\d]/g,'')" /><br />

         <input type="submit" value="查询"  />
    </form>
        <a>####################################################</a>
        <a>列出所有的数据</a>
     <form action="group/list" method="post" >
         <a>method_id:<a/><input type="text" id="method_id" name="method_id" maxlength="12" onKeyUp="value=value.replace(/[^\d]/g,'')" /><br />

         <input type="submit" value="查询"  />
    </form>
    
</body>
	
</body>
</html>
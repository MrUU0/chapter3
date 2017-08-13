<%--
  Created by IntelliJ IDEA.
  User: wangz
  Date: 2017/8/8
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- edit customer info -->
<h2>create customer info</h2>
<form action="customer_create" method="post">
    id：     <input name="id" type="text" value="${customer.id}"/>
    name：   <input name="name" type="text" value="${customer.name}"/>
<input type="submit">
</form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css">
    body {
        background-color: #f0f0f0;
    }

    .tg {
        border-collapse: collapse;
        border-spacing: 0;
        border-color: #ccc;
    }

    .tg td {
        font-family: Arial, sans-serif;
        font-size: 10px;
        padding: 7px 5px;
        border-style: solid;
        border-width: 1px;
        overflow: hidden;
        word-break: normal;
        border-color: #ccc;
        color: #333;
        background-color: #fff;
    }

    .tg th {
        font-family: Arial, sans-serif;
        font-size: 10px;
        font-weight: normal;
        padding: 7px 5px;
        border-style: solid;
        border-width: 1px;
        overflow: hidden;
        word-break: normal;
        border-color: #ccc;
        color: #333;
        background-color: #f0f0f0;
    }

    .tg .tg-4eph {
        background-color: #f9f9f9
    }

    .pagination{
        font-size: 14px;
        boreder-style: solid;
        border-width: 1px;
        background-color: antiquewhite;
        color: #333333;
        alignment: center;
        width: 200px;
    }
</style>


<html>
<head>
    <title>for Java Rush</title>
</head>
<body>
<div align="center">
<h1>Book manager v1.0</h1>
<br/>
    <%response.sendRedirect("showBooks"); %>
<br/>
</div>
</body>
</html>
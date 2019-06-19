<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
</head>
<body>
<h1>Login to the system</h1>
<form method="post" action="${pageContext.request.contextPath}/person">
    <input type="text" name="name" placeholder="name"><br/>
    <input type="password" name="password" placeholder="pasword"><br/>
    <input type="submit"/>
</form>

</body>
</html>
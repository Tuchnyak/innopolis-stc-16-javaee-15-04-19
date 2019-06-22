<%@tag description="Page template" pageEncoding="UTF-8" %>

<%@attribute name="titleText" fragment="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        <jsp:invoke fragment="titleText"/>
    </title>
    <meta charset="UTF-8"/>
</head>
<body>
<jsp:doBody/>
<br>
<a href="${pageContext.request.contextPath}/">Main page</a>
</body>
</html>
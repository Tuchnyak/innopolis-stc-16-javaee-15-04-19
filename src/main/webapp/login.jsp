<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page-template>
    <jsp:attribute name="titleText">Login page</jsp:attribute>
    <jsp:body>
        <h1>Login to the system</h1>
        <form method="post" action="${pageContext.request.contextPath}/person">
            <input type="text" name="name" placeholder="name"><br/>
            <input type="password" name="password" placeholder="pasword"><br/>
            <input type="submit"/>
        </form>
    </jsp:body>
</t:page-template>
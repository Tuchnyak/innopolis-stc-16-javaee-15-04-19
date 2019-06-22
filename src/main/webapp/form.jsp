<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page-template>
    <jsp:attribute name="titleText">New Student</jsp:attribute>
    <jsp:body>
        <h1>Adding a new student</h1>
        <form method="post" action="${pageContext.request.contextPath}/person">
            <input type="text" name="name" placeholder="name"><br/>
            <input type="text" name="birth" placeholder="birth"><br/>
            <input type="text" name="email" placeholder="email"><br/>
            <input type="text" name="phone" placeholder="phone"><br/>
            <input type="submit"/>
        </form>
    </jsp:body>
</t:page-template>
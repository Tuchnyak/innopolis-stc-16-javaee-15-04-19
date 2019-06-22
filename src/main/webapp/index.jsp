<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page-template>
    <jsp:attribute name="titleText">Students</jsp:attribute>
    <jsp:body>
        <h1>Students</h1>
        <ul>
            <li><a href="${pageContext.request.contextPath}/person/list">List students</a></li>
            <li><a href="${pageContext.request.contextPath}/person">New student</a></li>
        </ul>
    </jsp:body>
</t:page-template>
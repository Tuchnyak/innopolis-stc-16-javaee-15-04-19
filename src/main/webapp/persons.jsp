<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="persons" scope="request" type="java.util.List"/>

<t:page-template>
    <jsp:attribute name="titleText">List of persons</jsp:attribute>
    <jsp:body>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Birthdate</th>
                <th>email</th>
                <th>phone</th>
            </tr>
            <c:forEach var="person" items="${persons}">
                <tr>
                    <td>${person.id}</td>
                    <td>${person.name}</td>
                    <td>${person.birthDate}</td>
                    <td>${person.email}</td>
                    <td>${person.phone}</td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:page-template>
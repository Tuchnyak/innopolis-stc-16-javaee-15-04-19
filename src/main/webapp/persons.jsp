<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="persons" scope="request" type="java.util.List"/>

<t:page-template>
    <jsp:attribute name="titleText">List of persons</jsp:attribute>
    <jsp:body>
        <div class="container-fluid">
            <div class="row">
                <div class="col text-center">
                    <h1 class="display-1">Person list</h1>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <table class="table table-striped table-hover">
                        <caption>List of persons</caption>
                        <thead>
                        <tr class="text-center table-success">
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Birthdate</th>
                            <th scope="col">Email</th>
                            <th scope="col">&#9742;</th>
                        </tr>
                        </thead>
                        <c:forEach var="person" items="${persons}">
                            <tr>
                                <th cope="row">${person.id}</th>
                                <td>${person.name}</td>
                                <td>${person.birthDate}</td>
                                <td>${person.email}</td>
                                <td>${person.phone}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:page-template>
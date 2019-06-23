<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page-template>
    <jsp:attribute name="titleText">Students</jsp:attribute>
    <jsp:body>

        <div class="container-fluid text-center">
            <div class="row">
                <div class="col">
                    <h1 class="display-1">Students</h1>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col col-md-2 justify-content-center">
                    <a class="btn btn-lg btn-info" href="${pageContext.request.contextPath}/person/list">
                        List students
                    </a>
                </div>
                <div class="col-sm-auto">
                </div>
                <div class="col col-md-2 justify-content-center">
                    <a class="btn btn-lg btn-info" href="${pageContext.request.contextPath}/person">
                        New student
                    </a>
                </div>
            </div>
        </div>

    </jsp:body>
</t:page-template>
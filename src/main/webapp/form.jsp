<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page-template>
    <jsp:attribute name="titleText">Students</jsp:attribute>
    <jsp:body>

        <div class="container-fluid text-center">
            <div class="row">
                <div class="col">
                    <h1 class="display-1">Adding a new student</h1>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col col-md-6 justify-content-center">
                    <form method="post" action="${pageContext.request.contextPath}/person">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Name</span>
                            </div>
                            <input class="form-control" type="text" name="name">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon2">Birth date</span>
                            </div>
                            <input class="form-control" type="text" name="birth" placeholder="dd.MM.yyyy">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon3">Email</span>
                            </div>
                            <input class="form-control" type="text" name="email">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon4">&#9742;</span>
                            </div>
                            <input class="form-control" type="text" name="phone">
                        </div>

                        <input class="btn btn-lg btn-warning my-1" type="submit"/>
                    </form>
                </div>
            </div>
        </div>

    </jsp:body>
</t:page-template>
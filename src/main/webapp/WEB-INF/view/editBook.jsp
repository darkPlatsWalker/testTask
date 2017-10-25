<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>CRUD Application</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
</head>
<body>
    <main>
        <div class="container">

            <div class="container-header">
                CRUD Application
            </div>

            <div class="inner">

                <c:url var="editAction" value="/showBooks/edit"/>

                <form:form action="${editAction}" commandName="book">

                    <h2>Editing book</h2>

                    <div class="addedit">
                        <div class="addname">
                            <form:label path="id">
                                <spring:message text="ID"/>
                            </form:label>
                        </div>
                        <div class="addinput">
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </div>
                    </div>


                    <div class="addedit">
                        <div class="addname">
                            <form:label path="title">
                                <spring:message text="Title"/>
                            </form:label>
                        </div>
                        <div class="addinput">
                            <form:input path="title" type="text" id="title"/>
                        </div>
                        <div class="addinput">
                            <form:errors path="title"/>
                        </div>
                    </div>

                    <div class="addedit">
                        <div class="addname">
                            <form:label path="description">
                                <spring:message text="Description"/>
                            </form:label>
                        </div>
                        <div class="addinput">
                            <form:input path="description" type="text" id="description"/>
                        </div>
                        <div class="addinput">
                            <form:errors path="description"/>
                        </div>
                    </div>

                    <div class="addedit">
                        <div class="addname">
                            <form:label path="author">
                                <spring:message text="Author"/>
                            </form:label>
                        </div>
                        <div class="addinput">
                            <form:input path="author" type="text" id="author" readonly="true" />
                        </div>
                        <div class="addinput">
                            <form:errors path="author"/>
                        </div>
                    </div>

                    <div class="addedit">
                        <div class="addname">
                            <form:label path="isbn">
                                <spring:message text="ISBN"/>
                            </form:label>
                        </div>
                        <div class="addinput">
                            <form:input path="isbn" type="text" id="isbn"/>
                        </div>
                        <div class="addinput">
                            <form:errors path="isbn"/>
                        </div>
                    </div>

                    <div class="addedit">
                        <div class="addname">
                            <form:label path="printYear">
                                <spring:message text="Print year"/>
                            </form:label>
                        </div>
                        <div class="addinput">
                            <form:input path="printYear" />
                        </div>
                        <div class="addinput">
                            <form:errors path="printYear"/>
                        </div>
                    </div>

                    <div class="addedit">
                        <div class="addname">
                            <form:label path="read">
                                <spring:message text="Read already"/>
                            </form:label>
                        </div>
                        <div>
                            <form:input path="read" value="false" readonly="true" disabled="true" />
                        </div>
                    </div>

                    <div class="addedit">
                        <input type="submit"  class="editbutton" value="<spring:message text="Edit"/>"/>
                    </div>

                </form:form>

            </div>

            <div class="pagelink-center">
                <a class="pagelink link" href="<c:url value="/showBooks"/>">All <br> books</a>
                <a class="pagelink link" href="<c:url value="/addBook"/>">Add <br> new book</a>
                <a class="pagelink link" href="<c:url value="/searchUnread"/>">Show <br> unread books</a>
            </div>

            <div class="pagelink-center">
                <div>
                    <form metod="POST", action="/searchAuthor">
                        <label for="author">Author`s name:</label>
                        <input type="text" id="author" name="author" placeholder="author"/>
                        <input class="big" type="submit"  value="Search author"/>
                    </form>
                </div>
                <div>
                    <form metod="POST", action="/searchTheme">
                        <label for="theme">Search books on:</label>
                        <input type="text" id="theme" name="theme" placeholder="example: java"/>
                        <input class="big" type="submit"  value="Search by theme"/>
                    </form>
                </div>
                <div>
                    <form metod="POST", action="/searchByYear">
                        <label for="year">Print year:</label>
                        <input id="year"  name="year" placeholder="42"/>
                        <input class="big" type="submit"  value="Search by year"/>
                    </form>
                </div>
            </div>

        </div>
    </main>
</body>
</html>

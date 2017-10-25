<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                <c:if test="${pageCount == 0}">
                    <h2 align="center">В списке нет книг!</h2>
                </c:if>

                <c:if test="${pageCount > 0}">
                    <c:if test="${!empty page}">
                        <p><h2>List of books</h2></p>
                        <table class="table-users">
                            <tr>
                                <th>ID</th>
                                <th>Title </th>
                                <th>Description</th>
                                <th>Author</th>
                                <th>ISBN</th>
								<th>Year</th>
								<th>Is read?</th>
								<th>Set read</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <c:forEach var="book" items="${page}">
                                <tr>
                                    <td>${book.id}</td>
                                    <td>${book.title}</td>
                                    <td>${book.description}</td>
                                    <td>${book.author}</td>
									<td>${book.isbn}</td>
									<td>${book.printYear}</td>
									<td>${book.read}</td>
									<td align="center"><a class="tablelink" href="<c:url value="/setRead/${book.id}/${currentPage}"/>">Set Read</a></td>
                                    <td align="center"><a class="tablelink" href="<c:url value="/editBook/${book.id}"/>">Edit</a></td>
                                    <td align="center"><a class="tablelink" href="<c:url value="/remove/${book.id}/${currentPage}"/>">Delete</a></td>
                                    </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </c:if>

                <br/>

                <c:if test="${!empty page}">
                    <table class="table-pages">
                        <tr>
                            <td><a class="tablelink" href="<c:url value="/showBooks/${0}"/>"><<</a></td>
                            <td><a class="tablelink" href="<c:url value="/showBooks/${currentPage - 1}"/>">previous</a></td>
                            <td></td>
                            <c:forEach items="${refs}" var="count">
                                <td>
                                    <a class="tablelink" href="<c:url value="/showBooks/${count.intValue() - 1}"/>">${count.intValue()}</a>
                                </td>
                            </c:forEach>
                            <td></td>
                            <td><a class="tablelink" href="<c:url value="/showBooks/${currentPage + 1}"/>">next</a></td>
                            <td><a class="tablelink" href="<c:url value="/showBooks/${pageCount - 1}"/>">>></a></td>
                        </tr>
                    </table>
                </c:if>

            </div>

            <div class="pagelink-center">
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
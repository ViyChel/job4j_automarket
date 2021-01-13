<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <%@page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" type="image/png" href="favicon.ico"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <title>Авторынок: купить, продать и обменять машину</title>
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/posts.do"/>'>Вакансии</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/candidates.do"/>'>Кандидаты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/post/edit.jsp"/>'>Добавить вакансию</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/candidate/edit.jsp"/>'>Создать кандидата</a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href='<c:url value="/auth.do"> <c:param name="exit" value="true"/> </c:url> '>
                    <c:out value="${user.name}"/> | Выйти
                </a>
            </li>
        </ul>
    </div>
</div>
<div class="container pt-3">

    <div class="row">
        <div class="card" style="width: 100%">
                <div class="row justify-content-center mt-3 sticky-top">
                    <a href='<c:url value="/advertisement/add.jsp"/>' class="btn btn-outline-primary" role="button">Подать объявление</a>
                </div>
            <%--<div class="card-header">
                Кандидаты
            </div>--%>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <%--<tr>
                        <th scope="col">Фото</th>
                        <th scope="col">Описание</th>
                    </tr>--%>
                    </thead>
                    <tbody>
                    <tr>
                        <td>

                            <img src='<c:url value="./img/BMW%20Z4.jpg"/>' width="450px"
                                 height="300px"/>
                        </td>
                        <td>
                            <a href="<c:url value='/static/defaultPhoto.jpg'/>">Ссылка на детальную инфу, модель - год</a>
                            <div>Двигатель дизель, 2.1 л</div>
                            <div>Пробег 41 138</div>
                            <div>автомат</div>
                            <div>4 WD</div>
                        </td>
                        <td>
                            <div>Цена 1500000 р</div>
                            <div>Город</div>
                            <div>Дата публикации</div>
                        </td>
                    </tr>
                    <tr>
                        <td>

                            <img src='<c:url value="./img/Porsche%20Cayenne.jpg"/>' width="450px"
                                 height="300px"/>
                        </td>
                        <td>
                            <a href="<c:url value='/static/defaultPhoto.jpg'/>">Ссылка на детальную инфу, модель - год</a>
                            <div>Двигатель дизель, 2.1 л</div>
                            <div>Пробег 41 138</div>
                            <div>автомат</div>
                            <div>4 WD</div>
                        </td>
                        <td>
                            <div>Цена 1500000 р</div>
                            <div>Город</div>
                            <div>Дата публикации</div>
                        </td>
                    </tr>
                    <tr>
                        <td>

                            <img src='<c:url value="./img/Mercedes-Benz%20GLA-Class.jpg"/>' width="450px"
                                 height="300px"/>
                        </td>
                        <td>
                            <a href="<c:url value='/static/defaultPhoto.jpg'/>">Ссылка на детальную инфу, модель - год</a>
                            <div>Двигатель дизель, 2.1 л</div>
                            <div>Пробег 41 138</div>
                            <div>автомат</div>
                            <div>4 WD</div>
                        </td>
                        <td>
                            <div>Цена 1500000 р</div>
                            <div>Город</div>
                            <div>Дата публикации</div>
                        </td>
                    </tr>
                    <%--                    <c:forEach var="candidate" items="${candidates}">--%>
                    <%--                        <tr>--%>
                    <%--                            <td>--%>
                    <%--                                <c:if test="${candidate.photo == null}">--%>
                    <%--                                    <img src='<c:url value="/static/defaultPhoto.jpg"/>' width="150px"--%>
                    <%--                                         height="100px"/>--%>
                    <%--                                    <a href="<c:url value='/static/defaultPhoto.jpg'/>">Download</a>--%>
                    <%--                                </c:if>--%>
                    <%--                                <c:if test="${candidate.photo != null}">--%>
                    <%--                                    <img src="<c:url value='/download?photoId=${candidate.photo.id}'/>" width="150px"--%>
                    <%--                                         height="100px"/>--%>
                    <%--                                    <a href="<c:url value='/download?photoId=${candidate.photo.id}'/>">Download</a>--%>
                    <%--                                </c:if>--%>
                    <%--                            </td>--%>
                    <%--                            <td>--%>
                    <%--                                <a href='<c:url value="/candidate/edit.jsp?id=${candidate.id}"/>'>--%>
                    <%--                                    <i class="fas fa-edit mr-3"></i>--%>
                    <%--                                </a>--%>
                    <%--                                <a href='<c:url value="/candidates.do">--%>
                    <%--                                    <c:param name="action" value="delete" />--%>
                    <%--                                    <c:param name="id" value="${candidate.id}" />--%>
                    <%--                                    </c:url>'>--%>
                    <%--                                    <i class='far fa-trash-alt mr-3'></i>--%>
                    <%--                                </a>--%>
                    <%--                                <c:out value="${candidate.name}"/>--%>
                    <%--                            </td>--%>
                    <%--                        </tr>--%>
                    <%--                    </c:forEach>--%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
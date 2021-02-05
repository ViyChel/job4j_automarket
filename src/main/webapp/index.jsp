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
    <link rel="stylesheet" href="./css/style.css">
    <title>Авторынок: купить, продать и обменять машину</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-end">
        <ul class="nav">
            <% if (session.getAttribute("user") == null) {%>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/reg.do"/>'>Регистрация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/auth.do"/>'>Войти</a>
            </li>
            <% } else {%>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/private.jsp"/>'>Личный кабинет</a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href='<c:url value="/auth.do"> <c:param name="exit" value="true"/> </c:url> '>
                    <c:out value="${user.name}"/> | Выйти
                </a>
            </li>
            <% }%>
        </ul>
    </div>
</div>

<div class="container pt-3">
    <div class="row">
        <div class="card " style="width: 100%">
            <div class="card-body text-center mt-3">
                <a href='<c:url value="/advertisement/add.jsp"/>' class="btn btn-outline-primary" role="button">Подать
                    объявление</a>
            </div>
            <div class="card-body">
                <ul class="list-inline">
                    <li class="list-inline-item mx-3">
                        <label><input class="form-check-input" type="checkbox" id="lastDay" value=""
                                      onclick="printTasks()">показать за последний
                            день</label>
                    </li>
                    <li class="list-inline-item mx-3">
                        <label><input class="form-check-input" type="checkbox" id="checkPhoto" value=""
                                      onclick="printTasks()">показать с фото</label>
                    </li>
                    <li class="list-inline-item mx-3">
                        <label>
                            <select name="" class="custom-select my-1 mr-sm-2" id="brands"
                                    onchange="printTasks()"></select>
                        </label>
                    </li>
                </ul>
                <table class="table table-borderless">
                    <tbody id="ads">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="./scripts/script.js" type="text/javascript" defer></script>
</body>
</html>
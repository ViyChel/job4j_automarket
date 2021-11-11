<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.job4j.automarket.model.Advertisement" %>
<%@ page import="ru.job4j.automarket.persistence.HbmAdvertisement" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.automarket.model.Photo" %>
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0/dist/fancybox.css"/>
    <link rel="stylesheet" href="./css/style.css">
    <title>Авторынок: купить, продать и обменять машину</title>
</head>
<body class=".bg-info">
<%
    String id = request.getParameter("id");
    Advertisement add = Advertisement.of();
    if (id != null) {
        add = HbmAdvertisement.getStore().findById(Integer.parseInt(id));
        String s = "";
    }
    List<Photo> photos = add.getPhotos();
%>
<div class="container">
    <div class="row justify-content-end">
        <ul class="nav">
            <% if (session.getAttribute("user") == null) {%>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/"/>'>Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/reg.do"/>'>Регистрация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/auth.do"/>'>Войти</a>
            </li>
            <% } else {%>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/"/>'>Главная</a>
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
            <div class="card-body">
                <table class="table">
                    <tr>
                        <div class="h2 text-success">
                            Продажа <%=add.getCar().getBrand().getName()%> <%=add.getCar().getModel()%>,
                            <%=add.getCar().getYear()%> г.,
                            г. <%=add.getCity()%>
                        </div>
                    </tr>
                    <tbody id="ads">
                    <tr>
                        <td class="w-50 h-50">
                            <% if (add.getPhotos().size() == 0) {%>
                            <img src='<c:url value="default.png"/>' class="border" width="600px" height="400px"
                                 alt="photo"/>
                            <%
                            } else {
                                int photoId = 0;
                                for (Photo photo : photos) {
                                    if (photoId == 0) {%>
                            <a
                                    data-fancybox="gallery"
                                    data-src="./image/<%=photo.getName()%>"
                            >
                                <img src="./image/<%=photo.getName()%>" class="border" width="600px"
                                     height="400px" alt="photo"/>
                            </a>
                            <% photoId++;
                            } else {%>
                            <a data-fancybox="gallery" data-src="./image/<%=photo.getName()%>">
                                <img src="./image/<%=photo.getName()%>" class="border" width="197px"
                                     height="150px" alt="photo"/>
                            </a>
                            <%
                                        }
                                    }
                                }
                            %>
                            <% if (add.isStatus()) {%>
                            <div class="font-weight-bold text-danger mt-3">ПРОДАНО</div>
                            <% } else {%>
                            <div class="font-weight-bold text-success mt-3">ПРОДАЕТСЯ</div>
                            <% }%>
                            <div class="mt-3">Дата размещения : <%=add.dateFormat(add.getDate())%>
                            </div>
                            <div class="mt-3">Контакты : <%=add.getUser().getName() %>
                                тел. <%=add.getUser().getPhone() %>
                            </div>
                        </td>
                        <td>
                            <table class="table table-borderless">
                                <tbody>
                                <tr>
                                    <td class="text-secondary p-1">Марка</td>
                                    <td class="p-1"><%=add.getCar().getBrand().getName()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-secondary p-1">Модель</td>
                                    <td class="p-1"><%=add.getCar().getModel()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-secondary p-1">Двигатель</td>
                                    <td class="p-1"><%=add.getCar().getEngineType()%>
                                        , <%=add.getCar().getEngineVolume()%>
                                        л., <%=add.getCar().getEnginePower()%> л.с.
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-secondary p-1">Пробег, км</td>
                                    <td class="p-1"><%=add.getCar().getMileage()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-secondary p-1" style="width: 40%">Коробка передач</td>
                                    <td class="p-1"><%=add.getCar().getTransmission()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-secondary p-1">Привод</td>
                                    <td class="p-1"><%=add.getCar().getGear()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-secondary p-1">Дополнительно</td>
                                    <td class="p-1"><%=add.getDescription()%>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="h4 text-danger">Цена <%=add.getPrice()%> ₽</div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<br>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0/dist/fancybox.umd.js"></script>
</body>
</html>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" type="image/png" href="./favicon.ico"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

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
<div class="container">
    <form class="form-horizontal" style="width:100%" action="<c:url value='/add.do'/>" method="post"
          enctype="multipart/form-data">
        <input hidden name="id" type="text" value="<c:out value="${ad.id}"/>">
        <!-- Form Name -->
        <div class="text-center">
            <c:if test="${ad == null}">
                <legend>Добавить объявление</legend>
            </c:if>
            <c:if test="${ad != null}">
                <legend>Редактирование объявления</legend>
            </c:if>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="body">Марка автомобиля</label>
            <div class="col-md-4">
                <select id="brand" name="brand" class="form-control" required>
                    <option hidden value="1">Выберите марку автомобиля</option>
                    <c:forEach var="brand" items="${brands}">
                        <option value="<c:out value="${brand.name}" />"
                                <c:if test="${ad != null && brand.name == ad.car.brand.name}">
                                    selected
                                </c:if>>
                            <c:out value="${brand.name}"/></option>
                    </c:forEach>

                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="model">Модель</label>
            <div class="col-md-4">
                <input id="model" type="text" name="model" placeholder="Модель автомобиля"
                       class="form-control input-md" value="<c:out value="${ad.car.model}"/>" required>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="yearRelease">Год выпуска</label>
            <div class="col-md-4">
                <input id="yearRelease" type="text" name="yearRelease" placeholder="Год выпуска"
                       class="form-control input-md" value="<c:out value="${ad.car.year}"/>" required>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="color">Цвет</label>
            <div class="col-md-4">
                <input id="color" type="text" name="color" placeholder="Цвет автомобиля" class="form-control input-md"
                       value="<c:out value="${ad.car.color}"/>" required>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label">Тип двигателя</label>
            <div class="col-md-4">
                <label class="radio-inline" for="radios-0">
                    <input type="radio" name="engineType" id="radios-0" value="Бензиновый"
                    <c:if test="${ad == null || ad.car.engine.engineType == 'Бензиновый'}">
                           checked="checked"
                    </c:if>>
                    Бензиновый
                </label>
                <label class="radio-inline" for="radios-1">
                    <input type="radio" name="engineType" id="radios-1" value="Дизельный"
                    <c:if test="${ad.car.engine.engineType == 'Дизельный'}">
                           checked="checked"
                    </c:if>>
                    Дизельный
                </label>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="enginePower">Мощность двигателя, л.с.</label>
            <div class="col-md-4">
                <input name="enginePower" id="enginePower" type="text" placeholder="Мощность двигателя"
                       class="form-control input-md" value="<c:out value="${ad.car.engine.enginePower}"/>" required>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="engineVolume">Объем двигателя, л</label>
            <div class="col-md-4">
                <input id="engineVolume" name="engineVolume" type="text" placeholder="Объем двигателя"
                       class="form-control input-md" value="<c:out value="${ad.car.engine.engineVolume}"/>" required>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="body">Тип кузова</label>
            <div class="col-md-4">
                <select id="body" name="body" class="form-control" required>
                    <option hidden value="1">Выберите тип кузова</option>
                    <c:forEach var="body" items="${bodies}">
                        <option value="<c:out value="${body.name}" />"
                            <%-- <c:if test="${ad == null}">
                                 selected
                             </c:if>>--%>
                                <c:if test="${ad != null && body.name == ad.car.body.name}">
                                    selected
                                </c:if>>
                            <c:out value="${body.name}"/></option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label">Тип коробки передач</label>
            <div class="col-md-4">
                <label class="radio-inline" for="mech1">
                    <input type="radio" name="transmission" id="mech1" value="Автоматическая"
                    <c:if test="${ad == null || ad.car.transmission == 'Автоматическая'}">
                           checked="checked"
                    </c:if>>
                    Автоматическая
                </label>
                <label class="radio-inline" for="mech2">
                    <input type="radio" name="transmission" id="mech2" value="Механическая"
                    <c:if test="${ad.car.transmission == 'Механическая'}">
                           checked="checked"
                    </c:if>>
                    Механическая
                </label>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label">Привод</label>
            <div class="col-md-4">
                <label class="radio-inline" for="gear1">
                    <input type="radio" name="gear" id="gear1" value="2WD"
                    <c:if test="${ad == null || ad.car.gear == '2WD'}">
                           checked="checked"
                    </c:if>>
                    2WD
                </label>
                <label class="radio-inline" for="gear2">
                    <input type="radio" name="gear" id="gear2" value="4WD"
                    <c:if test="${ad.car.gear == '4WD'}">
                           checked="checked"
                    </c:if>>
                    4WD
                </label>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mileage">Пробег, км</label>
            <div class="col-md-4">
                <input id="mileage" type="text" name="mileage" placeholder="Пробег автомобиля"
                       class="form-control input-md" value="<c:out value="${ad.car.mileage}"/>" required>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="price">Цена (в руб.)</label>
            <div class="col-md-4">
                <input id="price" type="text" name="price" placeholder="Цена автомобиля" class="form-control input-md"
                       value="<c:out value="${ad.price}"/>" required>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="price">Город</label>
            <div class="col-md-4">
                <input id="city" type="text" name="city" placeholder="Город" class="form-control input-md"
                       value="<c:out value="${ad.city}"/>" required>
            </div>
        </div>

        <!-- Textarea -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="desc">Дополнительно</label>
            <div class="col-md-4">
                 <textarea class="form-control" id="desc" name="desc" value="<c:out value="${ad.description}"/>"
                           required><c:out value="${ad.description}"/></textarea>
            </div>
        </div>

        <!-- File Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="imgData">Добавить изображение</label>
            <div class="col-md-4">
                <input id="imgData" name="file" class="input-file" type="file" multiple>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button type="submit" id="singlebutton" name="singlebutton" class="btn btn-primary"
                        onclick="return validate()">
                    <c:if test="${ad == null}"> Добавить объявление </c:if>
                    <c:if test="${ad != null}"> Сохранить объявление </c:if>
                </button>
            </div>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
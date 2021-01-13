<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <%@page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" type="image/png" href="../favicon.ico"/>
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
    <h2>Добавить объявление</h2>
    <form class="form-horizontal" style="width:100%" action="<c:url value='/car.do'/>" method="post"
          enctype="multipart/form-data">
        <%--    <fieldset >--%>

        <!-- Form Name -->
        <%--        <legend class="text-center">Добавить объявление</legend>--%>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="model">Модель</label>
            <div class="col-md-4">
                <input id="model" type="text" name="model" placeholder="Модель автомобиля"
                       class="form-control input-md">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="yearRelease">Год выпуска</label>
            <div class="col-md-4">
                <input id="yearRelease" type="text" name="yearRelease" placeholder="Год выпуска"
                       class="form-control input-md">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="color">Цвет</label>
            <div class="col-md-4">
                <input id="color" type="text" name="color" placeholder="Цвет автомобиля" class="form-control input-md">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label">Тип двигателя</label>
            <div class="col-md-4">
                <label class="radio-inline" for="radios-0">
                    <input type="radio" name="engine" id="radios-0" value="Бензиновый" checked="checked">
                    Бензиновый
                </label>
                <label class="radio-inline" for="radios-1">
                    <input type="radio" name="engine" id="radios-1" value="Дизельный">
                    Дизельный
                </label>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label">Мощность двигателя, л.с.</label>
            <div class="col-md-4">
                <input name="powerengine" type="text" placeholder="Мощность двигателя"
                       class="form-control input-md">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="capacityEngine">Объем двигателя, л</label>
            <div class="col-md-4">
                <input id="capacityEngine" name="capacityEngine" type="text" placeholder="Объем двигателя"
                       class="form-control input-md">
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="body">Тип кузова</label>
            <div class="col-md-4">
                <select id="body" name="body" class="form-control">
                    <option hidden value="1">Выберите тип кузова</option>
                    <option value="Седан">Седан</option>
                    <option value="Хэтчбек">Хэтчбек</option>
                    <option value="Внедорожник">Внедорожник</option>
                    <option value="Кроссовер">Кроссовер</option>
                    <option value="Универсал">Универсал</option>
                    <option value="Купе">Купе</option>
                    <option value="Минивен">Минивен</option>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label">Тип коробки передач</label>
            <div class="col-md-4">
                <label class="radio-inline" for="mech1">
                    <input type="radio" name="transmission" id="mech1" value="Автоматическая" checked="checked">
                    Автоматическая
                </label>
                <label class="radio-inline" for="mech2">
                    <input type="radio" name="transmission" id="mech2" value="Механическая">
                    Механическая
                </label>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label">Привод</label>
            <div class="col-md-4">
                <label class="radio-inline" for="gear1">
                    <input type="radio" name="gear" id="gear1" value="2WD" checked="checked">
                    2WD
                </label>
                <label class="radio-inline" for="gear2">
                    <input type="radio" name="gear" id="gear2" value="4WD">
                    4WD
                </label>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mileage">Пробег, км</label>
            <div class="col-md-4">
                <input id="mileage" type="text" name="mileage" placeholder="Пробег автомобиля"
                       class="form-control input-md">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="price">Цена (в руб.)</label>
            <div class="col-md-4">
                <input id="price" type="text" name="price" placeholder="Цена автомобиля" class="form-control input-md">
            </div>
        </div>

        <!-- Textarea -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="desc">Дополнительно</label>
            <div class="col-md-4">
                <textarea class="form-control" id="desc" name="desc"></textarea>
            </div>
        </div>

        <!-- File Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="imgData">Добавить изображение</label>
            <div class="col-md-4">
                <input id="imgData" name="file" class="input-file" type="file">
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button type="submit" id="singlebutton" name="singlebutton" class="btn btn-primary"
                        onclick="return validate()">Добавить объявление
                </button>
            </div>
        </div>

        <%--    </fieldset>--%>
    </form>
</div>
</body>
</html>
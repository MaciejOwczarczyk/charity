<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link href='<c:url value="../../resources/css/style2.css"/>' rel="stylesheet" type="text/css">
</head>
<body>

<header class="header--form-page">
    <%@ include file="header.jsp"%>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br />
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section>
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <H1 class="text">Ważne!</H1>
            <h2>Podaj adres oraz termin odbioru rzeczy.</h2>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>4</span>/4</div>
        <br><br><br><br>

        <form:form method="get" modelAttribute="donation" action="/donation/addConfirm">

            <div>
                <h2>Podaj adres oraz termin odbioru rzecz przez kuriera:</h2>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Ulica
                                <form:input path="street" type="text" id="dupa" name="street"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Miasto
                                <form:input path="city" type="text"/>
                            </label>

                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Kod pocztowy
                                <form:input path="zipCode" type="text"/>
                            </label>
                        </div>

                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Data
                                <form:input path="pickUpDate" type="date" pattern="yyyy-MM-dd"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Godzina
                                <form:input path="pickUpTime" type="time"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <form:textarea path="pickUpComment" rows="4" cols="50" size="40" maxlength="160"/>
                            </label>
                        </div>
                    </div>
                </div>
                <br><br><br><br><br><br><br><br><br>
                <div class="form-group form-group--buttons">

                    <a href="/donation/addStep3" class="btn prev-step">Wstecz</a>
                    <input type="submit" class="btn" value="Dalej">
                </div>
            </div>

        </form:form>
    </div>
</section>

<br><br><br><br><br><br><br><br><br>

<%@ include file="footer.jsp"%>

<script src="<c:url value='../../resources/js/app.js'/>" type="text/javascript"></script>
</body>
</html>

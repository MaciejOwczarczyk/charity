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
    <link rel="stylesheet" href="../../../resources/css/style.css" />
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj Agata
                <ul class="dropdown">
                    <li><a href="#">Profil</a></li>
                    <li><a href="#">Ustawienia</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <li><a href="#">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="/donation/add" class="btn btn--without-border active">Start</a></li>
            <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

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
            <h1>Ważne!</h1>
            <h2>
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </h2>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>3</span>/4</div>

        <form:form method="get" modelAttribute="donation" action="/donation/addStep4">

            <!-- STEP 3 -->
            <div>
                <h2>Wybierz organizacje, której chcesz pomóc:</h2>

                <c:forEach items="${institutions}" var="answer" varStatus="loop">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:radiobutton path="institution" value="${answer.id}"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                                <div class="title">${answer.name}</div>
                                <div class="subtitle">${answer.description}</div>
                            </span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <a href="/donation/addStep2" class="btn prev-step">Wstecz</a>
                    <input type="submit" class="btn next-step" value="Dalej">
                </div>
            </div>

        </form:form>
    </div>
</section>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<%@ include file="../../../WEB-INF/views/footer.jsp"%>

<script src="../../../resources/js/app.js"></script>
</body>
</html>

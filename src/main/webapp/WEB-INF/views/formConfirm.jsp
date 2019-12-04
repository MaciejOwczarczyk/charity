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
            <li><a href="" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
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

    <div class="form--steps-container">

        <form:form modelAttribute="donation" method="post">
            <div data-step="1">
                <h2>Podsumowanie Twojej darowizny</h2>

                <div class="summary">
                    <div class="form-section">
                        <h2>Oddajesz:</h2>
                        <ul>
                            <li>
                                <span></span>
                                <span>
                                    ${donation.quantity} worek/ów
                                    <c:forEach items="${donation.categoryList}" var="category">
                                        ${category.name}
                                    </c:forEach>
                                    w dobrym stanie</span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text"
                                >${donation.institution.name}</span
                                >
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns title">
                        <div class="form-section--column">
                            <h1>Adres odbioru:</h1>
                            <ul>
                                <li>${donation.street}</li>
                                <li>${donation.city}</li>
                                <li>${donation.zipCode}</li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h1>Termin odbioru:</h1>
                            <ul>
                                <li>${donation.pickUpDate}</li>
                                <li>${donation.pickUpTime}</li>
                                <li>${donation.pickUpComment}</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <br><br><br><br>

                <div class="form-group form-group--buttons">

                    <a href="/donation/addStep4" class="btn prev-step">Wstecz</a>
                    <input type="submit" class="btn" value="Potwierdzam">
                </div>
            </div>
        </form:form>

</section>

<br><br><br><br><br><br>





<%@ include file="../../../WEB-INF/views/footer.jsp"%>

<script src="../../../resources/js/app.js"></script>
</body>
</html>

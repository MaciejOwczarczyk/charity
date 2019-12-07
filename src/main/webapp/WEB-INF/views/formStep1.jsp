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
            <H2>Ważne!</H2>
            <H5>
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </H5>
        </div>
    </div>

    <br/>
    <br/>
    <br/>
    <br/>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form method="get" modelAttribute="donation" action="/donation/addStep2">
            <!-- STEP 1: class .active is switching steps -->
            <div>
                <h2>Zaznacz co chcesz oddać:</h2>

                <c:forEach items="${categories}" var="category">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="checkbox" name="categories" value="${category.id}"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>

                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>

                <div class="form-group form-group--buttons">
                    <input type="submit" class="btn next-step" value="Dalej">
                </div>
                <br/>
                <br/>
                <br/>
                <br/>
            </div>

        </form:form>
    </div>
</section>

<%@ include file="footer.jsp"%>

<script src="<c:url value='../../resources/js/app.js'/>" type="text/javascript"></script>
</body>
</html>

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
  <header>
    <nav class="container container--70">
      <ul class="nav--actions">
        <li><a href="/login">Zaloguj</a></li>
        <li class="highlighted"><a href="/register">Załóż konto</a></li>
      </ul>

      <ul>
        <li><a href="/donation/add" class="btn btn--without-border active">Start</a></li>
        <li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="#about-us" class="btn btn--without-border">O nas</a></li>
        <li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
      </ul>
    </nav>
  </header>

  <form:form method="post" modelAttribute="user">

    <section class="login-page">
      <h2>Załóż konto</h2>
      <form>
        <div class="form-group">
          <form:input path="username" type="email" placeholder="Użytkownik"/>
          <form:errors cssClass="error" path="username">Pole nie może być puste</form:errors>
          <c:if test="${registerFail == true}">
            <c:out value="Użytkownik już istnieje"/>
          </c:if>
        </div>
        <div class="form-group">
          <form:input path="firstName" type="text" placeholder="Imię"/>
          <form:errors cssClass="error" path="firstName">Pole nie może być puste</form:errors>
        </div>
        <div class="form-group">
          <form:input path="lastName" type="text" placeholder="Nazwisko"/>
          <form:errors cssClass="error" path="lastName">Pole nie może być puste</form:errors>
        </div>
        <div class="form-group">
          <form:input path="password" type="password" placeholder="Hasło"/>
          <form:errors cssClass="error" path="password">Pole nie może być puste</form:errors>
        </div>
        <div class="form-group form-group--buttons">
          <a href="/login" class="btn btn--without-border">Zaloguj się</a>

          <input type="submit" class="btn" value="Załóż konto">
        </div>
      </form>
    </section>
  </form:form>

  <%@ include file="footer.jsp"%>
  </body>
</html>

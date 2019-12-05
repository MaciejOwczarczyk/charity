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
  <%@ include file="../../../WEB-INF/views/header.jsp"%>

  <form:form method="post" modelAttribute="user">

    <section class="login-page">
      <h2>Załóż konto</h2>
      <form>
        <div class="form-group">
          <form:input path="username" type="email" placeholder="Użytkownik"/>
        </div>
        <div class="form-group">
          <form:input path="password" type="password" placeholder="Hasło"/>
        </div>
        <div class="form-group form-group--buttons">
          <a href="/login" class="btn btn--without-border">Zaloguj się</a>

          <input type="submit" class="btn" value="Załóż konto">
        </div>
      </form>
    </section>
  </form:form>

  <%@ include file="../../../WEB-INF/views/footer.jsp"%>
  </body>
</html>

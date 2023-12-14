<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <title>Document</title>
  </head>
  <body>
      <%@include file="fragment/navBar.jspf" %>
    <main>
        <form class="form-container" method="post" action="forminiciasesion">
        <div class="form-img-container">
          <img src="${pageContext.request.contextPath}/img/Rydon_300.jpg" alt="" />
        </div>
        <div class="input-container">
          <input type="text" required name="userName"/>
          <label for="">Nombre de usuario</label>
        </div>
        <div class="input-container">
          <input type="password" name="userPassword" required />
          <label for="">Contraseña</label>
        </div>
        <div class="input-container">
          <button class="submit" type="submit">Iniciar Sesion</button>
        </div>
        <div class="form-login-link">
          <a href="${pageContext.request.contextPath}/usuario/crearcuenta/">Crear cuenta</a>
        </div>
      </form>
    </main>
  </body>
</html>
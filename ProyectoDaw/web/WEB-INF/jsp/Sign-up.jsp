<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
        <title>funGames | Crear cuenta</title>
    </head>
    <body>
        <%@include file="fragment/navBar.jspf" %>
        <main>
            <form name="form-sign-up" class="form-container" method="post" action="${pageContext.request.contextPath}/usuario/formcrearcuenta/">
        <header class="main-title-container">
          <h1>Formulario de Registro</h1>
        </header>
        <div class="form-img-container">
          <img src="${pageContext.request.contextPath}/img/Rydon_300.jpg" alt="" />
        </div>
        <div class="input-container">
          <input id="userName" name="userName" type="text" />
          <label for="">Nombre de usuario</label>
        </div>
        <div class="input-container">
          <input id="userMail" name="userMail" type="email" />
          <label for="">Correo</label>
        </div>
        <div class="input-container">
          <input id="userPassword" name="userPassword" type="password" />
          <label for="">Contraseña</label>
        </div>
        <div class="input-container">
          <input name="userOtherPassword" type="password" />
          <label for="">Repite la contraseña</label>
          <span name="error-message" class="hidden-message"></span>
        </div>
        <div class="input-container">
          <button class="submit" type="submit">Registrarse</button>
        </div>
      </form>
    </main>
    </body>
</html>

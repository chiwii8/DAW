<%-- 
    Document   : ViewGame
    Created on : 20 dic 2023, 19:27:51
    Author     : alejandro
--%>

<%@page import="MVC.Models.Juego"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous"
            />
    </head>
    <body>
        <%@include file="fragment/navBar.jspf" %>
        <%@include file="fragment/ErrorMessage.jsp" %>

        <%
            Juego game = null;
            try {
                game = (Juego) request.getAttribute(JSP_NAME_ATTRIBUTE.GAME_ONE);
            } catch (Exception e) {
                System.out.println("Error obtenido " + e.getMessage());
            }
            System.out.println("valor de msg en viewGames" + msg);

            if (game != null && msg == null) {
                Long idgame = game.getId();
                String gameName = game.getSaga() + " " + game.getNombre();
                String gameImage = game.getUrlImagen() == null ? JSP_NAME_ATTRIBUTE.DEFAULT_IMAGE_URL : game.getUrlImagen();
                String gameVersion = game.getVersion();
                String gameDescription = game.getDescripcion();
        %>

        <div class ="d-flex mt-5 justify-content-center">
            <div class="card mb-3" style="max-width: 720px;">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src=<%=gameImage%> class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title"><strong>Titulo:</strong> <%=gameName%></h5>
                            <p class="card-text"><strong>Version:</strong> <%=gameVersion%></p>
                            <p class="card-text"><strong>Descripcion:</strong> <%=gameDescription%></p>
                            <%
                                if(gameImage!=JSP_NAME_ATTRIBUTE.DEFAULT_IMAGE_URL){
                            %>
                            <form action="${pageContext.request.contextPath}/descargarJuego/" method="post">
                                <input class="visually-hidden" name="GameFront" value= <%=gameImage%>> 
                                 <button type="submit" class="btn btn-primary">Descargar</button>
                            </form>
                           
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"
        ></script>
    </body>
</html>

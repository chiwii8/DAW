<%-- 
    Document   : myGames
    Created on : 19 dic 2023, 23:42:50
    Author     : alejandro
--%>

<%@page import="java.util.List"%>
<%@page import="MVC.Models.Juego"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous"
            />
        <title>Document</title>
    </head>
    <body>
        <%@include file="fragment/navBar.jspf" %>
        <main class="container-sm mt-5">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 g-5">
                <%                    ///Recuperamos los juegos
                    List<Juego> juegos = (List<Juego>) request.getAttribute(JSP_NAME_ATTRIBUTE.GAME_ALL);
                    if (juegos == null || juegos.isEmpty()) {
                    System.out.println("No tengo juegos propios");
                %>
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    No se han encontrado juegos
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%} else {
                    System.out.println("Tengo juegos propios");
                    for (Juego juego : juegos) {
                        String gameName = juego.getSaga() + " " + juego.getNombre();
                        String urlImage = juego.getUrlImagen() == null ? JSP_NAME_ATTRIBUTE.DEFAULT_IMAGE_URL : juego.getUrlImagen();
                        Long idgame = juego.getId();
                %>
                <div class="col">
                    <div class="card" style="max-width: 350px">
                        <img src=<%=urlImage%> class="card-img-top" alt="..." />
                        <div class="card-body text-center">
                            <form action="${pageContext.request.contextPath}/juego/verJuego/" method="post">
                                <button type="submit" class="btn btn-link card-title" name="idgame" value= <%=idgame%> > <%=gameName%> </button>
                            </form>
                            <form action="${pageContext.request.contextPath}/juego/borrarjuego/" method="post">
                                <input class="visually-hidden" name="idgame" value= <%=idgame%>> 
                                <button type="submit" class="btn btn-primary">Eliminar</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/juego/editarjuego/" method="post">
                                <input class="visually-hidden" name="idgame" value= <%=idgame%>> 
                                <button type="submit" class="btn btn-primary">Editar</button>
                            </form>
                        </div>
                    </div>
                </div>

                <%}
                    }%>

            </div>
        </main>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"
        ></script>
    </body>
</html>

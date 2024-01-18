<%-- 
    Document   : AllGames
    Created on : 21 dic 2023, 0:40:29
    Author     : alejandro
--%>

<%@page import="MVC.Models.Juego"%>
<%@page import="java.util.List"%>
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
        <%@include file="fragment/ErrorMessage.jsp" %>
        <form
            class="gradient-custom mt-5"
            >
            <div class="container py-5">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-12 col-lg-10 col-xxl-10 col-xl-9">
                        <div
                            class="card text-black"
                            style="border-radius: 1rem; background-color: #4578e7"
                            >
                            <div class="card-body p-5 text-center">
                                <div class="mb-md-2 mt-md-2">
                                    <h2 class="fw-bold mb-2 text-uppercase">
                                        Filtros
                                    </h2>

                                    <div class="form-outline form-white mt-4 mb-4 text-start">
                                        <label class="form-label" for="sageFilter">Saga</label>
                                        <input
                                            type="text"
                                            name="sageFilter"
                                            id="sageFilter"
                                            class="form-control"
                                            />
                                    </div>
                                    <div class="form-outline form-white mt-4 mb-4 text-start">
                                        <label class="form-label" for="nameFilter">Nombre</label>
                                        <input
                                            type="text"
                                            name="nameFilter"
                                            id="nameFilter"
                                            class="form-control"
                                            />
                                    </div>

                                    <button
                                        class="btn btn-outline-dark btn-lg px-5"
                                        type="button"
                                        onclick="filtrar();"
                                        id="filter"
                                        >
                                        Filtrar
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <main class="container-sm mt-5 mb-5">
            <h2>Juegos</h2>
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 g-5" id="listaJuegos">
                <%                    ///Recuperamos los juegos
                    List<Juego> juegos = (List<Juego>) request.getAttribute(JSP_NAME_ATTRIBUTE.GAME_ALL);
                    if (juegos == null || juegos.isEmpty()) {
                %>
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    No se han encontrado juegos
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%} else {
                    for (Juego juego : juegos) {
                        String gameName = juego.getSaga() + " " + juego.getNombre();
                        String urlImage = juego.getUrlImagen() == null ? JSP_NAME_ATTRIBUTE.DEFAULT_IMAGE_URL : juego.getUrlImagen();
                        Long idgame = juego.getId();
                %>
                <div class="col">
                    <div class="card" style="max-width: 350px">
                        <img src=<%=urlImage%> class="card-img-top" style="width:100%; max-height:350px" alt="..." />
                        <div class="card-body">
                            <form action="${pageContext.request.contextPath}/juego/verJuego/" method="post">
                                <button type="submit" class="btn btn-link card-title" name="idgame" value= <%=idgame%> > <%=gameName%> </button>
                            </form>
                        </div>
                    </div>
                </div>

                <%}
                    }%>
            </div>
        </main>
                    <script src="${pageContext.request.contextPath}/js/funciones.js" type="text/javascript"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"
        ></script>
    </body>
</html>

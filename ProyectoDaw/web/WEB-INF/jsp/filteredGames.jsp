<%-- 
    Document   : filteredGames
    Created on : 18 ene 2024, 10:42:07
    Author     : alejandro
--%>

<%@page import="MVC.Models.Constants.JSP_NAME_ATTRIBUTE"%>
<%@page import="MVC.Models.Juego"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <img src=<%=urlImage%> class="card-img-top" alt="..." />
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/juego/verJuego/" method="post">
                <button type="submit" class="btn btn-link card-title" name="idgame" value= <%=idgame%> > <%=gameName%> </button>
            </form>
        </div>
    </div>
</div>

<%}
                    }%>

<%-- 
    Document   : CRUDNews
    Created on : 19 dic 2023, 21:43:11
    Author     : alejandro
--%>

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
            method="post"
            action="${pageContext.request.contextPath}/usuario/formcrearnoticia/"
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
                                    <div class="text-center">
                                        <img
                                            src="${pageContext.request.contextPath}/img/Rydon_300_sinFondo.png"
                                            alt=""
                                            />
                                    </div>
                                    <h2 class="fw-bold mb-2 text-uppercase">
                                        Registro de Notica
                                    </h2>

                                    <div class="form-outline form-white mt-4 mb-4 text-start">
                                        <label class="form-label" for="title">Título</label>
                                        <input
                                            type="text"
                                            name="title"
                                            id="title"
                                            class="form-control"
                                            required
                                            />
                                    </div>

                                    <select
                                        class="form-select mb-4"
                                        name="typeNotice"
                                        aria-label="Default select example"
                                        >
                                        <option selected>Selecciona un tipo de noticia</option>
                                        <option value="1">One</option>
                                        <option value="2">Two</option>
                                        <option value="3">Three</option>
                                    </select>

                                    <div class="mb-3 text-start">
                                        <label for="formFile" class="form-label"
                                               >Imagen de referencia</label
                                        >
                                        <input
                                            class="form-control"
                                            type="file"
                                            accept="image/*"
                                            name="imagenReferencia"
                                            id="formFile"
                                            />
                                    </div>

                                    <div class="form-floating mb-4">
                                        <textarea
                                            class="form-control"
                                            placeholder="Leave a comment here"
                                            id="floatingTextarea2"
                                            name="description"
                                            style="height: 100px"
                                            required
                                            ></textarea>
                                        <label for="floatingTextarea2">Descripcion</label>
                                    </div>

                                    <button
                                        class="btn btn-outline-dark btn-lg px-5"
                                        type="submit"
                                        >
                                        Registrarse
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"
        ></script>
    </body>
</html>


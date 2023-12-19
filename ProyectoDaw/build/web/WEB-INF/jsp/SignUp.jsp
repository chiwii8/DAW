<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous"
            />
        <title>funGames | Crear cuenta</title>
    </head>
    <body>
        <%@include file="fragment/navBar.jspf" %>
        <%@include file="fragment/ErrorMessage.jsp" %>
        <form class="gradient-custom mt-5" method="post" action="${pageContext.request.contextPath}/usuario/formcrearcuenta/">
            <div class="container py-5">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div
                            class="card text-black"
                            style="border-radius: 1rem; background-color: #4578e7"
                            >
                            <div class="card-body p-5 text-center">
                                <div class="mb-md-2 mt-md-2">
                                    <div class="text-center">
                                        <img src="${pageContext.request.contextPath}/img/Rydon_300_sinFondo.png" alt="" />
                                    </div>
                                    <h2 class="fw-bold mb-2 text-uppercase">
                                        Registro de usuario
                                    </h2>

                                    <div class="form-outline form-white mt-4 mb-4 text-start">
                                        <label class="form-label" for="userName"
                                               >Nombre de Usuario</label
                                        >
                                        <input
                                            type="text"
                                            name="userName"
                                            id="userName"
                                            class="form-control"
                                            required
                                            />
                                    </div>

                                    <div class="form-outline form-white mb-4 text-start">
                                        <label class="form-label" for="userMail">Correo</label>
                                        <input
                                            type="email"
                                            id="userMail"
                                            name="userMail"
                                            class="form-control"
                                            required
                                            />
                                    </div>

                                    <div class="form-outline form-white mb-4 text-start">
                                        <label class="form-label" for="userPassword"
                                               >Contraseña</label
                                        >
                                        <input
                                            type="password"
                                            id="userPassword"
                                            name="userPassword"
                                            class="form-control"
                                            required
                                            />
                                    </div>

                                    <div class="form-outline form-white mb-4 text-start">
                                        <label class="form-label" for="userOtherPassword"
                                               >Repite la contraseña</label
                                        >
                                        <input
                                            type="password"
                                            id="userOtherPassword"
                                            name="userOtherPassword"
                                            class="form-control"
                                            required
                                            />
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

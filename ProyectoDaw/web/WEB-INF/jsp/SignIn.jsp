<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
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
        <main>
            <form class="gradient-custom" method="post" action="${pageContext.request.contextPath}/usuario/forminiciasesion/">
                <div class="container py-5">
                    <div class="row d-flex justify-content-center align-items-center">
                        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                            <div class="card text-black" style="border-radius: 1rem; background-color: #4578e7">
                                <div class="card-body p-5 text-center">
                                    <div class="mb-md-2 mt-md-2">
                                        <div class="text-center">
                                            <img src="${pageContext.request.contextPath}/img/Rydon_300_sinFondo.png" alt="" />
                                        </div>
                                        <h2 class="fw-bold mb-2 text-uppercase">Inicio de sesión</h2>

                                        <div class="form-outline form-white mt-4 mb-4">
                                            <label class="form-label" for="userName">Usuario</label>
                                            <input
                                                type="text"
                                                id="userName"
                                                name ="userName"
                                                class="form-control form-control-lg"
                                                />
                                        </div>

                                        <div class="form-outline form-white mb-4">
                                            <label class="form-label" for="userPassword"
                                                   >Password</label
                                            >
                                            <input
                                                type="password"
                                                name = "userPassword"
                                                id="userPassword"
                                                class="form-control form-control-lg"
                                                />
                                        </div>

                                        <button
                                            class="btn btn-outline-dark btn-lg px-5"
                                            type="submit"
                                            >
                                            Login
                                        </button>

                                        <div
                                            class="d-flex text-center mt-3"
                                            >
                                            <a href="#!" class="text-white"
                                               ><i class="fab fa-facebook-f fa-lg"></i
                                                ></a>
                                            <a href="#!" class="text-white"
                                               ><i class="fab fa-twitter fa-lg mx-4 px-2"></i
                                                ></a>
                                            <a href="#!" class="text-white"
                                               ><i class="fab fa-google fa-lg"></i
                                                ></a> 
                                            <p class="mb-0">
                                                ¿No tienes cuenta?,
                                                <a href="${pageContext.request.contextPath}/usuario/crearcuenta/" class="text-white-50 fw-bold">Crear cuenta</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </main>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
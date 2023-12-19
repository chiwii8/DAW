<%-- 
    Document   : myGames
    Created on : 19 dic 2023, 23:42:50
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
        <main class="container-sm mt-5">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 g-5">
                <div class="col">
                    <div class="card" style="max-width: 350px">
                        <img src="..." class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">
                                This is a longer card with supporting text below as a natural
                                lead-in to additional content. This content is a little bit
                                longer.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card" style="max-width: 350px">
                        <img src="..." class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">
                                This is a longer card with supporting text below as a natural
                                lead-in to additional content. This content is a little bit
                                longer.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card" style="max-width: 350px">
                        <img src="..." class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">
                                This is a longer card with supporting text below as a natural
                                lead-in to additional content.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card" style="max-width: 350px">
                        <img src="..." class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">
                                This is a longer card with supporting text below as a natural
                                lead-in to additional content. This content is a little bit
                                longer.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"
        ></script>
    </body>
</html>

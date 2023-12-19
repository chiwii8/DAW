<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <section class="gradient-custom">
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
                    <img src="img/Rydon_300_sinFondo.png" alt="" />
                  </div>
                  <h2 class="fw-bold mb-2 text-uppercase">Registro de Juego</h2>

                  <div class="form-outline form-white mt-4 mb-4 text-start">
                    <label class="form-label" for="userName"
                      >saga del juego</label
                    >
                    <input
                      type="text"
                      name="userName"
                      id="userName"
                      class="form-control"
                    />
                  </div>

                  <div class="form-outline form-white mb-4 text-start">
                    <label class="form-label" for="userMail"
                      >Nombre del juego</label
                    >
                    <input
                      type="email"
                      id="userMail"
                      name="userMail"
                      class="form-control"
                    />
                  </div>
                  <!-- Añadir aqui un fragmento JSP que nos permita generar las option-->
                  <div class="pt-3">
                    <select
                      class="form-select mb-4"
                      aria-label="Default select example"
                    >
                      <option selected>Selecciona una version</option>
                      <option value="1">One</option>
                      <option value="2">Two</option>
                      <option value="3">Three</option>
                    </select>
                  </div>

                  <!--En este apartado se insertarán los elemento seleccionados-->
                  <div id="Element-Container-Generate"></div>

                  <div class="container justify-content-between mt-5">
                    <div class="row">
                      <div class="col-md mb-2">
                        <select
                          class="form-select"
                          id="select-creator"
                          aria-label="Default select example"
                        >
                          <option selected>Selecciona una Opcion</option>
                          <option value="1">One</option>
                          <option value="2">Two</option>
                          <option value="3">Three</option>
                        </select>
                      </div>
                      <div class="col">
                        <button
                          class="btn btn-outline-dark btn-lg py-1 px-4"
                          id="btn-new-features"
                        >
                          Generar Apartado
                        </button>
                      </div>
                    </div>
                  </div>

                  <button
                    class="btn btn-outline-dark btn-lg px-5 mt-4"
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
    </section>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"
    ></script>

    <script>
      const generator = document.getElementById("btn-new-features");
      generator.addEventListener("click", (e) => {
        e.defaultPrevented();
        const optionSelected = document.getElementById("select-creator").value;
        let newElement;
        switch (optionSelected) {
          case "text":
            newElement = `<p>El elemento creado es texto</p>`;
            break;
          default:
            newElement = `<input type=text placeholder='Elemento Creado con js'>`;
        }

        const contenedor = document.getElementById(
          "Element-Container-Generate"
        );
        contenedor.innerHTML += newElement;
      });
    </script>
  </body>
</html>

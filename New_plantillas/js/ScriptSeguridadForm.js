let idAcordeon = 0;
      let buttonInputAccordionID;

      const DEFAULT_ACORDEON_ID = "acordeon";
      const DEFAUL_ACORDEON_CONTAINER = "Container-GeneratedElement";
      ///Listeners addAcordeon
      const btn_addAcordeon = document.getElementById("btn-new-features");
      btn_addAcordeon.addEventListener("click", (e) => {
        e.preventDefault();
        const selectedOption = document.getElementById("select-creator").value;
        if (selectedOption !== undefined) {
          ///Instanciamos el contenedor principal
          const mainContainer = document.getElementById(
            DEFAUL_ACORDEON_CONTAINER
          );

          ///Definimos el id del nuevo acordeón
          let newAccordionID = DEFAULT_ACORDEON_ID + idAcordeon;

          ///Creamos el acordeon
          let newAccordion = createAccordion(newAccordionID, selectedOption);

          ///Insertamos el acordeon en el container principal
          //mainContainer.appendChild(newAccordion);
          mainContainer.appendChild(newAccordion);

          ///Recuperamos el botón de eliminar
          let buttonRemove = document.getElementById(buttonInputAccordionID);
          console.log("el botton de remover es ", buttonRemove);
          ///Se añaden el eventListener del click
          buttonRemove.addEventListener("click", function (event) {
            let accordeonID = this.id.split("-")[0]; ///acordeon + nº que le corresponde
            console.log(accordeonID);
            const accordeonContainer = document.getElementById(accordeonID);

            ///Verificamos si tiene el elemento padre (DIV)
            if (accordeonContainer.contains(this.parentNode)) {
              ///Si lo tiene lo elimina
              accordeonContainer.remove(this.parentNode);

              ///Tras eliminarlo, verificamos si el accordeon tiene más nodos del tipo <div class="input-form">
              if (
                accordeonContainer.querySelectorAll(".input-group").length == 0
              ) {
                ///Recuperamos el contenedor principal
                const mainContainer = document.getElementById(
                  DEFAUL_ACORDEON_CONTAINER
                );

                ///Eliminamos el container ya que ha perdido su funcionalidad
                mainContainer.removeChild(accordeonContainer);
              }
            }
          });

          ///Finalizamos el listener
          ///Sumando un nuevo acordeon con el otro método
          idAcordeon++;
        }
      });

      ///Crea un elemento
      function createElement(PreIdElement, typeField) {
        ///Creamos el contenedor del input
        const newElement = document.createElement("div");
        newElement.classList.add("input-group", "mb-3");

        ///Llamamos al acordeon que estamos creando para ver que ID le tenemos que insertar al elemento
        const accordion = document.getElementById(PreIdElement);

        ///Creamos la variable que indicará el ID del input
        let newElementID;

        if (accordion === null || accordion === undefined) {
          newElementID = 0;
        } else {
          newElementID = accordion.hasChildNodes()
            ? accordion.childElementCount
            : 0;
        }

        ///Creamos un Id acorde al ID del padre para evitar que haya dos ID iguales
        ///Independiente del tipo de input que sea se le asigna el ID
        newElement.setAttribute("id", PreIdElement + "-Input-" + newElementID);

        ///Creamos el input que queremos
        ///Falta añadir las clases así como los atributos que sean necesarios
        let newInputElement;
        switch (typeField) {
          case "text":
            newInputElement = document.createElement("textarea");
            break;
          case "feature":
            newInputElement = document.createElement("input");
            newInputElement.setAttribute("type", "text");
            break;
          case "image":
            newInputElement = document.createElement("input");
            newInputElement.setAttribute("type", "file");
            newInputElement.setAttribute("accept", "image/*");
            break;
          default:
            ///No se debería dar este caso pero, por precaución creamos un textArea como inputElement
            newInputElement = document.createElement("textarea");
        }

        newInputElement.classList.add("form-control");
        ///Insertamos el nombre al input element, el nombre que tendrá el array de elementos
        ///que se crea
        newInputElement.setAttribute("name", PreIdElement + "[]");
        newElement.appendChild(newInputElement);

        ///Creamos el button para eliminar dicho elemento
        const newButtonElement = document.createElement("button");

        ///Guardamos el ID del button
        buttonInputAccordionID = PreIdElement + "-btn-Remove";

        ///Seteamos sus atributos,clases,texto
        newButtonElement.setAttribute("id", PreIdElement + "-btn-Remove");
        newButtonElement.setAttribute("type", "button");
        newButtonElement.classList.add("btn", "btn-danger");
        newButtonElement.innerText = "X";

        ///No funciona
        /*newButtonElement.addEventListener("click", function (event) {
          console.log("hacer Algo");

          /// button -> Element(div) -> Accordeon
          const accordeonContainer = document.getElementById(
            this.parentNode.parentNode
          );

          ///Eliminamos este Button -> Element(div)
          accordeonContainer.removeChild(this.parentNode);

          ///Verificamos si existe un hijo en el accordeon
          if (!accordeonContainer.hasChildNodes()) {
            ///Si no tiene hijos, llamamos al contenedor principal
            const mainContainer = document.getElementById(
              DEFAUL_ACORDEON_CONTAINER
            );

            ///Eliminamos el accordeon que a perdido su funcionalidad
            mainContainer.removeChild(accordeonContainer);
          }
        });*/

        newButtonElement.setAttribute =
          ("onClick",
          function () {
            console.log("El botón ha sido pulsado");
          });

        ///Insertamos el button en el nuevo elemento
        newElement.appendChild(newButtonElement);
        ///Creamos un eventListener que nos permite eliminar El input asociado

        console.log(newElement);
        return newElement;
      }

      function handleClickRemover(node) {
        console.log(node.parentNode);
        const accordeonContainer = document.getElementById(node.parentNode);

        ///Eliminamos este Button->Element(div)
        accordeonContainer.removeChild(node);

        ///Verificamos si existe un hijo en el accordeon
        if (!accordeonContainer.hasChildNodes()) {
          ///Si no existe, llamamos al contenedor principal
          const mainContainer = document.getElementById(
            DEFAUL_ACORDEON_CONTAINER
          );

          ///Eliminamos el accordeon que a perdido su funcionalidad
          mainContainer.removeChild(accordeonContainer);
        }
      }

      ///Creamos un accordeon, como lo inicialiazamos no es necesario preocuparnos por las ID's de los
      ///elementos
      function createAccordion(idAcordeon, typeField) {
        ///Insertamos el ID del item del acordeon
        const divElement = document.createElement("div");
        divElement.classList.add("accordion-item");
        divElement.id = idAcordeon;
        divElement.appendChild(createAccordionHeader(idAcordeon));
        divElement.appendChild(createAccordionBody(idAcordeon, typeField));

        return divElement;
        /*return `<div class="accordion-item" id="${idAcordeon}">
                ${createAcordionHeader(idAcordeon)}
                ${createAccordionBody(idAcordeon, typeField)}
            </div>`;*/
      }

      function createAccordionHeader(idAcordeon) {
        ///En la
        let newID = idAcordeon + "-body";
        // Crear el elemento h2
        const h2Element = document.createElement("h2");
        h2Element.classList.add("accordion-header");

        // Crear el elemento button
        const buttonElement = document.createElement("button");
        buttonElement.classList.add("accordion-button", "collapsed");
        buttonElement.setAttribute("type", "button");
        buttonElement.setAttribute("data-bs-toggle", "collapse");
        buttonElement.setAttribute("data-bs-target", `#${newID}`);
        buttonElement.setAttribute("aria-expanded", "true");
        buttonElement.setAttribute("aria-controls", `${newID}`);
        buttonElement.textContent = idAcordeon;

        // Adjuntar el botón al elemento h2
        h2Element.appendChild(buttonElement);

        return h2Element;

        /*return `<h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#${newID}" aria-expanded="true" aria-controls="${newID}">
                    ${idAcordeon}
                </button>
            </h2>`;*/
      }

      function createAccordionBody(idAcordeon, typeField) {
        let newID = idAcordeon + "-body";

        // Crear el elemento div
        const divElement = document.createElement("div");
        divElement.id = newID;
        divElement.classList.add("accordion-collapse", "collapse");
        divElement.setAttribute(
          "data-bs-parent",
          `#${DEFAUL_ACORDEON_CONTAINER}`
        );

        // Crear el elemento div interno (accordion-body) y establecer su contenido
        const innerDivElement = document.createElement("div");
        innerDivElement.classList.add("accordion-body", "mt-2");
        innerDivElement.appendChild(createElement(idAcordeon, typeField));

        // Adjuntar el div interno al div principal
        divElement.appendChild(innerDivElement);

        return divElement;

        /*return `<div id="${newID}" class="accordion-collapse collapse" data-bs-parent="#${DEFAUL_ACORDEON_CONTAINER}">
                  <div class="accordion-body mt-2">
                    ${createElement(idAcordeon, typeField)}
                  </div>
            </div>
        `;*/
      }

      /*
        <div class="accordion-item">
          <h2 class="accordion-header">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
            Accordion Item #1
            </button>
          </h2>
          <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <strong>This is the first item's accordion body.</strong> It is shown by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
          </div>
        </div>
      </div>


      */
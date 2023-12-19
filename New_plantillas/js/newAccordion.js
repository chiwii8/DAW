export function createAccordion(id, dinamicField) {
    return `<div class="accordion-item">
                ${createAcordionHeader(id)}
                ${createAccordionBody(id, dinamicField)}
            </div>`;
      }

function createAcordionHeader(id) {
    return `<h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${id}" aria-expanded="true" aria-controls="collapse${id}">
                    Acordeón ${id} 
                </button>
            </h2>`;
}

function createAccordionBody(id, dinamicField) {
    return `<div id="collapse${id}" class="accordion-collapse collapse" data-bs-parent="#Element-Container-Generate"
                <div class="accordion-body mt-2">
                    ${dinamicField}
                </div>
            </div>
        `;
}


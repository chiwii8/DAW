const btnNewFeatures = document.getElementById('btn-new-features')
const select = documetn.getElementById('select-creator')
btn.addEventListener('click',(e)=>{
    e.preventDefault()
    const selectedOption = searchSelectedOption(select.options)
    let newElement;

    switch(selectedOption){
        case 'text':
            newElement = createText();
            break;
        case 'features':
            newElement = createFeatures();
            break;
        case 'dashboard':
            newElement = createDashBoard();
            break;
        default:
            break;

    }
})

/**
 * Nos permite buscar el elemento seleccionado dentro de las posibles opciones
 * @param {*} options array de opciones
 * @returns la posicion del elemento seleccionado
 */
function searchSelectedOption(options){
    let i = 0;
    let finded = false;
    while(i<options.length && !finded){
        if(options[i].selected){
            finded = true;
        }else{i++;}
    }
    return i;
}

/**
 * Nos permite crear un elemento para la escritura de párrafos
 * @returns Cadena HTML para un input de escritura
 */
function createText(){
    return `<textarea></textarea>`
}

/**
 * Nos permite crear una serie de características
 * @returns Cadena HTML para una lista de características
 */
function createFeatures(){
    return `<ul><li></li></ul>`
}

/**
 * Nos permite crear el HTML necesario para un mural de imágenes
 * @returns Cadena HTML para un mural
 */
function createDashBoard(){
    return `<ul><li><li/></ul>`
}
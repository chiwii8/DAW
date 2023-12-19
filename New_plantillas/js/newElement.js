/**
 * Nos permite generar un elemento de nuestra preferencia según la opcion seleccionada
 * @param {*} option valor representativo de la opcion
 * @returns la cadena HTML representativa de ese elemento
 */
function createElement(option){
    let newElement;
    switch(option){
        case 'text':
            newElement = `<p></p>`
            break;
        case 'feature':
            newElement = `<p></p>`
            break;
        case 'image':
            newElement = `<p></p>`
            break;
        default:
    }

    return newElement

}

/**
 * Nos permite eliminar un Accordion del conjunto padre
 * @param {*} element El id del Accordion a eliminar 
 */
function deleteContainer(element){
    const container = document.getElementById('Element-Container-Generate')
    container.removeChild(element)
}
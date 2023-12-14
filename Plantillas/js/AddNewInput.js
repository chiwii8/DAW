var acc = document.getElementById("Nueva-caracteristica")
var Container = document.getElementById("Caracteristicas-Container")

acc.addEventListener('click',function(){
    let newDiv = document.createElement("div")
    let newInput = document.createElement("input")
    newInput.type='text'
    newInput.classList.toggle('reduce-input')

    let newdeleteButton = document.createElement('button')
    newdeleteButton.classList.toggle('action-button')
    newdeleteButton.type='button'
    newdeleteButton.innerText='X'

    
    newDiv.classList.add('input-container','inline-input')

    //Lo introducimos antes de los botones
    newDiv.appendChild(newInput)
    newDiv.appendChild(newdeleteButton)

    Container.appendChild(newDiv)
})
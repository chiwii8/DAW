const errorMessage = document.getElementsByName('error-message')[0]

document.getElementsByName('form-sign-up')[0]
    .addEventListener('submit',(e)=>{
        let error = checkUserName()

        console.log('Este es un error',error);
        if(error === undefined){
            console.log('Error es undefined');
            error = checkEmail()
            if(error === undefined){
                error = checkPassword()
                if(error === undefined)
                    error = checkRepitPassword()
            }
                
        }

        console.log('Llega hasta aqui al menos');
        if(error!==undefined){
            e.preventDefault();
            errorMessage.innerHTML = error
            errorMessage.classList.remove('hidden-message')
        }else{
            ///Realiza la operacion que le corresponda
            ///realiza una llamada con el servlet, y trás validar los datos lo lleva a la pantalla principal
            
        }
    })
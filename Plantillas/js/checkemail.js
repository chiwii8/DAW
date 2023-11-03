function checkEmail(){
    const email = document.getElementsByName('email')[0].value
    const pattern = new RegExp( /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/)
    let error = undefined

    if(!pattern.test(email))
        error = 'El formato del correo no es válido'

    return error
}
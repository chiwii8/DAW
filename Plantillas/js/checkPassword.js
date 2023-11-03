function checkPassword(){
    let error = undefined
    const password = document.getElementsByName('password')[0].value

    if(password.length<8)
        error = 'Necesitas al menos 8 caracteres'

    return error
}
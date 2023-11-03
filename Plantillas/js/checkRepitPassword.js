function checkRepitPassword(){
    const password = document.getElementsByName('password')[0].value
    const repitPassword = document.getElementsByName('repit-password')[0].value
    let error = undefined

    if(password!==repitPassword)
        error = 'Las contraseñas son distintas'

    return error
}
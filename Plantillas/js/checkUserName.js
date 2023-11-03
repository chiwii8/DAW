function checkUserName(){
    let error = undefined
    const userName = document.getElementsByName('userName')[0].value
    console.log('este es el username',userName);
     if( userName.length===0 )
        error = 'Es necesario tener un nombre de usuario'

    return error 
}
const MenuICon = document.querySelector('.Menu-Icon')
const MenuFadeDown = document.querySelector('.Menu')
MenuICon.addEventListener('click',(e)=>{
    MenuICon.classList.toggle('open')
    MenuFadeDown.classList.toggle('showMenu')
})

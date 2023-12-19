import { createAccordion } from "./newAccordion";
import {createElement} from './newElement'

let idAccordion = 0;
const btn_generator = document.getElementById('btn-new-features')

btn_generator.addEventListener('click',(e)=>{
    e.preventDefault();
    const optionSelected = document.getElementById('select-creator').ariaValueMax;


    let newElement = createElement(optionSelected)

    if(newElement!==undefined){
        const container = document.getElementById('Element-Container-Generate')
        let newAccordion = createAccordion(idAccordion,newElement);
        container.innerHTML += newAccordion;
        idAccordion++;
    }

})

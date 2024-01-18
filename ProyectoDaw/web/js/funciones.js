/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var xhr;

function init_ajax() {
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }

}

function filtrar() {

    init_ajax();

    var url = "filtro";
    xhr.open("POST", url, true);
    xhr.onreadystatechange = juegosFiltrados;

    var saga = document.getElementById("sageFilter");
    var nombre = document.getElementById("nameFilter");

    var datos = "sageFilter=" + encodeURIComponent(saga.value) +
            "&nameFilter=" + encodeURIComponent(nombre.value);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(datos);

}

function juegosFiltrados() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            document.getElementById("listaJuegos").innerHTML = xhr.responseText;
        }
    }

}
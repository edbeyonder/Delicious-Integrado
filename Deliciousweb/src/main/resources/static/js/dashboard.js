//========================================
// OBTENER EL SELECT
//========================================

const menu = document.getElementById("menu");

//========================================
// ESCUCHAR EL CAMBIO DEL SELECT
//========================================
menu.addEventListener("change", function(){

    if(menu.value !== ""){

        window.location.href = menu.value;

    }

});
//========================================
// MENÚ DESPLEGABLE
//========================================

const botones = document.querySelectorAll(".menu-btn");
botones.forEach(function(boton){

    boton.addEventListener("click", function(){

        const submenu = boton.nextElementSibling;

        submenu.classList.toggle("activo");

    });

});

function cerrarSesion(){

    if(confirm("¿Desea cerrar sesión?")){

        window.location.href = "login.html";

    }

}
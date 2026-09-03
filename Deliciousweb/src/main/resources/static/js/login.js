const correo = document.getElementById("correo");
const password = document.getElementById("password");
const btnLogin = document.getElementById("btnLogin");


btnLogin.addEventListener("click", function(){

    if(correo.value === ""){
        alert("Ingrese su correo.");
        return;
    }
    if(!correo.value.includes("@") || !correo.value.includes(".")){
        alert("Ingrese un correo válido.");
        return;
    }

    if(password.value === ""){
        alert("Ingrese su contraseña.");
        return;
    }

    alert("Inicio de sesión correcto.");
    window.location.href = "dashboard.html";
});
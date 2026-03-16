

// Esto lo que resta para que pueda validar al ingresar el correo
function entrarACrowd() {
    // Obtenemos lo que el usuario escribió
    const email = document.getElementById('emailInput').value;
    const pass = document.getElementById('passInput').value;

    // Validación: que el correo tenga un formato básico y la contraseña no esté vacía
    if (email.includes("@") && email.includes(".") && pass.length >= 4) {

        // Animación de salida para el login
        const loginSection = document.getElementById('seccion-login');
        loginSection.style.opacity = "0";
        loginSection.style.transition = "opacity 0.5s ease";

        // Cambiamos las pantallas después de medio segundo
        setTimeout(() => {
            loginSection.classList.add('hidden'); // Escondemos Login

            const home = document.getElementById('pantalla-inicio');
            home.classList.remove('hidden'); // Mostramos Inicio
            home.style.animation = "aparecer 1s ease-out"; // Usamos tu animación de CSS
        }, 500);

    } else {
        alert("Por favor, ingresa un correo válido y una contraseña de al menos 4 caracteres.");
    }
}
function cerrarSesion() {
    // Volvemos a ocultar el inicio y mostrar el login
    document.getElementById('pantalla-inicio').classList.add('hidden');
    document.getElementById('seccion-login').classList.remove('hidden');

    // Opcional: Limpiar los campos de texto
    document.getElementById('emailInput').value = "";
    document.getElementById('passInput').value = "";
}
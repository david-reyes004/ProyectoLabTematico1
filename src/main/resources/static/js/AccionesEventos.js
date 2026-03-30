document.addEventListener('DOMContentLoaded', function() {
    let usuario = localStorage.getItem('usuarioActual');
    if (!usuario) {
        window.location.href = 'iniciarSesion.html';
        return;
    }

    // Personalizar el título con el nombre del usuario
    document.getElementById('tituloBienvenida').innerText = `Mis Eventos - ${usuario}`;

    cargarEventosLocales(usuario);
    cargarPresentacionesLocales(usuario);
    cargarBoletosServidor();
});

// --- FUNCIONES DE CARGA ---

function cargarEventosLocales(usuario) {
    let clave = `misEventos_${usuario}`;
    let eventos = JSON.parse(localStorage.getItem(clave)) || [];
    let tabla = document.getElementById("tablaMisEventos");
    tabla.innerHTML = "";

    eventos.forEach(e => {
        let fechaLimpia = e.fecha.split('T')[0];
        let fechaFormateada = fechaLimpia.split('-').reverse().join('/');
        tabla.innerHTML += `<tr>
            <td>${e.id}</td>
            <td>${e.nombre}</td>
            <td>${fechaFormateada}</td>
            <td>${e.hora}</td>
            <td><button onclick="eliminarEvento(${e.id})" style="cursor:pointer; background:none; border:none;">❌</button></td>
        </tr>`;
    });
}

function cargarPresentacionesLocales(usuario) {
    let clave = `misPresentaciones_${usuario}`;
    let presentaciones = JSON.parse(localStorage.getItem(clave)) || [];
    let tabla = document.getElementById("tablaMisPresentaciones");
    tabla.innerHTML = "";

    presentaciones.forEach(p => {
        tabla.innerHTML += `<tr>
            <td>${p.id}</td>
            <td>${p.eventoNombre}</td>
            <td>${p.artistaId}</td>
            <td>${p.hora}</td>
            <td><button onclick="eliminarPresentacion(${p.id})" style="cursor:pointer; background:none; border:none;">❌</button></td>
        </tr>`;
    });
}

async function cargarBoletosServidor() {
    const tabla = document.getElementById("tablaMisBoletos");
    try {
        const respuesta = await fetch('http://localhost:8080/boletos');
        const boletos = await respuesta.json();

        tabla.innerHTML = "";
        boletos.forEach(b => {
            tabla.innerHTML += `<tr>
                <td>#${b.id}</td>
                <td>${b.nombre}</td>
                <td>$${b.precio}</td>
                <td>${b.eventoId}</td>
            </tr>`;
        });
    } catch (error) {
        console.error("Error al conectar con el servidor para boletos:", error);
    }
}

// --- FUNCIONES DE ELIMINAR Y SESIÓN ---

function eliminarEvento(id) {
    let usuario = localStorage.getItem('usuarioActual');
    let clave = `misEventos_${usuario}`;
    let eventos = JSON.parse(localStorage.getItem(clave)) || [];
    eventos = eventos.filter(e => e.id !== id);
    localStorage.setItem(clave, JSON.stringify(eventos));
    cargarEventosLocales(usuario);
}

function eliminarPresentacion(id) {
    let usuario = localStorage.getItem('usuarioActual');
    let clave = `misPresentaciones_${usuario}`;
    let presentaciones = JSON.parse(localStorage.getItem(clave)) || [];
    presentaciones = presentaciones.filter(p => p.id !== id);
    localStorage.setItem(clave, JSON.stringify(presentaciones));
    cargarPresentacionesLocales(usuario);
}

function cerrarSesion() {
    if(confirm("¿Seguro que quieres cerrar sesión?")) {
        localStorage.removeItem('usuarioActual');
        window.location.href = 'iniciarSesion.html';
    }
}
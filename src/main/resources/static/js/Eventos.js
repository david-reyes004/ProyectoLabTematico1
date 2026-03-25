document.addEventListener("DOMContentLoaded", event => {
    cargarEventos();
});

async function cargarEventos() {
    const contenedor = document.getElementById('ContenedorDeEventos');
    try {
        const respuesta = await fetch('http://localhost:8080/eventos');
        const eventos = await respuesta.json();

        contenedor.innerHTML = '';
        eventos.forEach(evento => {
            contenedor.innerHTML += `
                <div class="evento-card">
                    <h3>#${evento.id}</h3>
                    <hr class="linea">
                    <p>${evento.nombre}</p>
                    <hr class="linea">
                    <p>Fecha: ${new Date(evento.fecha).toLocaleDateString('es-MX')}</p>
                    <p>Hora: ${evento.horaInicio} hrs</p>
                    <br>
                    <button class="btn-comprar" onclick="guardarEvento(${evento.id}, '${evento.nombre}', '${evento.fecha}', '${evento.horaInicio}')">GUARDAR BOLETOS</button>
                </div>
            `;
        });
    } catch (error) {
        contenedor.innerHTML = '<p>ERROR AL CONECTAR CON EL SERVIDOR</p>';
    }
}

function guardarEvento(id, nombre, fecha, hora) {
    let usuario = localStorage.getItem('usuarioActual');

    if (!usuario) {
        alert('Debes iniciar sesión para guardar eventos');
        return;
    }

    let clave = `misEventos_${usuario}`;
    let misEventos = JSON.parse(localStorage.getItem(clave)) || []; // ← declarado aquí

    let yaExiste = misEventos.some(e => e && e.id === id);
    if (yaExiste) {
        alert('Este evento ya está guardado');
        return;
    }

    misEventos.push({ id, nombre, fecha, hora });
    localStorage.setItem(clave, JSON.stringify(misEventos));
    alert('Evento guardado correctamente');
}
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
                    <h3>Evento #${evento.id}</h3>
                    <p>Nombre: ${evento.nombre}</p>
                    <p>Fecha: ${evento.fecha}</p>
                    <h3>Hora: ${evento.horaInicio}hrs</h3>
                    <button class="btn-comprar">VER BOLETOS</button>
                </div>
            `;
        });
    } catch (error) {
        contenedor.innerHTML = '<p>ERROR AL CONECTAR CON EL SERVIDOR</p>';
    }
}
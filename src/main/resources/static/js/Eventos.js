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
            // Aquí es donde agregamos el botón de compra al lado del de guardar
            contenedor.innerHTML += `
                <div class="evento-card" style="cursor: pointer;" onclick="abrirModal(${evento.id}, '${evento.nombre}')">
                    <h3>#${evento.id}</h3>
                    <hr class="linea">
                    <p><strong>${evento.nombre}</strong></p>
                    <hr class="linea">
                    <p>Fecha: ${new Date(evento.fecha).toLocaleDateString('es-MX')}</p>
                    <p>Hora: ${evento.horaInicio} hrs</p>
                    <br>
                    <div class="botones-container">
                        <button class="btn-guardar" onclick="event.stopPropagation(); guardarEvento(${evento.id}, '${evento.nombre}', '${evento.fecha}', '${evento.horaInicio}')">⭐ GUARDAR</button>
                        <button class="btn-comprar-real" onclick="event.stopPropagation(); comprarBoleto(${evento.id}, '${evento.nombre}')">🎫 COMPRAR</button>
                    </div>
                </div>
            `;
        });
    } catch (error) {
        contenedor.innerHTML = '<p>ERROR AL CONECTAR CON EL SERVIDOR</p>';
    }
}

// --- LOGICA DE COMPRA (La que ya tenías) ---
async function comprarBoleto(eventoId, nombreEvento) {
    const usuarioEmail = localStorage.getItem('usuarioActual');
    if (!usuarioEmail) {
        alert('¡Hey! Inicia sesión para comprar tu boleto.');
        return;
    }

    const datosBoleto = {
        nombre: `General - ${nombreEvento}`,
        precio: 1500.00,
        eventoId: eventoId
    };

    try {
        const respuesta = await fetch('http://localhost:8080/boletos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datosBoleto)
        });

        if (respuesta.ok) {
            const resultado = await respuesta.json();
            alert(`¡Compra exitosa! 🎉 Tu boleto para ${nombreEvento} tiene el ID: ${resultado.id}`);
        } else {
            alert('Error en el servidor al procesar la compra.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('No se pudo conectar con el servidor.');
    }
}

// --- RESTO DE TUS FUNCIONES (Abajo se quedan igual) ---

async function abrirModal(eventoId, nombreEvento) {
    document.getElementById('modalPresentaciones').style.display = 'block';
    document.getElementById('tituloModal').innerText = `Presentaciones de: ${nombreEvento}`;
    const contenedor = document.getElementById('listaPresentaciones');
    contenedor.innerHTML = '<p>Buscando presentaciones...</p>';

    try {
        const respuesta = await fetch('http://localhost:8080/api/presentaciones');
        const todasLasPresentaciones = await respuesta.json();
        const presentacionesDelEvento = todasLasPresentaciones.filter(p => p.eventoId === eventoId);

        if (presentacionesDelEvento.length === 0) {
            contenedor.innerHTML = '<p>Aún no hay artistas confirmados para este evento.</p>';
            return;
        }

        contenedor.innerHTML = '';
        presentacionesDelEvento.forEach(p => {
            contenedor.innerHTML += `
                <div class="presentacion-card">
                    <div>
                        <h3 style="margin:0; color:#00d2ff;">Artista ID: ${p.artistaId}</h3>
                        <p style="margin:5px 0;">Escenario ID: ${p.escenarioId}</p>
                        <p style="margin:0; color:#ccc;">Horario: ${p.hora_presentacion} hrs</p>
                    </div>
                    <button class="btn-presentacion" onclick="guardarPresentacion(${p.id}, ${p.artistaId}, '${nombreEvento}', '${p.hora_presentacion}')">AGREGAR A MI LISTA</button>
                </div>
            `;
        });
    } catch (error) {
        contenedor.innerHTML = '<p>Error al cargar las presentaciones.</p>';
    }
}

function cerrarModal() {
    document.getElementById('modalPresentaciones').style.display = 'none';
}

function guardarPresentacion(id, artistaId, eventoNombre, hora) {
    let usuario = localStorage.getItem('usuarioActual');
    if (!usuario) { alert('Debes iniciar sesión'); return; }
    let clave = `misPresentaciones_${usuario}`;
    let misPresentaciones = JSON.parse(localStorage.getItem(clave)) || [];
    if (misPresentaciones.some(p => p && p.id === id)) { alert('Ya guardaste esta presentación'); return; }
    misPresentaciones.push({ id, artistaId, eventoNombre, hora });
    localStorage.setItem(clave, JSON.stringify(misPresentaciones));
    alert('¡Presentación guardada en tu itinerario!');
}

function guardarEvento(id, nombre, fecha, hora) {
    let usuario = localStorage.getItem('usuarioActual');
    if (!usuario) { alert('Debes iniciar sesión para guardar eventos'); return; }
    let clave = `misEventos_${usuario}`;
    let misEventos = JSON.parse(localStorage.getItem(clave)) || [];
    if (misEventos.some(e => e && e.id === id)) { alert('Este evento ya está guardado'); return; }
    misEventos.push({ id, nombre, fecha, hora });
    localStorage.setItem(clave, JSON.stringify(misEventos));
    alert('Evento guardado correctamente');
}
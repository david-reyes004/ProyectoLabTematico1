document.addEventListener("DOMContentLoaded", event => {
    cargarEventos();
});

async function cargarEventos() {
    const contenedor = document.getElementById('ContenedorDeEventos');
    try {
        const respuesta = await fetch('http://localhost:8080/eventos');
        const eventos = await respuesta.json();

        console.log('primer evento:', eventos[0]);

        contenedor.innerHTML = '';
        eventos.forEach(evento => {
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
                        <button class="btn-comprar-real" onclick="event.stopPropagation(); comprarBoleto(${evento.id}, '${evento.nombre}', ${evento.precio || 1500})">🎫 COMPRAR</button>
                    </div>
                </div>
            `;
        });
    } catch (error) {
        contenedor.innerHTML = '<p>ERROR AL CONECTAR CON EL SERVIDOR</p>';
    }
}

async function comprarBoleto(eventoId, nombreEvento, precio) {
    const usuarioEmail = localStorage.getItem('usuarioActual');
    const usuarioId = parseInt(localStorage.getItem('usuarioId'));

    if (!usuarioEmail) {
        alert('¡Hey! Inicia sesión para comprar tu boleto.');
        return;
    }

    if (!usuarioId) {
        alert('Error: no se encontró tu sesión. Vuelve a iniciar sesión.');
        return;
    }

    const cantidad = parseInt(prompt(`¿Cuántos boletos deseas comprar para ${nombreEvento}?`));
    if (!cantidad || cantidad <= 0) return;

    const precioTotal = cantidad * precio;
    const confirmar = confirm(`Total a pagar: $${precioTotal}. ¿Confirmas la compra?`);
    if (!confirmar) return;

    const datosBoleto = {
        nombre: `General - ${nombreEvento}`,
        compra: cantidad,
        precio: precioTotal,
        eventoId: eventoId,
        usuarioId: usuarioId
    };

    console.log('enviando:', JSON.stringify(datosBoleto));

    try {
        const respuesta = await fetch('http://localhost:8080/boletos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datosBoleto)
        });

        if (respuesta.ok) {
            const resultado = await respuesta.json();
            alert(`¡Compra exitosa! 🎉 Tu boleto tiene el ID: ${resultado.id}`);
        } else {
            const errorText = await respuesta.text();
            console.error('Error del servidor:', errorText);
            alert('Error en el servidor al procesar la compra.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('No se pudo conectar con el servidor.');
    }
}

async function abrirModal(eventoId, nombreEvento) {
    document.getElementById('modalPresentaciones').style.display = 'flex';
    document.getElementById('tituloModal').innerText = `Presentaciones de: ${nombreEvento}`;
    const contenedor = document.getElementById('listaPresentaciones');
    contenedor.innerHTML = '<p>Buscando presentaciones...</p>';

    try {
        const respuesta = await fetch('http://localhost:8080/api/presentaciones');
        const todasLasPresentaciones = await respuesta.json();

        console.log('eventoId recibido:', eventoId, typeof eventoId);
        console.log('primera presentacion evento.id:', todasLasPresentaciones[0]?.evento?.id, typeof todasLasPresentaciones[0]?.evento?.id);

        const presentacionesDelEvento = todasLasPresentaciones.filter(p =>
            parseInt(p.evento.id) === parseInt(eventoId)
        );

        console.log('presentaciones filtradas:', presentacionesDelEvento.length);

        if (presentacionesDelEvento.length === 0) {
            contenedor.innerHTML = '<p>Aún no hay artistas confirmados para este evento.</p>';
            return;
        }

        contenedor.innerHTML = '';
        presentacionesDelEvento.forEach(p => {
            contenedor.innerHTML += `
                <div class="presentacion-card">
                    <div>
                        <h3 style="margin:0; color:#00d2ff;"> ${p.artista.nombre_artistico}</h3>
                        <p style="margin:5px 0;">Género: ${p.artista.genero}</p>
                        <p style="margin:5px 0;"> Escenario: ${p.escenario.nombre} - ${p.escenario.ubicacion}</p>
                        <p style="margin:0; color:#ccc;"> Horario: ${p.hora_presentacion}</p>
                    </div>
                    <button class="btn-presentacion" onclick="guardarPresentacion(${p.id}, '${p.artista.nombre_artistico}', '${nombreEvento}', '${p.hora_presentacion}')">
                        AGREGAR A MI LISTA
                    </button>
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

function guardarPresentacion(id, nombreArtista, eventoNombre, hora) {
    let usuario = localStorage.getItem('usuarioActual');
    if (!usuario) { alert('Debes iniciar sesión'); return; }

    let clave = `misPresentaciones_${usuario}`;
    let misPresentaciones = JSON.parse(localStorage.getItem(clave)) || [];

    if (misPresentaciones.some(p => p && p.id === id)) {
        alert('Ya guardaste esta presentación');
        return;
    }

    misPresentaciones.push({ id, nombreArtista, eventoNombre, hora });
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
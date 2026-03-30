document.addEventListener('DOMContentLoaded', function() {
    let usuario = localStorage.getItem('usuarioActual');
    if (!usuario) {
        window.location.href = 'iniciarSesion.html';
        return;
    }

    const titulo = document.getElementById('tituloBienvenida');
    if (titulo) titulo.innerText = `Mis Eventos - ${usuario}`;

    const tablaMisEventos = document.getElementById('tablaMisEventos');
    if (tablaMisEventos) {
        cargarEventosLocales(usuario);
        cargarPresentacionesLocales(usuario);
        cargarBoletosServidor();
    }
});

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
            <td>${p.nombreArtista}</td>
            <td>${p.hora}</td>
            <td><button onclick="eliminarPresentacion(${p.id})" style="cursor:pointer; background:none; border:none;">❌</button></td>
        </tr>`;
    });
}

async function cargarBoletosServidor() {
    const contenedor = document.getElementById("contenedor-mis-boletos");
    if (!contenedor) return;

    const usuarioId = parseInt(localStorage.getItem('usuarioId'));

    try {
        const respuesta = await fetch('http://localhost:8080/boletos');

        if (!respuesta.ok) {
            contenedor.innerHTML = '<p>Error al cargar boletos</p>';
            return;
        }

        const data = await respuesta.json();
        const boletos = Array.isArray(data) ? data : [data];

        console.log('boletos:', boletos);

        const misBoletos = boletos.filter(b => b.usuarioId === usuarioId);

        contenedor.innerHTML = '';

        if (misBoletos.length === 0) {
            contenedor.innerHTML = '<p>No tienes boletos comprados</p>';
            return;
        }

        const nombreUsuario = localStorage.getItem('usuarioActual');

        misBoletos.forEach(b => {
            const tarjeta = document.createElement('div');
            tarjeta.classList.add('boleto-card');
            tarjeta.innerHTML = `
                <div class="boleto-ticket">
                    <div class="boleto-izquierda">
                        <span class="boleto-tag"> BOLETO OFICIAL</span>
                        <h2 class="boleto-evento">${b.nombre}</h2>
                        <p class="boleto-usuario"> ${nombreUsuario}</p>
                        <p class="boleto-cantidad"> Cantidad: ${b.compra}</p>
                    </div>
                    <div class="boleto-separador"></div>
                    <div class="boleto-derecha">
                        <p>ID: <strong>#${b.id}</strong></p>
                        <p>Total: <strong>$${b.precio}</strong></p>
                        <span class="boleto-estado">✅ Confirmado</span>
                    </div>
                </div>
            `;
            contenedor.appendChild(tarjeta);
        });

    } catch (error) {
        console.error("Error:", error);
        contenedor.innerHTML = '<p>No se pudo conectar</p>';
    }
}

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
        localStorage.removeItem('usuarioId');
        window.location.href = 'iniciarSesion.html';
    }
}
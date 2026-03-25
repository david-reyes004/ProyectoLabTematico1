document.addEventListener('DOMContentLoaded', function() {
    let usuario = localStorage.getItem('usuarioActual');
    let clave = `misEventos_${usuario}`;
    let eventos = JSON.parse(localStorage.getItem(clave)) || [];
    let tabla = document.getElementById("tablaMisEventos");

    eventos.forEach(e => {
        let fechaLimpia = e.fecha.split('T')[0];
        let fechaFormateada = fechaLimpia.split('-').reverse().join('/');
        let fila = `<tr>
            <td>${e.id}</td>
            <td>${e.nombre}</td>
            <td>${fechaFormateada}</td>
            <td>${e.hora}</td>
        </tr>`;
        tabla.innerHTML += fila;
    });
});
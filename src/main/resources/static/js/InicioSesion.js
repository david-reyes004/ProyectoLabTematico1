document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formInicio').addEventListener('submit', async function(e) {
        e.preventDefault();

        const datosUsuario = {
            correo:   document.getElementById('correo').value,
            password: document.getElementById('password').value
        }

        try {
            const response = await fetch('http://localhost:8080/Login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(datosUsuario)
            });

            console.log('Status:', response.status);
            const texto = await response.text();
            console.log('Respuesta raw:', texto);
            const usuario = JSON.parse(texto);
            console.log('usuario parseado:', usuario);

            if (response.ok) {
                localStorage.setItem('usuarioActual', usuario.correo);
                localStorage.setItem('usuarioId', String(usuario.id));
                document.getElementById('mensaje').textContent = '✓ Inicio de Sesion exitoso';
                document.getElementById('formInicio').reset();
                setTimeout(() => {
                    window.location.href = '/paginaPrincipal.html';
                }, 100);
            } else {
                document.getElementById('mensaje').textContent = 'Error al Iniciar sesion';
            }
        } catch(err) {
            document.getElementById('mensaje').textContent = 'No se pudo iniciar Sesion';
            console.error('Error completo:', err);
        }
    })
})

function cerrarSesion() {
    localStorage.removeItem('usuarioActual');
    localStorage.removeItem('usuarioId');
    window.location.href = '/iniciarSesion.html';
}
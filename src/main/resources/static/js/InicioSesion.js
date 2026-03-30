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
            console.log('response.ok:', response.ok);
            console.log('response.status:', response.status);

            const usuario = JSON.parse(texto);

            console.log('Antes de guardar - usuarioId:', usuario.id);
            localStorage.setItem('usuarioId', String(usuario.id));
            console.log('Después de guardar:', localStorage.getItem('usuarioId'));


            if (response.ok) {
                localStorage.setItem('usuarioActual', usuario.correo);
                localStorage.setItem('usuarioId', String(usuario.id));

                console.log('usuarioId guardado:', localStorage.getItem('usuarioId'));

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
            console.error('Error completo:', err); // 👈 ver el error completo
        }
    })
})

function cerrarSesion() {
    localStorage.removeItem('usuarioActual');
    window.location.href = '/iniciarSesion.html';
}
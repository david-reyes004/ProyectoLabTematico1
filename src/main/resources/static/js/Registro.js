document.addEventListener('DOMContentLoaded', function() {

    document.getElementById('formRegistro').addEventListener('submit', async function(e) {
        e.preventDefault();

        const datosUsuario = {
            nombre:   document.getElementById('nombre').value,
            correo:    document.getElementById('correo').value,
            password: document.getElementById('password').value
        };

        try {
            // Cambiamos a 127.0.0.1 por si el DNS local te está dando guerra
            const response = await fetch('http://127.0.0.1:8080/usuarios', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(datosUsuario)
            });

            if (response.ok) {
                // ESTA ES LA LÍNEA CLAVE: Guardamos el correo en la sesión local
                localStorage.setItem('usuarioActual', datosUsuario.correo);

                document.getElementById('mensaje').textContent = '✓ Usuario registrado correctamente';
                document.getElementById('formRegistro').reset();

                // Ahora sí, al redirigir, la app ya sabrá quién eres
                window.location.href = 'paginaPrincipal.html';
            } else {
                document.getElementById('mensaje').textContent = 'Error al registrar: Servidor respondió mal';
            }

        } catch (err) {
            document.getElementById('mensaje').textContent = 'No se pudo conectar con el servidor';
            console.error(err);
        }
    });
});
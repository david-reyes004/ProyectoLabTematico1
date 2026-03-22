document.addEventListener('DOMContentLoaded', function() {

    document.getElementById('formRegistro').addEventListener('submit', async function(e) {
        e.preventDefault();

        const datosUsuario = {
            nombre:   document.getElementById('nombre').value,
            correo:    document.getElementById('correo').value,
            password: document.getElementById('password').value
        };

        try {
            const response = await fetch('http://localhost:8080/usuarios', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(datosUsuario)
            });

            if (response.ok) {
                document.getElementById('mensaje').textContent = '✓ Usuario registrado correctamente';
                document.getElementById('formRegistro').reset();
                window.location.href='/paginaPrincipal.html';
            } else {
                document.getElementById('mensaje').textContent = 'Error al registrar';
            }

        } catch (err) {
            document.getElementById('mensaje').textContent = 'No se pudo conectar con el servidor';
            console.error(err);
        }

    });

});
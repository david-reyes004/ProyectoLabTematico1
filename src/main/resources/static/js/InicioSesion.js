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
            })
            console.log('Status:', response.status);
            console.log('Respuesta:', await response.text());
            if (response.ok) {
                localStorage.setItem('usuarioActual', document.getElementById('correo').value);
                document.getElementById('mensaje').textContent = '✓ Inicio de Sesion exitoso';
                document.getElementById('formInicio').reset();
                window.location.href = '/paginaPrincipal.html';
            } else {
                document.getElementById('mensaje').textContent = 'Error al Iniciar sesion';
            }
        }catch(err){
            document.getElementById('mensaje').textContent = 'No se pudo iniciar Sesion';
            console.error(err);
        }
    })
})

function cerrarSesion() {
    localStorage.removeItem('usuarioActual');
    window.location.href = '/iniciarSesion.html';
}
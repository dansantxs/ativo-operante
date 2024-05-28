document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('login-form');

    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const email = document.getElementById('email_login').value;
        const senha = document.getElementById('senha_login').value;

        const usuario = {
            email: email,
            senha: senha
        };

        try {
            const response = await fetch('http://localhost:8080/apis/security/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(usuario)
            });

            if (!response.ok) {
                const errorMessage = await response.text();
                alert('Erro: ' + errorMessage);
                return;
            }

            const token = await response.text();
            localStorage.setItem('jwtToken', token);

            alert('Login bem-sucedido!');

            window.location.href = '/home.html';
        } catch (error) {
            console.error('Erro ao fazer login:', error);
            alert('Erro ao fazer login: ' + error.message);
        }
    });
});

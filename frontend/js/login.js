document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('login-form');

    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const email = document.getElementById('email_cad').value;
        const senha = document.getElementById('senha_cad').value;

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

            const token = await response.text();

            if (!response.ok) {
                alert('Erro: ' + token);
                return;
            }

            const payload = JSON.parse(atob(token.split('.')[1]));
            const nivel = parseInt(payload.nivel);

            // Armazenar o token no localStorage
            localStorage.setItem('token', token);

            if (nivel === 1) {
                window.location.href = 'home-administrador.html';
            } else if (nivel === 2) {
                window.location.href = 'home-cidadao.html';
            } else {
                alert('Nível de usuário desconhecido.');
            }

        } catch (error) {
            console.error('Erro ao fazer login:', error);
            alert('Erro ao fazer login: ' + error.message);
        }
    });
});

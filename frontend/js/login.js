document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('login-form');

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Evita o comportamento padrão de submit do formulário

        const email = document.getElementById('email_cad').value;
        const senha = document.getElementById('senha_cad').value;

        const data = {
            email: email,
            senha: senha
        };

        // Envia uma requisição POST para o endpoint /apis/security/login
        fetch('http://localhost:8080/apis/security/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao realizar login');
            }
            return response.text();
        })
        .then(token => {
            // Decodificar o token JWT para obter o nível do usuário
            const nivel = parseInt(JSON.parse(atob(token.split('.')[1])).nivel);

            // Redirecionamento baseado no nível do usuário
            if (nivel === 1) {
                window.location.href = 'home-administrador.html';
            } else if (nivel === 2) {
                window.location.href = 'home-cidadao.html';
            } else {
                alert('Nível de usuário desconhecido.');
            }
        })
        .catch(error => {
            console.error('Erro ao realizar login:', error);
            // Exemplo de exibição de mensagem de erro ao usuário
            alert('Falha no login. Verifique suas credenciais.');
        });
    });
});

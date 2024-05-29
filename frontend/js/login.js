document.getElementById('login-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const email = document.getElementById('email_login').value;
    const password = document.getElementById('senha_login').value;
    const emailError = document.getElementById('email-error');
    const passwordError = document.getElementById('senha-error');
    const message = document.getElementById('message');

    emailError.textContent = '';
    passwordError.textContent = '';
    message.textContent = '';

    const user = {
        email: email,
        senha: password
    };

    try {
        const response = await fetch('http://localhost:8080/apis/security/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            const token = await response.text();
            message.textContent = 'Login bem-sucedido!';
            message.style.color = 'green';

            const payload = JSON.parse(atob(token.split('.')[1]));
            const userLevel = payload.nivel;
            localStorage.setItem('token', token);
            localStorage.setItem('nivel', userLevel);

            if (email === 'admin@pm.br') {
                setTimeout(() => {
                    window.location.href = 'teste.html';
                }, 1000);
            } else {
                setTimeout(() => {
                    window.location.href = 'options.html';
                }, 1000);
            }
        } else {
            const error = await response.text();
            message.textContent = `Erro: ${error}`;
            message.style.color = 'red';
        }
    } catch (error) {
        message.textContent = `Erro: ${error.message}`;
        message.style.color = 'red';
    }
});
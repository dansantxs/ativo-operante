document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('cadastro-form');

    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const cpf = document.getElementById('cpf_cad').value.replace(/\D/g, '');
        const email = document.getElementById('email_cad').value;
        const senha = document.getElementById('senha_cad').value;

        const usuario = {
            cpf: cpf,
            email: email,
            senha: senha,
            nivel: 2
        };

        try {
            const response = await fetch('http://localhost:8080/apis/security/add-usuario', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(usuario)
            });

            const message = await response.text();

            if (!response.ok) {
                alert('Erro: ' + message);
                return;
            }

            alert('Cadastro bem-sucedido!');

            window.location.href = 'login.html';
        } catch (error) {
            console.error('Erro ao fazer cadastro:', error);
            alert('Erro ao fazer cadastro: ' + error.message);
        }
    });
});

function formatarCPF(cpfField) {
    const cpf = cpfField.value.replace(/\D/g, '');
    cpfField.value = cpf.replace(/(\d{3})(\d)/, '$1.$2')
                         .replace(/(\d{3})(\d)/, '$1.$2')
                         .replace(/(\d{3})(\d{1,2})$/, '$1-$2');
}

function apenasNumeros(input) {
    input.value = input.value.replace(/\D/g, '');
}

function apenasNumerosTecla(event) {
    const tecla = event.key;
    if (tecla >= '0' && tecla <= '9' || tecla === 'Backspace' || tecla === 'Delete' || tecla === 'ArrowLeft' || tecla === 'ArrowRight' || tecla === 'Tab') {
        return true;
    } else {
        event.preventDefault();
        return false;
    }
}

document.addEventListener("DOMContentLoaded", function() {
    const buscarDenunciasBtn = document.getElementById("buscarDenunciasBtn");

    buscarDenunciasBtn.addEventListener("click", function() {
        const usuarioId = document.getElementById("usuarioId").value;
        if (!usuarioId) {
            alert("Por favor, insira o ID do usuário.");
            return;
        }

        // Chama a função para buscar e exibir as denúncias do usuário com o ID fornecido
        exibirDenunciasDoUsuario(usuarioId);
    });

    // Função para buscar e exibir as denúncias do usuário
    async function exibirDenunciasDoUsuario(usuarioId) {
        const URL = `http://localhost:8080/apis/cidadao/denuncias-do-usuario/${usuarioId}`;

        try {
            const response = await fetch(URL);
            
            if (!response.ok) {
                throw new Error('Erro na requisição: ' + response.statusText);
            }

            const denuncias = await response.json();
            // Exibir as denúncias na página
            exibirDenuncias(denuncias);
        } catch (error) {
            console.error('Erro:', error);
            alert("Erro ao buscar denúncias");
        }
    }

    // Função para exibir as denúncias na página
    function exibirDenuncias(denuncias) {
        const denunciasContainer = document.getElementById("denuncias-container");
        denunciasContainer.innerHTML = ""; // Limpar o conteúdo anterior

        if (denuncias.length === 0) {
            denunciasContainer.innerHTML = "<p>Nenhuma denúncia encontrada.</p>";
            return;
        }

        const ul = document.createElement("ul");
        denuncias.forEach(denuncia => {
            const li = document.createElement("li");
            li.textContent = `Título: ${denuncia.titulo}, Texto: ${denuncia.texto}, Urgência: ${denuncia.urgencia}, Data: ${denuncia.data}`;
            ul.appendChild(li);
        });

        denunciasContainer.appendChild(ul);
    }
});
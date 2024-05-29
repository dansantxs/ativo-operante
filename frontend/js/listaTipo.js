document.addEventListener("DOMContentLoaded", function() {
    // Função para buscar e exibir os tipos
    async function buscarTipos() {
        const URL = "http://localhost:8080/apis/cidadao/get-all-tipos";

        try {
            const response = await fetch(URL);
            
            if (!response.ok) {
                throw new Error('Erro na requisição: ' + response.statusText);
            }

            const tipos = await response.json();
            // Exibir os tipos na página
            exibirTipos(tipos);
        } catch (error) {
            console.error('Erro:', error);
            alert("Erro ao buscar tipos");
        }
    }

    // Função para exibir os tipos na página
    function exibirTipos(tipos) {
        const tiposContainer = document.getElementById("tipos-container");
        tiposContainer.innerHTML = ""; // Limpar o conteúdo anterior

        if (tipos.length === 0) {
            tiposContainer.innerHTML = "<p>Nenhum tipo encontrado.</p>";
            return;
        }

        const ul = document.createElement("ul");
        tipos.forEach(tipo => {
            const li = document.createElement("li");
            li.textContent = `ID: ${tipo.id}, Nome: ${tipo.nome}`;
            ul.appendChild(li);
        });

        tiposContainer.appendChild(ul);
    }

    // Chama a função para buscar e exibir os tipos
    buscarTipos();
});
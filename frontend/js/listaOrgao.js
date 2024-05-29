document.addEventListener("DOMContentLoaded", function() {
    // Função para buscar e exibir os órgãos
    async function buscarOrgaos() {
        const URL = "http://localhost:8080/apis/cidadao/get-all-orgaos";

        try {
            const response = await fetch(URL);
            
            if (!response.ok) {
                throw new Error('Erro na requisição: ' + response.statusText);
            }

            const orgaos = await response.json();
            // Exibir os órgãos na página
            exibirOrgaos(orgaos);
        } catch (error) {
            console.error('Erro:', error);
            alert("Erro ao buscar órgãos");
        }
    }

    // Função para exibir os órgãos na página
    function exibirOrgaos(orgaos) {
        const orgaosContainer = document.getElementById("orgaos-container");
        orgaosContainer.innerHTML = ""; // Limpar o conteúdo anterior

        if (orgaos.length === 0) {
            orgaosContainer.innerHTML = "<p>Nenhum órgão encontrado.</p>";
            return;
        }

        const ul = document.createElement("ul");
        orgaos.forEach(orgao => {
            const li = document.createElement("li");
            li.textContent = `ID: ${orgao.id}, Nome: ${orgao.nome}`;
            ul.appendChild(li);
        });

        orgaosContainer.appendChild(ul);
    }

    // Chama a função para buscar e exibir os órgãos
    buscarOrgaos();
});
document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Você precisa estar logado para acessar esta página.');
        window.location.href = 'login.html';
        return;
    }

    const apiUrl = 'http://localhost:8080/apis/adm';

    function setHeaders() {
        return {
            'Content-Type': 'application/json',
            'Authorization': `${token}`
        };
    }

    async function fetchOrgaos() {
        try {
            const headers = setHeaders();
            const response = await fetch(`${apiUrl}/get-all-orgaos`, {
                method: 'GET',
                headers: headers
            });

            if (!response.ok) {
                throw new Error('Erro ao carregar órgãos competentes');
            }

            const orgaos = await response.json();
            populateTable(orgaos);
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    function populateTable(orgaos) {
        const tbody = document.querySelector('#orgaos-table tbody');
        tbody.innerHTML = '';

        orgaos.forEach(orgao => {
            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${orgao.id}</td>
                <td>${orgao.nome}</td>
                <td class="actions">
                    <button class="edit" onclick="editOrgao(${orgao.id}, '${orgao.nome}')">Editar</button>
                    <button class="delete" onclick="deleteOrgao(${orgao.id})">Excluir</button>
                </td>
            `;

            tbody.appendChild(row);
        });
    }

    document.getElementById('orgao-form').addEventListener('submit', async function (event) {
        event.preventDefault();

        const id = document.getElementById('orgao-id').value;
        const nome = document.getElementById('nome').value;

        const orgao = { id: id ? parseInt(id) : null, nome: nome };

        try {
            const method = id ? 'PUT' : 'POST';
            const url = id ? `${apiUrl}/update-orgao` : `${apiUrl}/add-orgao`;

            const response = await fetch(url, {
                method: method,
                headers: setHeaders(),
                body: JSON.stringify(orgao)
            });

            if (!response.ok) {
                throw new Error('Erro ao salvar órgão competente');
            }

            alert('Órgão competente salvo com sucesso!');
            document.getElementById('orgao-form').reset();
            document.getElementById('form-title').textContent = 'Adicionar Órgão Competente';
            fetchOrgaos();
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    });

    window.editOrgao = function (id, nome) {
        document.getElementById('orgao-id').value = id;
        document.getElementById('nome').value = nome;
        document.getElementById('form-title').textContent = 'Editar Órgão Competente';
    };

    window.deleteOrgao = async function (id) {
        if (!confirm('Tem certeza que deseja excluir este órgão competente?')) {
            return;
        }

        try {
            const response = await fetch(`${apiUrl}/delete-orgao?id=${id}`, {
                method: 'DELETE',
                headers: setHeaders()
            });

            if (!response.ok) {
                throw new Error('Erro ao excluir órgão competente');
            }

            alert('Órgão competente excluído com sucesso!');
            fetchOrgaos();
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    };

    window.filterGrid = function () {
        const searchTerm = document.getElementById('search-input').value.toLowerCase();
        const rows = document.querySelectorAll('#orgaos-table tbody tr');

        rows.forEach(row => {
            const id = row.cells[0].textContent;
            const nome = row.cells[1].textContent.toLowerCase();

            if (id.includes(searchTerm) || nome.includes(searchTerm)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    };

    fetchOrgaos();
});

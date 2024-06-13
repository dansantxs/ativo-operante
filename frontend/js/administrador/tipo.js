document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Você precisa estar logado para acessar esta página.');
        window.location.href = '/html/login.html';
        return;
    }

    const apiUrl = 'http://localhost:8080/apis/adm';

    function setHeaders() {
        return {
            'Content-Type': 'application/json',
            'Authorization': `${token}`
        };
    }

    async function fetchTipos() {
        try {
            const headers = setHeaders();
            const response = await fetch(`${apiUrl}/get-all-tipos`, {
                method: 'GET',
                headers: headers
            });

            if (!response.ok) {
                throw new Error('Erro ao carregar tipos de problema');
            }

            const tipos = await response.json();
            populateTable(tipos);
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    function populateTable(tipos) {
        const tbody = document.querySelector('#tipos-table tbody');
        tbody.innerHTML = '';

        tipos.forEach(tipo => {
            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${tipo.id}</td>
                <td>${tipo.nome}</td>
                <td class="actions">
                    <button class="edit" onclick="editTipo(${tipo.id}, '${tipo.nome}')">Editar</button>
                    <button class="delete" onclick="deleteTipo(${tipo.id})">Excluir</button>
                </td>
            `;

            tbody.appendChild(row);
        });
    }

    document.getElementById('tipo-form').addEventListener('submit', async function (event) {
        event.preventDefault();

        const id = document.getElementById('tipo-id').value;
        const nome = document.getElementById('nome').value;

        const tipo = { id: id ? parseInt(id) : null, nome: nome };

        try {
            const method = id ? 'PUT' : 'POST';
            const url = id ? `${apiUrl}/update-tipo` : `${apiUrl}/add-tipo`;

            const response = await fetch(url, {
                method: method,
                headers: setHeaders(),
                body: JSON.stringify(tipo)
            });

            if (!response.ok) {
                throw new Error('Erro ao salvar tipo de problema');
            }

            alert('Tipo de problema salvo com sucesso!');
            document.getElementById('tipo-form').reset();
            document.getElementById('form-title').textContent = 'Adicionar Tipo de Problema';
            fetchTipos();
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    });

    window.editTipo = function (id, nome) {
        document.getElementById('tipo-id').value = id;
        document.getElementById('nome').value = nome;
        document.getElementById('form-title').textContent = 'Editar Tipo de Problema';
    };

    window.deleteTipo = async function (id) {
        if (!confirm('Tem certeza que deseja excluir este tipo de problema?')) {
            return;
        }

        try {
            const response = await fetch(`${apiUrl}/delete-tipo?id=${id}`, {
                method: 'DELETE',
                headers: setHeaders()
            });

            if (!response.ok) {
                throw new Error('Erro ao excluir tipo de problema');
            }

            alert('Tipo de problema excluído com sucesso!');
            fetchTipos();
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    };

    window.filterGrid = function () {
        const searchTerm = document.getElementById('search-input').value.toLowerCase();
        const rows = document.querySelectorAll('#tipos-table tbody tr');

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

    fetchTipos();
});

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
            'Authorization': `Bearer ${token}`
        };
    }

    async function fetchFeedbacks() {
        try {
            const headers = setHeaders();
            const response = await fetch(`${apiUrl}/get-all-feedbacks`, {
                method: 'GET',
                headers: headers
            });

            if (!response.ok) {
                throw new Error('Erro ao carregar feedbacks');
            }

            const feedbacks = await response.json();
            populateTable(feedbacks);
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    async function fetchDenuncias() {
        try {
            const headers = setHeaders();
            const response = await fetch(`${apiUrl}/get-all-denuncias`, {
                method: 'GET',
                headers: headers
            });

            if (!response.ok) {
                throw new Error('Erro ao carregar denúncias');
            }

            const denuncias = await response.json();
            populateDenunciaDropdown(denuncias);
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    function populateTable(feedbacks) {
        const tbody = document.querySelector('#feedbacks-table tbody');
        tbody.innerHTML = '';

        feedbacks.forEach(feedback => {
            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${feedback.id}</td>
                <td>${feedback.texto}</td>
                <td>${feedback.denuncia ? feedback.denuncia.titulo : 'Sem denúncia'}</td>
                <td class="actions">
                    <button class="edit" onclick="editFeedback(${feedback.id}, '${feedback.texto}', ${feedback.denuncia ? feedback.denuncia.id : ''})">Editar</button>
                    <button class="delete" onclick="deleteFeedback(${feedback.id})">Excluir</button>
                </td>
            `;

            tbody.appendChild(row);
        });
    }

    function populateDenunciaDropdown(denuncias) {
        const select = document.getElementById('denuncia');
        select.innerHTML = '<option value="">Selecione uma denúncia</option>';

        denuncias.forEach(denuncia => {
            const option = document.createElement('option');
            option.value = denuncia.id;
            option.textContent = `${denuncia.id} - ${denuncia.titulo}`;
            select.appendChild(option);
        });
    }

    document.getElementById('feedback-form').addEventListener('submit', async function (event) {
        event.preventDefault();

        const id = document.getElementById('feedback-id').value;
        const texto = document.getElementById('texto').value;
        const denunciaId = document.getElementById('denuncia').value;

        const feedback = { 
            id: id ? parseInt(id) : null, 
            texto: texto, 
            denuncia: denunciaId ? { id: parseInt(denunciaId) } : null 
        };

        try {
            const method = id ? 'PUT' : 'POST';
            const url = id ? `${apiUrl}/update-feedback` : `${apiUrl}/add-feedback`;

            const response = await fetch(url, {
                method: method,
                headers: setHeaders(),
                body: JSON.stringify(feedback)
            });

            if (!response.ok) {
                throw new Error('Erro ao salvar feedback');
            }

            alert('Feedback salvo com sucesso!');
            document.getElementById('feedback-form').reset();
            fetchFeedbacks();
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    });

    window.editFeedback = function (id, texto, denunciaId) {
        document.getElementById('feedback-id').value = id;
        document.getElementById('texto').value = texto;
        document.getElementById('denuncia').value = denunciaId;
        document.getElementById('form-title').textContent = 'Editar Feedback';
    };

    window.deleteFeedback = async function (id) {
        if (!confirm('Tem certeza de que deseja excluir este feedback?')) {
            return;
        }

        try {
            const response = await fetch(`${apiUrl}/delete-feedback?id=${id}`, {
                method: 'DELETE',
                headers: setHeaders()
            });

            if (!response.ok) {
                throw new Error('Erro ao excluir feedback');
            }

            alert('Feedback excluído com sucesso!');
            fetchFeedbacks();
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    };

    window.filterGrid = function () {
        const input = document.getElementById('search-input').value.toLowerCase();
        const rows = document.querySelectorAll('#feedbacks-table tbody tr');

        rows.forEach(row => {
            const id = row.cells[0].textContent.toLowerCase();
            const texto = row.cells[1].textContent.toLowerCase();
            row.style.display = id.includes(input) || texto.includes(input) ? '' : 'none';
        });
    };

    fetchFeedbacks();
    fetchDenuncias(); // Carrega as denúncias ao carregar a página
});

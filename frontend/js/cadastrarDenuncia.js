function cadastrarDenuncia(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    const URL = "http://localhost:8080/apis/cidadao/add-denuncia";
    
    var titulo = document.getElementById("den_titulo").value;
    var texto = document.getElementById("den_texto").value;
    var urgencia = document.getElementById("den_urgencia").value;
    var data = document.getElementById("den_data").value;
    var orgao = document.getElementById("org_id").value;
    var tipo = document.getElementById("tip_id").value;
    var usu_id = document.getElementById("usu_id").value;

    var params = new URLSearchParams({
        den_titulo: titulo,
        den_texto: texto,
        den_urgencia: urgencia,
        den_data: data,
        org_id: orgao,
        tip_id: tipo,
        usu_id: usu_id
    });

    fetch(URL + "?" + params.toString(), {
        method: 'POST'
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Erro na requisição: ' + resp.statusText);
        }
        return resp.text();
    })
    .then(text => {
        alert("Denúncia cadastrada com sucesso!");
        document.getElementById("formDenuncia").reset(); // Limpa o formulário
    })
    .catch(error => {
        console.error('Erro:', error);
        alert("Erro ao cadastrar denúncia");
    });
}
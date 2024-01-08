/**
 * Requisição de Compra (RC) 
 */

function insertIdManutencaoOpenModalRequisicaoCompra(btn) {
	document.querySelector('#modalRcLabel').textContent = "Manutenção: [" + btn.parentNode.parentNode.cells[0].textContent + "] - Requisição de Compra";
	popularTabelaRC();
	new bootstrap.Modal('#modalRc').show();
}

function salvarRC() {
	const url = base_url + '/rc/salvar';
	let lblId = document.querySelector('#modalRcLabel').textContent;
	lblId = lblId.split("-")[0].replace("[", "").replace("]", "").replace("Manutenção: ", "").trim();
	
	const data = {
		requisicaoCompra: Number(document.querySelector("input[name='requisicaoCompra']").value),
		idManutencao: Number(lblId),
		sitMemorizacao: document.querySelector("input[name='sitMemorizacao']").checked == true ? 'S' : 'N',
		valorTotal: document.querySelector("input[name='valorTotal']").value
	};
	
	$.ajax({
		url: url,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function() {
			popularTabelaRC();
		},
		error: function(error) {
			console.error('Erro na requisição:', error);
		}
	});
}

function popularTabelaRC() {
	let lblId = document.querySelector('#modalRcLabel').textContent;
	lblId = lblId.split("-")[0].replace("[", "").replace("]", "").replace("Manutenção: ", "").trim();
	const url = `${base_url}/rc/consultar?idManutencao=${lblId}`;

	$.ajax({
		url: url,
		type: 'GET',
		contentType: 'application/json',
		success: function(data) {
			inputDataTableRC(data);
		},
		error: function(error) {
			console.error('Erro na requisição:', error);
		}
	});
}

function inputDataTableRC(data) {
	var tbody = document.getElementById('tRCTbody');
	tbody.innerHTML = "";
	
	data.forEach(function(obj) {
		var novaLinha = tbody.insertRow();
		
		var cRequisicaoCompra = novaLinha.insertCell(0);
		var cMemorizacao = novaLinha.insertCell(1);
		var cValorTotal = novaLinha.insertCell(2);
		var cAcoes = novaLinha.insertCell(3);

		cRequisicaoCompra.textContent = obj.reqCompra;
		if (obj.memorizacao === 'S') {
			cMemorizacao.innerHTML = "<i class='bi bi-bell-fill'></i>";
		} else if (obj.memorizacao === 'N') {
			cMemorizacao.innerHTML = "<i class='bi bi-bell-slash-fill'></i>";
		}
		
		cValorTotal.textContent = obj.valorTotal;
		cAcoes.innerHTML = "<button type='button' id='" + obj.reqCompra + "' onclick='excluirRC(this)' class='btn btn-danger'><i class='bi bi-trash3'></i></button>";
	});
}

function excluirRC(btn) {
	const url = base_url + '/rc/deletar';
	let lblId = document.querySelector('#modalRcLabel').textContent;
	lblId = lblId.split("-")[0].replace("[", "").replace("]", "").replace("Manutenção: ", "").trim();
	
	const data = {
		requisicaoCompra: Number(btn.id),
		idManutencao: Number(lblId)
	};

	$.ajax({
		url: url,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function() {
			popularTabelaRC();
		},
		error: function(error) {
			console.error('Erro na requisição:', error);
		}
	});
}
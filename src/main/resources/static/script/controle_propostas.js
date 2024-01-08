/**
 * Proposta de Manutenção
 */

function insertIdManutencaoOpenModalProposta(btn) {
	document.querySelector("input[name='item']").value = "";
	document.querySelector("input[name='descricao']").value = "";
	document.querySelector("input[name='valor']").value = "";

	document.querySelector('#modalPropostaLabel').textContent = "Manutenção: [" + btn.parentNode.parentNode.cells[0].textContent + "] - Definições da Proposta";
	popularTabelaProposta();
	new bootstrap.Modal('#modalProposta').show();
}

function popularTabelaProposta() {
	let lblId = document.querySelector('#modalPropostaLabel').textContent;
	lblId = lblId.split("-")[0].replace("[", "").replace("]", "").replace("Manutenção: ", "").trim();

	const url = `${base_url}/proposta/consultar?idManutencao=${Number(lblId)}`;

	$.ajax({
		url: url,
		type: 'GET',
		contentType: 'application/json',
		success: function(data) {
			inputDataTableProposta(data);
		},
		error: function(error) { console.error('Erro na requisição:', error); }
	});
}

function salvarProposta() {
	const url = base_url + '/proposta/salvar';
	let lblId = document.querySelector('#modalPropostaLabel').textContent;
	lblId = lblId.split("-")[0].replace("[", "").replace("]", "").replace("Manutenção: ", "").trim();

	let numeroSerie = null;
	let idProposta = null;
	let idEmpresa = Number(document.querySelector("select[name='idEmpresa']").value)
	let item = Number(document.querySelector("input[name='item']").value)
	let descricao = document.querySelector("input[name='descricao']").value
	let valor = document.querySelector("input[name='valor']").value
	
	
	document.querySelectorAll("input[name='checkboxProposta']").forEach(function(input) {
		if (input.checked) {
			idProposta = input.parentNode.parentNode.cells[8].textContent;		
			numeroSerie = input.id;
		}
	});

	if (numeroSerie == null || item == 0 || descricao == '' || valor == '') {
		alert("Preencha todos os campos e selecione um Equipamento!");
		return;
	}

	const data = {
		id: Number(idProposta) === 0 ? null : Number(idProposta),
		idManutencao: Number(lblId),
		numeroSerieEquipamento: Number(numeroSerie),
		idEmpresa: idEmpresa,
		item: item,
		descricao: descricao,
		valor: valor
	};

	$.ajax({
		url: url,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function() {
			popularTabelaProposta();
			document.querySelector("input[name='item']").value = "";
			document.querySelector("input[name='descricao']").value = "";
			document.querySelector("input[name='valor']").value = "";
		},
		error: function(error) {
			console.error('Erro na requisição:', error);
		}
	});
}

function inputDataTableProposta(data) {
	var tbody = document.getElementById('tPropostasManutencaoTbody');
	tbody.innerHTML = "";
	
	let valorTotal = 0;
	
	data.forEach(function(obj) {
		var novaLinha = tbody.insertRow();
		
		var cCheck = novaLinha.insertCell(0);
		var cId = novaLinha.insertCell(1);
		var cEquipamentoNumSerie = novaLinha.insertCell(2);
		var cEquipamentoCodigo = novaLinha.insertCell(3);
		var cEmpresa = novaLinha.insertCell(4);
		var cItem = novaLinha.insertCell(5);
		var cDescricao = novaLinha.insertCell(6);
		var cValor = novaLinha.insertCell(7);
		var cIdProposta = novaLinha.insertCell(8);
		var cAcoes = novaLinha.insertCell(9);
		
		cCheck.innerHTML = `<input class="form-check-input" type="checkbox" value="" id="${obj.numeroSerie}" name="checkboxProposta">`;
		cId.textContent 					= obj.idEquipamento;
		cEquipamentoNumSerie.textContent 	= obj.numeroSerie;
		cEquipamentoCodigo.textContent 		= obj.codigo;
		cEmpresa.textContent 				= obj.nomeEmpresa;
		cItem.textContent 					= obj.item;
		cDescricao.textContent 				= obj.descricao;
		const valorBrl = Number(obj.valor);
		cValor.textContent 					= valorBrl.toLocaleString('pt-BR',{style:'currency',currency:'BRL'});
		cIdProposta.textContent				= obj.idProposta
		cAcoes.innerHTML = `<button type="button" id="${obj.idEquipamento}" onclick="excluirProposta(this)" class="btn btn-danger"><i class="bi bi-trash3"></i></button>`;
		
		novaLinha.addEventListener('click', function() {
            document.querySelectorAll("input[name='checkboxProposta']").forEach(function(input) {
				if (input.id == obj.numeroSerie) {
					input.checked = true;
				} else {
					input.checked = false;
				}
			});
        });
        valorTotal = valorTotal + Number(obj.valor);
        document.getElementById('labelValorTotal').innerText = `Total: ${valorTotal.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}`
	});
}

function excluirProposta(button) {
	const url = `${base_url}/proposta/deletar?id=${button.parentNode.parentNode.cells[8].textContent}`;
	let lblId = document.querySelector('#modalPropostaLabel').textContent;
	lblId = lblId.split("-")[0].replace("[", "").replace("]", "").replace("Manutenção: ", "").trim();

	$.ajax({
		url: url,
		type: 'GET',
		contentType: 'application/json',
		success: function() {
			popularTabelaProposta();
		},
		error: function(error) {
			console.error('Erro na requisição:', error);
		}
	});
}
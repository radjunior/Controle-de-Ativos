/**
 * Proposta de Manutenção
 */
function insertIdManutencaoOpenModalAddEquipamentos(btn) {
	document.querySelector('#modalAddEquipamentosLabel').textContent = "Manutenção: [" + btn.parentNode.parentNode.cells[0].textContent + "] - Definições da Proposta";
	buscarEquipamentosPorManutencao();
	new bootstrap.Modal('#modalAddEquipamentos').show();
}

function buscarEquipamento() {
	const url = `${base_url}/equipamentos/consultar-equipamento?numserie=${Number(document.querySelector("input[name='numeroSerieEquipamentoModal']").value)}`;
	$.ajax({
		url: url,
		type: 'GET',
		contentType: 'application/json',
		success: function(data) {
			inserirLinhasTabelaEquipamento(data);
		},
		error: function(error) { console.log('Erro na requisição:', error); }
	});
}

function buscarEquipamentosPorManutencao() {
	let lblId = document.querySelector('#modalAddEquipamentosLabel').textContent;
	lblId = lblId.split("-")[0].replace("[", "").replace("]", "").replace("Manutenção: ", "").trim();

	const url = `${base_url}/equipamentos/consultar?idManutencao=${Number(lblId)}`;

	$.ajax({
		url: url,
		type: 'GET',
		contentType: 'application/json',
		success: function(data) {
			inserirLinhasTabelaEquipamentoPorManutencao(data);
		},
		error: function(error) { console.error('Erro na requisição:', error); }
	});
}

function salvarRelacionamentoEquipamentoManutencao(btn) {
	let lblId = document.querySelector('#modalAddEquipamentosLabel').textContent;
	lblId = lblId.split("-")[0].replace("[", "").replace("]", "").replace("Manutenção: ", "").trim();

	const url = `${base_url}/equipamentos/salvar`;

	const data = {
		idManutencao: Number(lblId),
		numeroSerieEquipamento: Number(btn.parentNode.parentNode.cells[0].textContent),
	};

	$.ajax({
		url: url,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function() {
			buscarEquipamentosPorManutencao();
		},
		error: function(error) { console.log('Erro na requisição:', error); }
	});
}

function inserirLinhasTabelaEquipamento(data) {
	var tbody = document.getElementById('tEquipamentos');
	tbody.innerHTML = "";

	data.forEach(function(obj) {
		var novaLinha = tbody.insertRow();
		var cNumeroSerie = novaLinha.insertCell(0);
		var cCodigo = novaLinha.insertCell(1);
		var cFabricanteNome = novaLinha.insertCell(2);
		var cFabricanteModelo = novaLinha.insertCell(3);
		var cSituacao = novaLinha.insertCell(4);
		var cAcoes = novaLinha.insertCell(5);

		cNumeroSerie.textContent = obj.numeroSerie;
		cCodigo.textContent = obj.codigo;
		cFabricanteNome.textContent = obj.fabricante.nome;
		cFabricanteModelo.textContent = obj.fabricante.modelo;
		cSituacao.textContent = obj.situacao;
		cAcoes.innerHTML = `<button type="button" id="${obj.id}" onclick="salvarRelacionamentoEquipamentoManutencao(this)" class="btn btn-success"><i class="bi bi-arrow-down-square-fill"></i></button>`;
	});

}

function inserirLinhasTabelaEquipamentoPorManutencao(data) {
	var tbody = document.getElementById('tEquipamentosAssociados');
	tbody.innerHTML = "";

	data.forEach(function(obj) {
		var novaLinha = tbody.insertRow();
		var cId = novaLinha.insertCell(0)
		var cNumeroSerie = novaLinha.insertCell(1);
		var cCodigo = novaLinha.insertCell(2);
		var cFabricanteNome = novaLinha.insertCell(3);
		var cFabricanteModelo = novaLinha.insertCell(4);
		var cSituacao = novaLinha.insertCell(5);
		var cAcoes = novaLinha.insertCell(6);

		cId.textContent = obj.id;
		cNumeroSerie.textContent = obj.equipamento.numeroSerie;
		cCodigo.textContent = obj.equipamento.codigo;
		cFabricanteNome.textContent = obj.equipamento.fabricante.nome;
		cFabricanteModelo.textContent = obj.equipamento.fabricante.modelo;
		cSituacao.textContent = obj.equipamento.situacao;
		cAcoes.innerHTML = `<button type="button" id="${obj.id}" onclick="excluirRelacionamentoEquipamentoManutencao(this)" class="btn btn-danger"><i class="bi bi-trash3"></i></button>`;
	});
}

function excluirRelacionamentoEquipamentoManutencao(button) {
	const url = `${base_url}/equipamentos/deletar?id=${Number(button.id)}`;
	$.ajax({
		url: url,
		type: 'GET',
		contentType: 'application/json',
		success: function() {
			buscarEquipamentosPorManutencao();
		},
		error: function(error) {
			console.log('Erro na requisição:', error);
		}
	});
}
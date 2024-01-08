package coruripe.controleativos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import coruripe.controleativos.models.Equipamento;
import coruripe.controleativos.models.EquipamentosManutencao;
import coruripe.controleativos.models.PropostaManutencao;
import coruripe.controleativos.models.RequisicaoCompra;
import coruripe.controleativos.models.records.Records.EquipamentoManutencaoRecord;
import coruripe.controleativos.models.records.Records.PropostaManutencaoRecord;
import coruripe.controleativos.models.records.Records.PropostaRelacionadaRecord;
import coruripe.controleativos.models.records.Records.RequisicaoCompraRecord;
import coruripe.controleativos.service.CadastroService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	CadastroService cadastroService;

	/* -------------------------< Proposta >------------------------- */

	@GetMapping("/proposta/consultar")
	public ResponseEntity<List<PropostaRelacionadaRecord>> consultarProposta(@RequestParam Integer idManutencao) {
		List<PropostaRelacionadaRecord> propostas = new ArrayList<>();
		for (Object[] linha : this.cadastroService.buscarPropostasPorEquipamentosPorManutencao(idManutencao)) {
			propostas.add(new PropostaRelacionadaRecord(Integer.parseInt(linha[0].toString()),
					Integer.parseInt(linha[1].toString()), Integer.parseInt(linha[2].toString()),
					linha[3] == null ? "" : linha[3].toString(),
					Integer.parseInt(linha[4] == null ? "0" : linha[4].toString()),
					linha[5] == null ? "" : linha[5].toString(), linha[6] == null ? "" : linha[6].toString(),
					Integer.parseInt(linha[7] == null ? "0" : linha[7].toString())));
		}
		return ResponseEntity.status(HttpStatus.OK).body(propostas);
	}

	@PostMapping("/proposta/salvar")
	public @ResponseBody ResponseEntity<String> salvarProposta(@RequestBody PropostaManutencaoRecord pmRecord) {
		PropostaManutencao pm = new PropostaManutencao(this.cadastroService.buscarManutencaoId(pmRecord.idManutencao()),
				this.cadastroService.buscarEquipamentoId(pmRecord.numeroSerieEquipamento()),
				this.cadastroService.buscarEmpresaId(pmRecord.idEmpresa()), pmRecord);
		this.cadastroService.salvarPropostaManutencao(pm);
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}

	@GetMapping("/proposta/deletar")
	public ResponseEntity<String> deletarProposta(@RequestParam Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.cadastroService.excluirPropostaManutencao(id));
	}

	@GetMapping("/rc/consultar")
	public ResponseEntity<List<RequisicaoCompra>> consultarRcs(@RequestParam Integer idManutencao) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.cadastroService.buscarTodasRCsPorManutencao(idManutencao));
	}

	@PostMapping("/rc/salvar")
	public @ResponseBody ResponseEntity<String> salvarRcs(@RequestBody RequisicaoCompraRecord rcRecord) {
		return ResponseEntity.status(HttpStatus.OK).body(this.cadastroService.salvarRc(rcRecord));
	}

	@PostMapping("/rc/deletar")
	public @ResponseBody ResponseEntity<String> deletarRcs(@RequestBody RequisicaoCompraRecord rcRecord) {
		return ResponseEntity.status(HttpStatus.OK).body(this.cadastroService.excluirRC(rcRecord));
	}

	@PostMapping("/equipamentos/salvar")
	public @ResponseBody ResponseEntity<String> salvarEquipamentos(@RequestBody EquipamentoManutencaoRecord emRecord) {
		this.cadastroService.salvarEquipamentoManutencao(emRecord);
		return ResponseEntity.status(HttpStatus.OK).body("Sucesso");
	}

	@GetMapping("/equipamentos/consultar")
	public ResponseEntity<List<EquipamentosManutencao>> consultarEquipamentos(@RequestParam Integer idManutencao) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.cadastroService.buscarEquipamentosPorManutencao(idManutencao));
	}

	@GetMapping("/equipamentos/consultar-equipamento")
	public ResponseEntity<List<Equipamento>> consultarEquipamento(@RequestParam Integer numserie) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.cadastroService.buscarEquipamentosPorNumeroSerie(numserie));
	}

	@GetMapping("/equipamentos/deletar")
	public ResponseEntity<String> deletarEquipamentos(@RequestParam Integer id) {
		this.cadastroService.deletarEquipamentosPorManutencao(id);
		return ResponseEntity.status(HttpStatus.OK).body("sucesso");
	}

}

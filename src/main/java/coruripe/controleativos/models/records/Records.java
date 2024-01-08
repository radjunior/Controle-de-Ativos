package coruripe.controleativos.models.records;

import java.io.Serializable;
import java.time.LocalDate;

public class Records {

	public record AplicacaoRecord(String tag, String descricao, Integer numSerieEquipamento) {
	}

	public record UsuarioRecord(String usuario, String senha, String confirmSenha, String nomeCompleto, String perfil) {
	}

	public record RequisicaoCompraRecord(Integer requisicaoCompra, Integer idManutencao, String sitMemorizacao,
			String valorTotal) {
	}

	public record PropostaRelacionadaRecord(Integer idEquipamento, Integer numeroSerie, Integer codigo,
			String nomeEmpresa, Integer item, String descricao, String valor, Integer idProposta) {
	}

	public record PropostaManutencaoRecord(Integer id, Integer idManutencao, Integer numeroSerieEquipamento,
			Integer idEmpresa, Integer item, String descricao, String valor) implements Serializable {
	}

	public record ManutencaoEquipamentoRecord(Integer id, LocalDate dataEnvio, LocalDate dataRetorno, String tipo) {
	}

	public record FabricanteRecord(Integer id, String nome, String modelo) {
	}

	public record EquipamentoRecord(Integer numeroSerie, Integer codigo, Integer idFabricante, String situacao) {
	}

	public record EquipamentoManutencaoRecord(Integer idManutencao, Integer numeroSerieEquipamento) {
	}

	public record EmpresaRecord(Integer id, String nome) {
	}

	public record CalibracaoEquipamentoRecord(Integer id, Integer numeroSerieEquipamento, Integer idEmpresa,
			LocalDate dataCalibracao, String situacao) {
	}
}

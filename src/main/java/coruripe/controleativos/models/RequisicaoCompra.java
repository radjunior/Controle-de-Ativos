package coruripe.controleativos.models;

import java.math.BigDecimal;

import coruripe.controleativos.models.records.Records.RequisicaoCompraRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rc_manut_externa")
public class RequisicaoCompra {

	@Id
	@Column(name = "req_compra")
	private Integer reqCompra;

	@ManyToOne
	@JoinColumn(name = "id_manut_equipamento")
	private ManutencaoEquipamento manutencaoEquipamento;

	@Column(name = "sit_memorizacao")
	private String memorizacao;

	@Column(name = "val_total")
	private BigDecimal valorTotal;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public RequisicaoCompra() {

	}

	public RequisicaoCompra(RequisicaoCompraRecord rcRecord, String currentUser) {
		this.reqCompra = rcRecord.requisicaoCompra();
		this.manutencaoEquipamento = new ManutencaoEquipamento(rcRecord.idManutencao());
		this.memorizacao = rcRecord.sitMemorizacao();
		this.valorTotal = parseToBigDecimal(rcRecord.valorTotal());
		this.usuario = new Usuario(currentUser);
	}

	private BigDecimal parseToBigDecimal(String valorTotal) {
		valorTotal = valorTotal.replace(",", ".");
		return new BigDecimal(valorTotal);
	}

	public Integer getReqCompra() {
		return reqCompra;
	}

	public void setReqCompra(Integer reqCompra) {
		this.reqCompra = reqCompra;
	}

	public ManutencaoEquipamento getManutencaoEquipamento() {
		return manutencaoEquipamento;
	}

	public void setManutencaoEquipamento(ManutencaoEquipamento manutencaoEquipamento) {
		this.manutencaoEquipamento = manutencaoEquipamento;
	}

	public String getMemorizacao() {
		return memorizacao;
	}

	public void setMemorizacao(String memorizacao) {
		this.memorizacao = memorizacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "RCManutencaoExterna [reqCompra=" + reqCompra + ", manutencaoEquipamento=" + manutencaoEquipamento
				+ ", memorizacao=" + memorizacao + ", valorTotal=" + valorTotal + ", usuario=" + usuario + "]";
	}

}

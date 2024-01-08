package coruripe.controleativos.models;

import java.math.BigDecimal;

import coruripe.controleativos.models.records.Records.PropostaManutencaoRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "proposta_manut")
public class PropostaManutencao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_manut_equipamento")
	private ManutencaoEquipamento manutencaoEquipamento;

	@ManyToOne
	@JoinColumn(name = "id_equipamento")
	private Equipamento equipamento;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "item")
	private Integer item;

	@Column(name = "valor")
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public PropostaManutencao() {
	}

	public PropostaManutencao(ManutencaoEquipamento manutencaoEquipamento, Equipamento equipamento, Empresa empresa,
			PropostaManutencaoRecord pmr) {
		this.id = pmr.id();
		this.manutencaoEquipamento = manutencaoEquipamento;
		this.equipamento = equipamento;
		this.empresa = empresa;
		this.descricao = pmr.descricao();
		this.item = pmr.item();
		this.valor = this.parseToBigDecimal(pmr.valor());
	}

	private BigDecimal parseToBigDecimal(String valor) {
		valor = valor.replace(",", ".");
		return new BigDecimal(valor);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ManutencaoEquipamento getManutencaoEquipamento() {
		return manutencaoEquipamento;
	}

	public void setManutencaoEquipamento(ManutencaoEquipamento manutencaoEquipamento) {
		this.manutencaoEquipamento = manutencaoEquipamento;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "PropostaManutencao [id=" + id + ", manutencaoEquipamento=" + manutencaoEquipamento + ", equipamento="
				+ equipamento + ", empresa=" + empresa + ", descricao=" + descricao + ", item=" + item + ", valor="
				+ valor + ", usuario=" + usuario + "]";
	}

}

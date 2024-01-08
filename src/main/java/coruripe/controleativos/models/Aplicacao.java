package coruripe.controleativos.models;

import coruripe.controleativos.models.records.Records.AplicacaoRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "aplicacao")
public class Aplicacao {

	@Id
	@Column(name = "tag")
	private String tag;

	@Column(name = "descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_equipamento")
	private Equipamento equipamento;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Aplicacao() {
	}

	public Aplicacao(AplicacaoRecord aplicacaoRecord, String currentUser) {
		this.tag = aplicacaoRecord.tag();
		this.descricao = aplicacaoRecord.descricao();
		this.equipamento = new Equipamento(aplicacaoRecord.numSerieEquipamento());
		this.usuario = new Usuario(currentUser);
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Aplicacao [tag=" + tag + ", descricao=" + descricao + ", equipamento=" + equipamento + ", usuario="
				+ usuario + "]";
	}

}

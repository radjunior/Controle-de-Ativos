package coruripe.controleativos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipamento")
public class Equipamento {

	@Id
	@Column(name = "num_serie")
	private Integer numeroSerie;

	@Column(name = "codigo")
	private Integer codigo;

	@ManyToOne
	@JoinColumn(name = "id_fabricante")
	private Fabricante fabricante;

	@Column(name = "situacao")
	private String situacao;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Equipamento() {
	}

	public Equipamento(Integer numeroSerie, Integer codigo, Fabricante fabricante, String situacao,
			String currentUser) {
		this.numeroSerie = numeroSerie;
		this.codigo = codigo;
		this.fabricante = fabricante;
		this.situacao = situacao;
		this.usuario = new Usuario(currentUser);
	}

	public Equipamento(Integer numSerieEquipamento) {
		this.numeroSerie = numSerieEquipamento;
	}

	public Integer getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(Integer numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Equipamento [numeroSerie=" + numeroSerie + ", codigo=" + codigo + ", fabricante="
				+ fabricante.toString() + ", situacao=" + situacao + ", usuario=" + usuario + "]";
	}

}

package coruripe.controleativos.models;

import coruripe.controleativos.models.records.Records.EquipamentoRecord;
import coruripe.controleativos.models.records.Records.FabricanteRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fabricante")
public class Fabricante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "modelo")
	private String modelo;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Fabricante() {
	}

	public Fabricante(FabricanteRecord fabRecord, String currentUser) {
		this.id = fabRecord.id();
		this.nome = fabRecord.nome();
		this.modelo = fabRecord.modelo();
		this.usuario = new Usuario(currentUser);
	}

	public Fabricante(EquipamentoRecord equipamentoRecord) {
		this.id = equipamentoRecord.idFabricante();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Fabricante [id=" + id + ", nome=" + nome + ", modelo=" + modelo + ", usuario=" + usuario + "]";
	}

}

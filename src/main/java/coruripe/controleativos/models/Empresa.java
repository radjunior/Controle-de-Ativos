package coruripe.controleativos.models;

import coruripe.controleativos.models.records.Records.EmpresaRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Empresa() {
	}

	public Empresa(EmpresaRecord empresaRecord, String currentUser) {
		this.id = empresaRecord.id();
		this.nome = empresaRecord.nome();
		this.usuario = new Usuario(currentUser);
	}

	public Empresa(Integer idEmpresa) {
		this.id = idEmpresa;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + ", usuario=" + usuario + "]";
	}

}

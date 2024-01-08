package coruripe.controleativos.models;

import java.time.LocalDate;
import java.util.List;

import coruripe.controleativos.models.records.Records.ManutencaoEquipamentoRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "manut_equipamento")
public class ManutencaoEquipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "data_envio")
	private LocalDate dataEnvio;

	@Column(name = "data_retorno")
	private LocalDate dataRetorno;

	@Column(name = "tipo")
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@OneToMany
	@JoinColumn(name = "id_equipamento")
	private List<Equipamento> equipamentos;

	public ManutencaoEquipamento() {
	}

	public ManutencaoEquipamento(ManutencaoEquipamentoRecord mer, List<Equipamento> equipamentos, String currentUser) {
		this.id = mer.id();
		this.dataEnvio = mer.dataEnvio();
		this.dataRetorno = mer.dataRetorno();
		this.tipo = mer.tipo();
		this.usuario = new Usuario(currentUser);
		this.equipamentos = equipamentos;
	}

	public ManutencaoEquipamento(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public LocalDate getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(LocalDate dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "ManutencaoEquipamento [id=" + id + ", dataEnvio=" + dataEnvio + ", dataRetorno=" + dataRetorno
				+ ", tipo=" + tipo + ", usuario=" + usuario + "]";
	}

}

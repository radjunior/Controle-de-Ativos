package coruripe.controleativos.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "calibra_equipamento")
public class CalibracaoEquipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_equipamento")
	private Equipamento equipamento;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column(name = "data_calibracao")
	private LocalDate dataCalibracao;

	@Column(name = "situacao")
	private String situacao;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public CalibracaoEquipamento() {
	}

	public CalibracaoEquipamento(Integer id, Equipamento equipamento, Empresa empresa, LocalDate dataCalibracao,
			String situacao, String currentUser) {
		this.id = id;
		this.equipamento = equipamento;
		this.empresa = empresa;
		this.dataCalibracao = dataCalibracao;
		this.situacao = situacao;
		this.usuario = new Usuario(currentUser);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public LocalDate getDataCalibracao() {
		return dataCalibracao;
	}

	public void setDataCalibracao(LocalDate dataCalibracao) {
		this.dataCalibracao = dataCalibracao;
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
		return "CalibracaoEquipamento [id=" + id + ", equipamento=" + equipamento + ", empresa=" + empresa
				+ ", dataCalibracao=" + dataCalibracao + ", situacao=" + situacao + ", usuario=" + usuario + "]";
	}

}

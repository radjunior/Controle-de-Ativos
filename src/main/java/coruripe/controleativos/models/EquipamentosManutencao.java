package coruripe.controleativos.models;

import coruripe.controleativos.models.records.Records.EquipamentoManutencaoRecord;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipamentos_manutencao")
public class EquipamentosManutencao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_manutencao")
	private ManutencaoEquipamento manutencaoEquipamento;

	@ManyToOne
	@JoinColumn(name = "id_equipamento")
	private Equipamento equipamento;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	public EquipamentosManutencao() {
	}

	public EquipamentosManutencao(Integer id, ManutencaoEquipamento manutencaoEquipamento, Equipamento equipamento) {
		super();
		this.id = id;
		this.manutencaoEquipamento = manutencaoEquipamento;
		this.equipamento = equipamento;
	}

	public EquipamentosManutencao(EquipamentoManutencaoRecord emRecord, String currentUser) {
		this.manutencaoEquipamento = new ManutencaoEquipamento(emRecord.idManutencao());
		this.equipamento = new Equipamento(emRecord.numeroSerieEquipamento());
		this.usuario = new Usuario(currentUser);
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

	@Override
	public String toString() {
		return "EquipamentosManutencao [id=" + id + ", manutencaoEquipamento=" + manutencaoEquipamento.toString()
				+ ", equipamento=" + equipamento.toString() + "]";
	}

}

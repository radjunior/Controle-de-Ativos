package coruripe.controleativos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import coruripe.controleativos.models.EquipamentosManutencao;

public interface EquipamentoManutencaoRepository extends JpaRepository<EquipamentosManutencao, Integer>{

	@Query("SELECT p FROM EquipamentosManutencao p WHERE p.manutencaoEquipamento.id = ?1")
	List<EquipamentosManutencao> buscarManutencaoEquipamentoId(Integer idManutencao);
	
}

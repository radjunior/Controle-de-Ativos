package coruripe.controleativos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coruripe.controleativos.models.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {

	List<Equipamento> findByFabricanteId(Integer id);

	List<Equipamento> findByNumeroSerie(Integer numserie);

}

package coruripe.controleativos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coruripe.controleativos.models.CalibracaoEquipamento;

public interface CalibracaoEquipamentoRepository extends JpaRepository<CalibracaoEquipamento, Integer> {

	List<CalibracaoEquipamento> findByEquipamentoNumeroSerie(Integer numeroSerie);

	List<CalibracaoEquipamento> findByEmpresaId(Integer id);

}

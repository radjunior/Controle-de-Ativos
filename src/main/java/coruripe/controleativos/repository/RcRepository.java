package coruripe.controleativos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import coruripe.controleativos.models.RequisicaoCompra;

@Transactional
public interface RcRepository extends JpaRepository<RequisicaoCompra, Integer> {

	@Query("SELECT r FROM RequisicaoCompra r WHERE r.manutencaoEquipamento.id = ?1")
	List<RequisicaoCompra> buscarTodasRCsPorManutencao(Integer idManutencao);

	@Modifying
	@Query("DELETE FROM RequisicaoCompra r WHERE r.reqCompra = ?1 AND r.manutencaoEquipamento.id = ?2")
	void excluirPorIdManutencao(Integer requisicaoCompra, Integer idManutencao);

	@Modifying
	@Query("DELETE FROM RequisicaoCompra r WHERE r.manutencaoEquipamento.id = ?1")
	void excluirPorIdManutencao(Integer id);

	@Query("SELECT r FROM RequisicaoCompra r WHERE r.memorizacao = 'S'")
	List<RequisicaoCompra> findByMemorizacao();

}

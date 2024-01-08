package coruripe.controleativos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import coruripe.controleativos.models.PropostaManutencao;

@Transactional
public interface PropostaManutencaoRepository extends JpaRepository<PropostaManutencao, Integer> {

	@Query("SELECT p FROM PropostaManutencao p WHERE p.manutencaoEquipamento.id = ?1")
	List<PropostaManutencao> buscarPropostasManutencaoId(Integer idManutencao);

	@Modifying
	@Query("DELETE FROM PropostaManutencao p WHERE p.manutencaoEquipamento.id = ?1")
	void excluirPorIdManutencao(Integer idManutencao);

	List<PropostaManutencao> findByEquipamentoNumeroSerie(Integer numeroSerie);

	List<PropostaManutencao> findByEmpresaId(Integer id);

	@Query(value = " 	SELECT  em.id as \"id_equipamento\",			"
				+ "				eq.num_serie,							"
				+ "				eq.codigo,								"
				+ "				ep.nome as \"empresa\",					"
				+ "				pm.item,								"
				+ "				pm.descricao,							"
				+ "				pm.valor,								"
				+ "				pm.id as \"id_proposta\"				"
				+ "      FROM 	manut_equipamento me					"
				+ "INNER JOIN 	equipamentos_manutencao em 				"
				+ "        ON 	me.id = em.id_manutencao				"
				+ "INNER JOIN 	equipamento eq 							"
				+ "		   ON 	eq.num_serie = em.id_equipamento		"
				+ "LEFT  JOIN 	proposta_manut pm 						"
				+ "		   ON 	pm.id_manut_equipamento = me.id 		"
				+ "	      AND 	pm.id_equipamento = em.id_equipamento	"
				+ "	      AND 	pm.id_equipamento = eq.num_serie		"
				+ "LEFT  JOIN 	empresa ep 								"
				+ "		   ON 	ep.id = pm.id_empresa					"
				+ "WHERE		me.id = ?1								"
				+ "ORDER   BY	em.id									", nativeQuery = true)
	List<Object[]> buscarPropostasPorEquipamentosPorManutencao(Integer idManutencao);

}

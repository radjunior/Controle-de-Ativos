package coruripe.controleativos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coruripe.controleativos.models.Aplicacao;

public interface AplicacaoRepository extends JpaRepository<Aplicacao, String> {

	List<Aplicacao> findByEquipamentoNumeroSerie(Integer num);

}

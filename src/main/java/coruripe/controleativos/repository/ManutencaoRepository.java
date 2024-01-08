package coruripe.controleativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coruripe.controleativos.models.ManutencaoEquipamento;

public interface ManutencaoRepository extends JpaRepository<ManutencaoEquipamento, Integer> {

}

package coruripe.controleativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coruripe.controleativos.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}

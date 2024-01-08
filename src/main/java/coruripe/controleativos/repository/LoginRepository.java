package coruripe.controleativos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import coruripe.controleativos.models.Usuario;

public interface LoginRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByLoginUsuario(String usuario);

}

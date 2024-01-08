package coruripe.controleativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import coruripe.controleativos.models.Usuario;

@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	@Modifying @Query(value = "DELETE FROM authorities WHERE username = ?1", nativeQuery = true)
	void deletePerfilPorUsuario(String usuario);

	@Modifying @Query(value = "INSERT INTO authorities (username, authority) VALUES (?1, ?2)", nativeQuery = true)
	void createPerfil(String usuario, String perfil);
	
	@Query(value = "SELECT * FROM authorities WHERE username = ?1", nativeQuery = true)
	void consultPerfilPorUsuario(String usuario);

	@Query(value = "SELECT * FROM authorities WHERE authority = ?1", nativeQuery = true)
	void consultPerfilPorPerfil(String perfil);

}

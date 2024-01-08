package coruripe.controleativos.models;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import coruripe.controleativos.models.records.Records.UsuarioRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Usuario {
	
	@Id
	@Column(name = "username")
	private String loginUsuario;

	@Column(name = "password")
	private String loginSenha;
	
	@Column(name = "nome_completo")
	private String nomeCompleto;
	
	@Column(name = "enabled")
	private boolean situacao;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "data_cadastro")
	private LocalDateTime dataHoraCadastro;
	
	public Usuario() {
	}

	public Usuario(UsuarioRecord usuarioRecord, String currentUser) {
		this.nomeCompleto = usuarioRecord.nomeCompleto().toUpperCase();
		this.loginUsuario = usuarioRecord.usuario();
		this.loginSenha = new BCryptPasswordEncoder().encode(usuarioRecord.senha());
		this.usuario = new Usuario(currentUser);
		this.dataHoraCadastro = LocalDateTime.now();
	}

	public Usuario(String currentUsername) {
		this.loginUsuario = currentUsername;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getLoginSenha() {
		return loginSenha;
	}

	public void setLoginSenha(String loginSenha) {
		this.loginSenha = loginSenha;
	}

	public boolean isValid() {
		if (!this.nomeCompleto.equals("") || !this.loginUsuario.equals("") || !this.loginSenha.equals(""))
			return true;
		return false;
	}

	public boolean isLoginTrue(UsuarioRecord usuarioRecord) {
		if (this.loginSenha.equals(usuarioRecord.senha()))
			return true;
		return false;
	}

	public Usuario getSaveSession() {
		this.loginSenha = null;
		return this;
	}

	@Override
	public String toString() {
		return "Usuario [loginUsuario=" + loginUsuario + ", loginSenha=" + loginSenha + ", nomeCompleto=" + nomeCompleto
				+ ", situacao=" + situacao + "]";
	}

}

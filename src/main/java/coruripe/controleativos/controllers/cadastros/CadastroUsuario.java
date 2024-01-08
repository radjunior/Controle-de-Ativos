package coruripe.controleativos.controllers.cadastros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import coruripe.controleativos.models.records.Records.UsuarioRecord;
import coruripe.controleativos.service.CadastroService;

@Controller
public class CadastroUsuario {

	@Autowired
	CadastroService cadastroService;

	@GetMapping("/cad/usuario/")
	public ModelAndView indexConsultar() {
		return new ModelAndView("redirect:/cad/usuario/consultar");
	}

	@GetMapping("/cad/usuario/salvar")
	public ModelAndView salvarToConsultar() {
		return new ModelAndView("redirect:/cad/usuario/consultar");
	}

	@GetMapping("/cad/usuario/consultar")
	public ModelAndView consultar() {
		return new ModelAndView("cad_usuario").addObject("usuarios", this.cadastroService.buscarTodosUsuarios());
	}

	@PostMapping("/cad/usuario/salvar")
	public ModelAndView salvar(UsuarioRecord usuarioRecord) {
		ModelAndView mav = new ModelAndView("cad_usuario");

		if (!usuarioRecord.senha().equals(usuarioRecord.confirmSenha())) {
			mav.addObject("error", "Senhas não coincidem!");

		} else if (this.cadastroService.buscarUsuarioLogin(usuarioRecord).isPresent()) {
			this.cadastroService.deletarPerfilPorUsuario(usuarioRecord);
			this.cadastroService.salvarUsuario(usuarioRecord);
			this.cadastroService.salvarPerfil(usuarioRecord);
			mav.addObject("success", "Usuário atualizado com sucesso!");

		} else {
			this.cadastroService.salvarUsuario(usuarioRecord);
			this.cadastroService.salvarPerfil(usuarioRecord);
			mav.addObject("success", "Usuário salvo com sucesso!");
		}

		mav.addObject("usuarios", this.cadastroService.buscarTodosUsuarios());
		return mav;
	}

	@PostMapping("/cad/usuario/deletar")
	public ModelAndView deletar(UsuarioRecord usuarioRecord) {
		ModelAndView mav = new ModelAndView("cad_usuario");

		// this.cadastroService.excluirUsuario(usuarioRecord);
		mav.addObject("error", "Não é possível excluir um usuário, entre em contato com o departamento de TI!");

		mav.addObject("usuarios", this.cadastroService.buscarTodosUsuarios());
		return mav;
	}
}

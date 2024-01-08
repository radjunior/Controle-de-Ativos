package coruripe.controleativos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import coruripe.controleativos.repository.LoginRepository;

@Controller
public class LoginController {

	@Autowired
	LoginRepository loginRepository;

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	/*@PostMapping("/login")
	public ModelAndView logar(UsuarioRecord usuarioRecord, HttpServletRequest req) {
		Optional<Usuario> usuario = this.loginRepository.findByLoginUsuario(usuarioRecord.usuario());

		if (usuario.isPresent() && usuario.get().isLoginTrue(usuarioRecord)) {
			Usuario user = usuario.get().getSaveSession();
			req.getSession().setAttribute("usuario", user);
			
			// Sessão com duração máxima de 10 minutos
			req.getSession().setMaxInactiveInterval(600);
			return new ModelAndView("redirect:/dashboard");
		}

		return new ModelAndView("login").addObject("error", "Credenciais inválidas");
	}*/

	/*@PostMapping("/logout")
	public ModelAndView logout(HttpServletRequest req) {
		req.getSession().invalidate();
		return new ModelAndView("redirect:/login");
	}*/
}

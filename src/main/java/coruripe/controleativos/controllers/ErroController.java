package coruripe.controleativos.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErroController implements ErrorController {

	@GetMapping("/error")
	public ModelAndView handleError() {
		return new ModelAndView("error");
	}
	
	@GetMapping("/error/access-denied")
	public ModelAndView accessDenied() {
		return new ModelAndView("access_denied");
	}

}

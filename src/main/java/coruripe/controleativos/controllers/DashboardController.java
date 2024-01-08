package coruripe.controleativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import coruripe.controleativos.models.RequisicaoCompra;
import coruripe.controleativos.service.CadastroService;

@Controller
public class DashboardController {

	@Autowired
	CadastroService cadastroService;
	
	@GetMapping("/dashboard")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("dashboard");
		List<RequisicaoCompra> rcs = this.cadastroService.buscarRcsMemorizadas();
		mav.addObject("rcsMemorizadas", rcs);
		mav.addObject("countRcsMemorizadas", rcs.size());
		return mav;
	}
}

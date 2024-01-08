package coruripe.controleativos.controllers.lancamentos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import coruripe.controleativos.models.records.Records.CalibracaoEquipamentoRecord;
import coruripe.controleativos.service.CadastroService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LancamentoCalibracao {

	@Autowired
	CadastroService cadastroService;

	@GetMapping("/nova/calibracao/")
	public ModelAndView consult() {
		return new ModelAndView("redirect:/nova/calibracao/consultar");
	}

	@GetMapping("/nova/calibracao/consultar")
	public ModelAndView consultar() {
		return new ModelAndView("lanc_calibracao").addAllObjects(this.buscarListas());
	}

	@PostMapping("/nova/calibracao/salvar")
	public ModelAndView salvar(CalibracaoEquipamentoRecord calibracaoEquipamentoRecord, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("lanc_calibracao");
		
		this.cadastroService.salvarCalibracao(calibracaoEquipamentoRecord);
		mav.addObject("success", "Calibração salva com sucesso!");

		mav.addAllObjects(this.buscarListas());
		return mav;
	}

	@PostMapping("/nova/calibracao/deletar")
	public ModelAndView deletar(CalibracaoEquipamentoRecord calibracaoEquipamentoRecord) {
		ModelAndView mav = new ModelAndView("lanc_calibracao");
		
		this.cadastroService.excluirCalibracao(calibracaoEquipamentoRecord);
		mav.addObject("success", "Calibração excluída com sucesso!");
		
		mav.addAllObjects(this.buscarListas());
		return mav;
	}

	private Map<String, Object> buscarListas() {
		Map<String, Object> listas = new HashMap<>();
		listas.put("equipamentos", this.cadastroService.buscarTodosEquipamentos());
		listas.put("empresas", this.cadastroService.buscarTodasEmpresas());
		listas.put("calibracoesEquipamentos", this.cadastroService.buscarTodasCalibracoes());
		return listas;
	}
}

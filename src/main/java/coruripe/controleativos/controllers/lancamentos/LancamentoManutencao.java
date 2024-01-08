package coruripe.controleativos.controllers.lancamentos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import coruripe.controleativos.models.Equipamento;
import coruripe.controleativos.models.records.Records.ManutencaoEquipamentoRecord;
import coruripe.controleativos.service.CadastroService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LancamentoManutencao {

	@Autowired
	CadastroService cadastroService;

	@GetMapping("/nova/proposta/")
	public ModelAndView propostaToIndex() {
		return new ModelAndView("redirect:/nova/manutencao/consultar");
	}

	@GetMapping("/nova/manutencao/")
	public ModelAndView index() {
		return new ModelAndView("redirect:/nova/manutencao/consultar");
	}

	@GetMapping("/nova/manutencao/consultar")
	public ModelAndView consultar() {
		return new ModelAndView("lanc_manutencao").addAllObjects(this.buscarListas());
	}
	
	@PostMapping("/nova/manutencao/salvar")
	public ModelAndView salvar(ManutencaoEquipamentoRecord mer, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("lanc_manutencao");
		
		this.cadastroService.salvarManutencao(mer, new ArrayList<Equipamento>());
		mav.addObject("success", "Manutenção salva com sucesso!");
		
		mav.addAllObjects(this.buscarListas());
		return mav;
	}
	
	@PostMapping("/nova/manutencao/deletar")
	public ModelAndView deletar(ManutencaoEquipamentoRecord mer) {
		ModelAndView mav = new ModelAndView("lanc_manutencao");
		
		this.cadastroService.excluirManutencao(mer);
		mav.addObject("success", "Manutenção excluída com sucesso!");
		
		mav.addAllObjects(this.buscarListas());
		return mav;
	}

	private Map<String, Object> buscarListas() {
		Map<String, Object> listas = new HashMap<>();
		listas.put("equipamentos", this.cadastroService.buscarTodosEquipamentos());
		listas.put("empresas", this.cadastroService.buscarTodasEmpresas());
		listas.put("manutencoes", this.cadastroService.buscarTodasManutencoes());
		return listas;
	}

}

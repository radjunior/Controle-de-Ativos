package coruripe.controleativos.controllers.cadastros;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import coruripe.controleativos.models.records.Records.EquipamentoRecord;
import coruripe.controleativos.service.CadastroService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CadastroEquipamento {

	@Autowired
	CadastroService cadastroService;

	@GetMapping("/cad/equipamento/")
	public ModelAndView index() {
		return new ModelAndView("redirect:/cad/equipamento/consultar");
	}

	@GetMapping("/cad/equipamento/consultar")
	public ModelAndView consultar() {
		return new ModelAndView("cad_equipamento").addAllObjects(this.buscarListas());
	}

	@PostMapping("/cad/equipamento/salvar")
	public ModelAndView salvar(EquipamentoRecord equipamentoRecord, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("cad_equipamento");

		this.cadastroService.salvarEquipamento(equipamentoRecord);
		mav.addObject("success", "Equipamento salvo com sucesso!");

		mav.addAllObjects(this.buscarListas());
		return mav;
	}

	@PostMapping("/cad/equipamento/deletar")
	public ModelAndView deletar(EquipamentoRecord equipamentoRecord) {
		ModelAndView mav = new ModelAndView("cad_equipamento");

		if (!this.cadastroService.buscarCalibracaoPorNumSerieEquipamento(equipamentoRecord.numeroSerie()).isEmpty()) {
			mav.addObject("error", "Há uma calibração relacionada com esse equipamento!");

		} else if (!this.cadastroService.buscarPropostaPorNumSerieEquipamento(equipamentoRecord.numeroSerie())
				.isEmpty()) {
			mav.addObject("error", "Há uma proposta de manutenção relacionada com esse equipamento!");

		} else if (!this.cadastroService.buscarAplicacaoPorNumSerieEquipamento(equipamentoRecord.numeroSerie())
				.isEmpty()) {
			mav.addObject("error", "Há uma aplicação relacionada com esse equipamento!");
		} else {
			this.cadastroService.excluirEquipamento(equipamentoRecord);
			mav.addObject("success", "Equipamento excluído com sucesso!");
		}

		mav.addAllObjects(this.buscarListas());
		return mav;
	}

	private Map<String, Object> buscarListas() {
		Map<String, Object> listas = new HashMap<>();
		listas.put("equipamentos", this.cadastroService.buscarTodosEquipamentos());
		listas.put("fabricantes", this.cadastroService.buscarTodosFabricantes());
		return listas;
	}
}

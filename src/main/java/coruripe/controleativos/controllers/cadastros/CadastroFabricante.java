package coruripe.controleativos.controllers.cadastros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import coruripe.controleativos.models.records.Records.FabricanteRecord;
import coruripe.controleativos.service.CadastroService;

@Controller
public class CadastroFabricante {

	@Autowired
	CadastroService cadastroService;

	@GetMapping("/cad/fabricante/")
	public ModelAndView indexConsultar() {
		return new ModelAndView("redirect:/cad/fabricante/consultar");
	}

	@GetMapping("/cad/fabricante/consultar")
	public ModelAndView consultar() {
		return new ModelAndView("cad_fabricante").addObject("fabricantes",
				this.cadastroService.buscarTodosFabricantes());
	}

	@PostMapping("/cad/fabricante/salvar")
	public ModelAndView salvar(FabricanteRecord fabricanteRecord) {
		ModelAndView mav = new ModelAndView("cad_fabricante");

		if (fabricanteRecord.nome().length() > 40 || fabricanteRecord.modelo().length() > 40) {
			if (fabricanteRecord.nome().length() > 40)
				mav.addObject("error", "Nome não pode conter mais que 40 caracteres!");
			else if (fabricanteRecord.modelo().length() > 40)
				mav.addObject("error", "Modelo não pode conter mais que 40 caracteres!");
		} else {
			this.cadastroService.salvarFabricante(fabricanteRecord);
			if (fabricanteRecord.id() != null)
				mav.addObject("success", "Fabricante editado com sucesso!");
			else
				mav.addObject("success", "Fabricante salvo com sucesso!");
		}

		mav.addObject("fabricantes", this.cadastroService.buscarTodosFabricantes());
		return mav;
	}

	@PostMapping("/cad/fabricante/deletar")
	public ModelAndView deletar(FabricanteRecord fabricanteRecord) {
		ModelAndView mav = new ModelAndView("cad_fabricante");

		if (!this.cadastroService.buscarEquipamentoPorIdFabricante(fabricanteRecord.id()).isEmpty()) {
			mav.addObject("error", "Há um Equipamento relacionado com esse Fabricante!");

		} else {
			this.cadastroService.excluirFabricante(fabricanteRecord);
			mav.addObject("success", "Fabricante excluído com sucesso!");
		}

		mav.addObject("fabricantes", this.cadastroService.buscarTodosFabricantes());
		return mav;
	}
}

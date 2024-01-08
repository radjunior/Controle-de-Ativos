package coruripe.controleativos.controllers.cadastros;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import coruripe.controleativos.models.records.Records.AplicacaoRecord;
import coruripe.controleativos.service.CadastroService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CadastroAplicacao {

	@Autowired
	CadastroService cadastroService;

	@GetMapping("/cad/aplicacao/")
	public ModelAndView index() {
		return new ModelAndView("redirect:/cad/aplicacao/consultar");
	}

	@GetMapping("/cad/aplicacao/consultar")
	public ModelAndView consultar() {
		ModelAndView mav = new ModelAndView("cad_aplicacao");
		mav.addAllObjects(this.buscarListas());
		return mav;
	}

	@PostMapping("/cad/aplicacao/salvar")
	public ModelAndView salvar(AplicacaoRecord appRecord, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("cad_aplicacao");

		if (appRecord.tag().length() > 20 || appRecord.descricao().length() > 40) {
			if (appRecord.tag().length() > 20) {
				mav.addObject("error", "TAG não pode conter mais de 20 caracteres");

			} else if (appRecord.descricao().length() > 40) {
				mav.addObject("error", "Descrição não pode conter mais de 40 caracteres");
			}

			mav.addAllObjects(this.buscarListas());
			return mav;
		}

		this.cadastroService.salvarAplicacao(appRecord);
		mav.addObject("success", "Aplicação salva com sucesso!");

		mav.addAllObjects(this.buscarListas());
		return mav;
	}

	@PostMapping("/cad/aplicacao/deletar")
	public ModelAndView deletar(AplicacaoRecord aplicacaoRecord) {
		ModelAndView mav = new ModelAndView("cad_aplicacao");

		this.cadastroService.excluirAplicacao(aplicacaoRecord);
		mav.addObject("success", "Aplicação excluída com sucesso!");

		mav.addAllObjects(this.buscarListas());
		return mav;
	}

	private Map<String, Object> buscarListas() {
		Map<String, Object> listas = new HashMap<>();
		listas.put("equipamentos", this.cadastroService.buscarTodosEquipamentos());
		listas.put("aplicacoes", this.cadastroService.buscarTodasAplicacoes());
		return listas;
	}

}

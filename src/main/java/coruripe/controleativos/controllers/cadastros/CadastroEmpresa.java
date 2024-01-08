package coruripe.controleativos.controllers.cadastros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import coruripe.controleativos.models.records.Records.EmpresaRecord;
import coruripe.controleativos.service.CadastroService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CadastroEmpresa {

	@Autowired
	CadastroService cadastroService;

	@GetMapping("/cad/empresa/")
	public ModelAndView index() {
		return new ModelAndView("redirect:/cad/empresa/consultar");
	}

	@GetMapping("/cad/empresa/consultar")
	public ModelAndView consultar() {
		ModelAndView mav = new ModelAndView("cad_empresa");

		mav.addObject("empresas", this.cadastroService.buscarTodasEmpresas());
		return mav;
	}

	@PostMapping("/cad/empresa/salvar")
	public ModelAndView salvar(EmpresaRecord empresaRecord, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("cad_empresa");

		if (empresaRecord.nome().length() > 40) {
			mav.addObject("error", "Nome da empresa não pode ter mais de 40 caracteres!");
		} else {
			this.cadastroService.salvarEmpresa(empresaRecord);
			if (empresaRecord.id() != null)
				mav.addObject("success", "Empresa editada com sucesso!");
			else
				mav.addObject("success", "Empresa salva com sucesso!");
		}

		mav.addObject("empresas", this.cadastroService.buscarTodasEmpresas());
		return mav;
	}

	@PostMapping("/cad/empresa/deletar")
	public ModelAndView deletar(EmpresaRecord empresaRecord) {
		ModelAndView mav = new ModelAndView("cad_empresa");

		if (!this.cadastroService.buscarCalibracaoPorIdEmpresa(empresaRecord.id()).isEmpty()) {
			mav.addObject("error", "Há uma Calibração relacionada com essa Empresa!");

		} else if (!this.cadastroService.buscarPropostaPorIdEmpresa(empresaRecord.id()).isEmpty()) {
			mav.addObject("error", "Há uma Proposta de Manutenção relacionada com essa Empresa!");

		} else {
			this.cadastroService.excluirEmpresa(empresaRecord);
			mav.addObject("success", "Empresa excluída com sucesso!");
		}

		mav.addObject("empresas", this.cadastroService.buscarTodasEmpresas());
		return mav;
	}
}

package coruripe.controleativos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coruripe.controleativos.config.FrameworkAuto;
import coruripe.controleativos.models.Aplicacao;
import coruripe.controleativos.models.CalibracaoEquipamento;
import coruripe.controleativos.models.Empresa;
import coruripe.controleativos.models.Equipamento;
import coruripe.controleativos.models.EquipamentosManutencao;
import coruripe.controleativos.models.Fabricante;
import coruripe.controleativos.models.ManutencaoEquipamento;
import coruripe.controleativos.models.PropostaManutencao;
import coruripe.controleativos.models.RequisicaoCompra;
import coruripe.controleativos.models.Usuario;
import coruripe.controleativos.models.records.Records.AplicacaoRecord;
import coruripe.controleativos.models.records.Records.CalibracaoEquipamentoRecord;
import coruripe.controleativos.models.records.Records.EmpresaRecord;
import coruripe.controleativos.models.records.Records.EquipamentoManutencaoRecord;
import coruripe.controleativos.models.records.Records.EquipamentoRecord;
import coruripe.controleativos.models.records.Records.FabricanteRecord;
import coruripe.controleativos.models.records.Records.ManutencaoEquipamentoRecord;
import coruripe.controleativos.models.records.Records.RequisicaoCompraRecord;
import coruripe.controleativos.models.records.Records.UsuarioRecord;
import coruripe.controleativos.repository.AplicacaoRepository;
import coruripe.controleativos.repository.CalibracaoEquipamentoRepository;
import coruripe.controleativos.repository.EmpresaRepository;
import coruripe.controleativos.repository.EquipamentoManutencaoRepository;
import coruripe.controleativos.repository.EquipamentoRepository;
import coruripe.controleativos.repository.FabricanteRepository;
import coruripe.controleativos.repository.ManutencaoRepository;
import coruripe.controleativos.repository.PropostaManutencaoRepository;
import coruripe.controleativos.repository.RcRepository;
import coruripe.controleativos.repository.UsuarioRepository;

@Service
public class CadastroService extends FrameworkAuto {

	@Autowired
	FabricanteRepository fabricanteRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	EquipamentoRepository equipamentoRepository;

	@Autowired
	AplicacaoRepository aplicacaoRepository;

	@Autowired
	CalibracaoEquipamentoRepository calibracaoEquipamentoRepository;

	@Autowired
	PropostaManutencaoRepository propostaManutencaoRepository;

	@Autowired
	ManutencaoRepository manutencaoRepository;

	@Autowired
	RcRepository rcRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EquipamentoManutencaoRepository equipamentoManutencaoRepository;

	/*--------------------------------< FABRICANTE >--------------------------------*/

	public List<Fabricante> buscarTodosFabricantes() {
		return this.fabricanteRepository.findAll();
	}

	public void salvarFabricante(FabricanteRecord fabricanteRecord) {
		this.fabricanteRepository.save(new Fabricante(fabricanteRecord, getCurrentUsername()));
	}

	public void excluirFabricante(FabricanteRecord fabricanteRecord) {
		this.fabricanteRepository.deleteById(fabricanteRecord.id());
	}

	/*--------------------------------< EMPRESA >--------------------------------*/

	public List<Empresa> buscarTodasEmpresas() {
		return this.empresaRepository.findAll();
	}

	public Empresa buscarEmpresaId(Integer idEmpresa) {
		Optional<Empresa> emp = this.empresaRepository.findById(idEmpresa);
		if (emp.isPresent())
			return emp.get();
		return new Empresa();
	}

	public void salvarEmpresa(EmpresaRecord empresaRecord) {
		this.empresaRepository.save(new Empresa(empresaRecord, getCurrentUsername()));
	}

	public void excluirEmpresa(EmpresaRecord empresaRecord) {
		this.empresaRepository.deleteById(empresaRecord.id());
	}

	/*--------------------------------< EQUIPAMENTOS >--------------------------------*/

	public List<Equipamento> buscarTodosEquipamentos() {
		return this.equipamentoRepository.findAll();
	}

	public Equipamento buscarEquipamentoId(Integer numSerie) {
		Optional<Equipamento> equip = this.equipamentoRepository.findById(numSerie);
		if (equip.isPresent())
			return equip.get();
		return new Equipamento();
	}

	public List<Equipamento> buscarEquipamentoPorIdFabricante(Integer id) {
		return this.equipamentoRepository.findByFabricanteId(id);
	}

	public List<Equipamento> buscarEquipamentosPorNumeroSerie(Integer numserie) {
		if (numserie == 0)
			return this.equipamentoRepository.findAll();
		return this.equipamentoRepository.findByNumeroSerie(numserie);
	}

	public void salvarEquipamento(EquipamentoRecord equipamentoRecord) {
		this.equipamentoRepository.save(new Equipamento(equipamentoRecord.numeroSerie(), equipamentoRecord.codigo(),
				new Fabricante(equipamentoRecord), equipamentoRecord.situacao(), getCurrentUsername()));
	}

	public void excluirEquipamento(EquipamentoRecord equipamentoRecord) {
		this.equipamentoRepository.deleteById(equipamentoRecord.numeroSerie());
	}

	/*--------------------------------< APLICAÇÃO >--------------------------------*/

	public List<Aplicacao> buscarTodasAplicacoes() {
		return this.aplicacaoRepository.findAll();
	}

	public List<Aplicacao> buscarAplicacaoPorNumSerieEquipamento(Integer numeroSerie) {
		return this.aplicacaoRepository.findByEquipamentoNumeroSerie(numeroSerie);
	}

	public void salvarAplicacao(AplicacaoRecord aplicacaoRecord) {
		this.aplicacaoRepository.save(new Aplicacao(aplicacaoRecord, getCurrentUsername()));
	}

	public void excluirAplicacao(AplicacaoRecord aplicacaoRecord) {
		this.aplicacaoRepository.deleteById(aplicacaoRecord.tag());
	}

	/*--------------------------------< CALIBRAÇÕES >--------------------------------*/

	public List<CalibracaoEquipamento> buscarTodasCalibracoes() {
		return this.calibracaoEquipamentoRepository.findAll();
	}

	public List<CalibracaoEquipamento> buscarCalibracaoPorNumSerieEquipamento(Integer numeroSerie) {
		return this.calibracaoEquipamentoRepository.findByEquipamentoNumeroSerie(numeroSerie);
	}

	public List<CalibracaoEquipamento> buscarCalibracaoPorIdEmpresa(Integer id) {
		return this.calibracaoEquipamentoRepository.findByEmpresaId(id);
	}

	public void salvarCalibracao(CalibracaoEquipamentoRecord calibracaoEquipamentoRecord) {
		this.calibracaoEquipamentoRepository.save(new CalibracaoEquipamento(calibracaoEquipamentoRecord.id(),
				new Equipamento(calibracaoEquipamentoRecord.numeroSerieEquipamento()),
				new Empresa(calibracaoEquipamentoRecord.idEmpresa()), calibracaoEquipamentoRecord.dataCalibracao(),
				calibracaoEquipamentoRecord.situacao(), getCurrentUsername()));
	}

	public void excluirCalibracao(CalibracaoEquipamentoRecord calibracaoEquipamentoRecord) {
		this.calibracaoEquipamentoRepository.deleteById(calibracaoEquipamentoRecord.id());
	}

	/*--------------------------------< PROPOSTA DE MANUTENÇÃO >--------------------------------*/

	public List<PropostaManutencao> salvarPropostaManutencao(PropostaManutencao pm) {
		pm.setUsuario(new Usuario(getCurrentUsername()));
		this.propostaManutencaoRepository.save(pm);
		return this.buscarTodasPropostasManutencaoId(pm.getManutencaoEquipamento().getId());
	}

	public void salvarManutencao(ManutencaoEquipamentoRecord mer, List<Equipamento> equipamentos) {
		this.manutencaoRepository.save(new ManutencaoEquipamento(mer, equipamentos, getCurrentUsername()));
	}

	public List<Object[]> buscarPropostasPorEquipamentosPorManutencao(Integer idManutencao) {
		return this.propostaManutencaoRepository.buscarPropostasPorEquipamentosPorManutencao(idManutencao);
	}

	public List<ManutencaoEquipamento> buscarTodasManutencoes() {
		return this.manutencaoRepository.findAll();
	}

	public List<PropostaManutencao> buscarPropostaPorNumSerieEquipamento(Integer numeroSerie) {
		return this.propostaManutencaoRepository.findByEquipamentoNumeroSerie(numeroSerie);
	}

	public List<PropostaManutencao> buscarPropostaPorIdEmpresa(Integer id) {
		return this.propostaManutencaoRepository.findByEmpresaId(id);
	}

	public ManutencaoEquipamento buscarManutencaoId(Integer idManutencao) {
		Optional<ManutencaoEquipamento> e = this.manutencaoRepository.findById(idManutencao);
		if (e.isPresent()) {
			return e.get();
		}
		return new ManutencaoEquipamento();
	}

	public List<PropostaManutencao> buscarTodasPropostasManutencaoId(Integer idManutencao) {
		return this.propostaManutencaoRepository.buscarPropostasManutencaoId(idManutencao);
	}

	public String excluirPropostaManutencao(Integer id) {
		this.propostaManutencaoRepository.deleteById(id);
		return "success";
	}

	public void excluirManutencao(ManutencaoEquipamentoRecord mer) {
		this.propostaManutencaoRepository.excluirPorIdManutencao(mer.id());
		this.rcRepository.excluirPorIdManutencao(mer.id());
		this.manutencaoRepository.deleteById(mer.id());
	}

	/*--------------------------------< REQUISIÇÃO DE COMPRA >--------------------------------*/

	public List<RequisicaoCompra> buscarTodasRCs(Integer requisicaoCompra) {
		return this.rcRepository.findAll();
	}

	public String salvarRc(RequisicaoCompraRecord rcRecord) {
		this.rcRepository.save(new RequisicaoCompra(rcRecord, getCurrentUsername()));
		return "success";
	}

	public List<RequisicaoCompra> buscarRcsMemorizadas() {
		return this.rcRepository.findByMemorizacao();
	}

	public List<RequisicaoCompra> buscarTodasRCsPorManutencao(Integer idManutencao) {
		return this.rcRepository.buscarTodasRCsPorManutencao(idManutencao);
	}

	public String excluirRC(RequisicaoCompraRecord rcRecord) {
		this.rcRepository.excluirPorIdManutencao(rcRecord.requisicaoCompra(), rcRecord.idManutencao());
		return "success";
	}

	/*--------------------------------< USUÁRIO >--------------------------------*/

	public List<Usuario> buscarTodosUsuarios() {
		return this.usuarioRepository.findAll();
	}

	public void excluirUsuario(UsuarioRecord usuarioRecord) {
		this.usuarioRepository.deleteById(usuarioRecord.usuario());
	}

	public Optional<Usuario> buscarUsuarioLogin(UsuarioRecord usuarioRecord) {
		return this.usuarioRepository.findById(usuarioRecord.usuario());
	}

	public void salvarUsuario(UsuarioRecord usuarioRecord) {
		this.usuarioRepository.save(new Usuario(usuarioRecord, getCurrentUsername()));
	}

	public void salvarPerfil(UsuarioRecord usuarioRecord) {
		this.usuarioRepository.createPerfil(usuarioRecord.usuario(), usuarioRecord.perfil());
	}

	public void deletarPerfilPorUsuario(UsuarioRecord usuarioRecord) {
		this.usuarioRepository.deletePerfilPorUsuario(usuarioRecord.usuario());
	}

	/*--------------------------------< EQUIPAMENTOS DE MANUTENÇÃO >--------------------------------*/

	public List<EquipamentosManutencao> buscarEquipamentosPorManutencao(Integer idManutencao) {
		return this.equipamentoManutencaoRepository.buscarManutencaoEquipamentoId(idManutencao);
	}

	public List<EquipamentosManutencao> salvarEquipamentoManutencao(EquipamentoManutencaoRecord emRecord) {
		this.equipamentoManutencaoRepository.save(new EquipamentosManutencao(emRecord, getCurrentUsername()));
		return this.buscarEquipamentosPorManutencao(emRecord.idManutencao());
	}

	public void deletarEquipamentosPorManutencao(Integer id) {
		this.equipamentoManutencaoRepository.deleteById(id);
	}

}

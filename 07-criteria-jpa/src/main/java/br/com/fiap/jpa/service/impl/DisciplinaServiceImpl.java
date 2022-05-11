package br.com.fiap.jpa.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import br.com.fiap.jpa.dao.impl.CursoDAOImpl;
import br.com.fiap.jpa.dao.impl.DisciplinaDAOImpl;
import br.com.fiap.jpa.entity.Curso;
import br.com.fiap.jpa.entity.Disciplina;
import br.com.fiap.jpa.service.GenericService;

public class DisciplinaServiceImpl extends GenericService<Disciplina, Long> {

	private static DisciplinaServiceImpl instance = null;
	
	private DisciplinaDAOImpl disciplinaDAO;
	private CursoDAOImpl cursoDAO;
	
	private DisciplinaServiceImpl() {
		disciplinaDAO = DisciplinaDAOImpl.getInstance();
		cursoDAO = CursoDAOImpl.getInstance();
	}
	
	public static DisciplinaServiceImpl getInstance() {
		
		if (instance == null) {
			instance = new DisciplinaServiceImpl();
		}
		
		return instance;
	}
	
	@Override
	public void inserir(Disciplina instance) {
		try {
			Curso curso = instance.getCurso();
			curso = cursoDAO.obterPorId(curso.getId(), getEntityManager());
			instance.setCurso(curso);
			
			disciplinaDAO.salvar(instance, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
	}

	@Override
	public void atualizar(Disciplina instance) {
		try {
			disciplinaDAO.atualizar(instance, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
	}

	@Override
	public void remover(Long id) {

		try {
			disciplinaDAO.remover(id, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
	}

	@Override
	public Disciplina obter(Long id) {
		Disciplina disciplina = null;
		
		try {
			disciplina = disciplinaDAO.obterPorId(id, getEntityManager());
		} catch (Exception e) {
		} finally {
			closeEntityManager();
		}
		
		return disciplina;
	}

	@Override
	public List<Disciplina> listar() {
		List<Disciplina> disciplinas = null;
		
		try {
			disciplinas = disciplinaDAO.listar(getEntityManager());
		} catch (Exception e) {
		} finally {
			closeEntityManager();
		}
		
		return disciplinas;
	}
	
	public List<Disciplina> listarPorCurso(Long idCurso) {
		List<Disciplina> disciplinas = null;
		
		try {
			disciplinas = disciplinaDAO.listarPorCurso(idCurso, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return disciplinas;
	}
	
	public List<Disciplina> listarPorCargaHoraria(Integer cargaHoraria) {
		List<Disciplina> disciplinas = null;
		
		try {
			disciplinas = disciplinaDAO.listarPorCargaHoraria(
					cargaHoraria, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return disciplinas;
	}
	
	public List<Disciplina> pesquisar(String nome, Integer cargaHoraria, 
			LocalDateTime dataCadastro) {
		
		List<Disciplina> disciplinas = null;
		
		try {
			disciplinas = disciplinaDAO.pesquisar(nome, cargaHoraria, 
					dataCadastro, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return disciplinas;
	}
	
	public Long contarDisciplinas() {
		Long qtd = 0L;
		
		try {
			qtd = disciplinaDAO.contarDisciplinas(getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return qtd;
	}
	
	public List<String> listarConcatenado() {
		List<String> disciplinas = null;
		
		try {
			disciplinas = disciplinaDAO.listarConcatenado(getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return disciplinas;
	}
	
	public List<Disciplina> buscarPorNomeIgnoreCase(String nome) {
		List<Disciplina> disciplinas = null;
		
		try {
			disciplinas = disciplinaDAO.buscarPorNomeIgnoreCase(nome, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return disciplinas;
	}
	
	public List<Disciplina> listarPorCursoComCriteria(Long idCurso) {
		List<Disciplina> disciplinas = null;
		
		try {
			disciplinas = disciplinaDAO.listarPorCursoComCriteria(idCurso, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return disciplinas;
	}

}

package br.com.fiap.jpa.service.impl;

import java.util.List;

import br.com.fiap.jpa.dao.impl.MatriculaDAOImpl;
import br.com.fiap.jpa.dto.AlunoCursoDTO;
import br.com.fiap.jpa.dto.TotalAlunosCursoDTO;
import br.com.fiap.jpa.entity.Matricula;
import br.com.fiap.jpa.service.GenericService;

public class MatriculaServiceImpl extends GenericService<Matricula, Long> {

private static MatriculaServiceImpl instance = null;
	
	private MatriculaDAOImpl matriculaDAO;
	
	private MatriculaServiceImpl() {
		matriculaDAO = MatriculaDAOImpl.getInstance();
	}
	
	public static MatriculaServiceImpl getInstance() {
		
		if (instance == null) {
			instance = new MatriculaServiceImpl();
		}
		
		return instance;
	}
	
	@Override
	public void inserir(Matricula instance) {
		try {
			matriculaDAO.salvar(instance, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
	}

	@Override
	public void atualizar(Matricula instance) {
		try {
			matriculaDAO.atualizar(instance, getEntityManager());
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
			matriculaDAO.remover(id, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
	}

	@Override
	public Matricula obter(Long id) {
		Matricula matricula = null;
		
		try {
			matricula = matriculaDAO.obterPorId(id, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
		
		return matricula;
	}

	@Override
	public List<Matricula> listar() {
		List<Matricula> matriculas = null;
		
		try {
			matriculas = matriculaDAO.listar(getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
		
		return matriculas;
	}
	
	public List<AlunoCursoDTO> listarAlunosCursos() {
		List<AlunoCursoDTO> alunosCursos = null;
		
		try {
			alunosCursos = matriculaDAO.listarAlunosCursos(getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		 
		return alunosCursos;
	}
	
	public List<TotalAlunosCursoDTO> contarPorCurso() {
		List<TotalAlunosCursoDTO> cursos = null;
		
		try {
			cursos = matriculaDAO.contarAlunosPorCurso(getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return cursos;
	}
	
	public List<Matricula> listarPorCurso(Long idCurso) {
		List<Matricula> matriculas = null;
		
		try {
			matriculas = matriculaDAO.listarPorCurso(idCurso, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return matriculas;
	}

	public List<AlunoCursoDTO> listarAlunosCursoComCriteria() {
		List<AlunoCursoDTO> alunos = null;
		
		try {
			alunos = matriculaDAO.listarAlunosCursoComCriteria(getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return alunos;
	}
}

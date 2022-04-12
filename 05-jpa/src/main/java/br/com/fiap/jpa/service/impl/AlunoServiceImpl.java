package br.com.fiap.jpa.service.impl;

import java.util.List;

import br.com.fiap.jpa.dao.impl.AlunoDAOImpl;
import br.com.fiap.jpa.dao.impl.CursoDAOImpl;
import br.com.fiap.jpa.dao.impl.EnderecoDAOImpl;
import br.com.fiap.jpa.entity.Aluno;
import br.com.fiap.jpa.entity.Endereco;
import br.com.fiap.jpa.service.GenericService;

public class AlunoServiceImpl extends GenericService<Aluno, Long> {

	private static AlunoServiceImpl instance = null;
	
	private AlunoDAOImpl alunoDAO;
	private EnderecoDAOImpl enderecoDAO;
	private CursoDAOImpl cursoDAO;
	
	private AlunoServiceImpl() {
		alunoDAO = AlunoDAOImpl.getInstance();
		enderecoDAO = EnderecoDAOImpl.getInstance();
		cursoDAO = CursoDAOImpl.getInstance();
	}
	
	public static AlunoServiceImpl getInstance() {
		
		if (instance == null) {
			instance = new AlunoServiceImpl();
		}
		
		return instance;
	}
	
	@Override
	public void inserir(Aluno aluno) {
		try {
			alunoDAO.salvar(aluno, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
	}
	
	/**
	 * Método para associar um aluno a um curso no relacionamento ManyToMany simples
	 * 
	 */
	/*
	public void matricular(Long idAluno, Long idCurso) {
		try {
			Aluno aluno = obter(idAluno);
			Curso curso = cursoDAO.obterPorId(idCurso, getEntityManager());
			
			List<Curso> cursos = aluno.getCursos();
			Boolean matriculado = false;
			
			if (cursos == null) {
				cursos = new ArrayList<Curso>();
			} else {
				for (Curso cursoDB : cursos) {
					if (cursoDB.getId().equals(idCurso)) {
						matriculado = true;
						break;
					}
				}
			}
			
			if (!matriculado) {
				cursos.add(curso);
				aluno.setCursos(cursos);
				alunoDAO.atualizar(aluno, getEntityManager());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
	}
	*/
	
	public void inserirComEndereco(Aluno aluno, Endereco endereco) {
		try {
			alunoDAO.salvar(aluno, getEntityManager());
			
			endereco.setAluno(aluno);
			enderecoDAO.salvar(endereco, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
	}
	
	@Override
	public void atualizar(Aluno aluno) {
		try {
			alunoDAO.atualizar(aluno, getEntityManager());
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
		
	}

	@Override
	public void remover(Long id) {
		try {
			alunoDAO.remover(id, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
		
	}

	@Override
	public Aluno obter(Long id) {
		Aluno aluno = null;
		
		try {
			aluno = alunoDAO.obterPorId(id, getEntityManager());
		} catch (Exception e) {
		} finally {
			closeEntityManager();
		}
		
		return aluno;
	}

	@Override
	public List<Aluno> listar() {
		List<Aluno> alunos = null;
		
		try {
			alunos = alunoDAO.listar(getEntityManager());
		} catch (Exception e) {
		} finally {
			closeEntityManager();
		}
		
		return alunos;
	}
}

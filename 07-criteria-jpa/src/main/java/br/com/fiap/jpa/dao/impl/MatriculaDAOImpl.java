package br.com.fiap.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.jpa.dto.AlunoCursoDTO;
import br.com.fiap.jpa.dto.TotalAlunosCursoDTO;
import br.com.fiap.jpa.entity.Matricula;

public class MatriculaDAOImpl extends HibernateGenericDAO<Matricula, Long> {

	private static MatriculaDAOImpl instance = null;
	
	public MatriculaDAOImpl() {
		super(Matricula.class);
	}
	
	public static MatriculaDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new MatriculaDAOImpl();
		}
		
		return instance;
	}
	
	public List<AlunoCursoDTO> listarAlunosCursos(EntityManager entityManager) {
		String jpql = "SELECT new br.com.fiap.jpa.dto.AlunoCursoDTO(m.aluno.matricula, m.aluno.nome, m.curso.nome) FROM Matricula m";
		
		TypedQuery<AlunoCursoDTO> consulta = entityManager.createQuery(jpql, AlunoCursoDTO.class);
		
		return consulta.getResultList();
	}
	
	public List<TotalAlunosCursoDTO> contarAlunosPorCurso(EntityManager entityManager) {
		String jpql = "SELECT new br.com.fiap.jpa.dto.TotalAlunosCursoDTO(m.curso.nome, count(m)) FROM Matricula m GROUP BY m.curso.nome";
		
		TypedQuery<TotalAlunosCursoDTO> consulta = entityManager.createQuery(jpql, TotalAlunosCursoDTO.class);
		
		return consulta.getResultList();
	}

}

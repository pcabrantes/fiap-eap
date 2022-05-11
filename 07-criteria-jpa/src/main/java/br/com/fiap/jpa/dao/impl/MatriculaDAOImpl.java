package br.com.fiap.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.fiap.jpa.dto.AlunoCursoDTO;
import br.com.fiap.jpa.dto.TotalAlunosCursoDTO;
import br.com.fiap.jpa.entity.Aluno;
import br.com.fiap.jpa.entity.Curso;
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
	
	public List<Matricula> listarPorCurso(Long idCurso, EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Matricula> criteriaQuery = builder.createQuery(Matricula.class);
		
		Root<Matricula> matricula = criteriaQuery.from(Matricula.class);
		Join<Matricula, Curso> curso = matricula.join("curso");
		
		Predicate predicate = builder.equal(curso.get("id"), idCurso);
		
		criteriaQuery.select(matricula);
		criteriaQuery.where(predicate);
		
		TypedQuery<Matricula> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}
	
	public List<AlunoCursoDTO> listarAlunosCursoComCriteria(EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AlunoCursoDTO> criteriaQuery = builder.createQuery(AlunoCursoDTO.class);
		
		Root<Matricula> matricula = criteriaQuery.from(Matricula.class);
		Join<Matricula, Aluno> aluno = matricula.join("aluno");
		Join<Matricula, Curso> curso = matricula.join("curso");
		
		criteriaQuery.select(builder.construct(AlunoCursoDTO.class, 
				aluno.get("matricula"), aluno.get("nome"), curso.get("nome")));
		
		TypedQuery<AlunoCursoDTO> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}
	

}

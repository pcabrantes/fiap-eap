package br.com.fiap.jpa.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.fiap.jpa.entity.Curso;
import br.com.fiap.jpa.entity.Disciplina;

public class DisciplinaDAOImpl extends HibernateGenericDAO<Disciplina, Long> {

	private static DisciplinaDAOImpl instance;
	
	private DisciplinaDAOImpl() {
		super(Disciplina.class);
	}
	
	public static DisciplinaDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new DisciplinaDAOImpl();
		}
		
		return instance;
	}
	
	public List<Disciplina> listarPorCurso(Long idCurso, EntityManager entityManager) {
		String jpql = "SELECT d FROM Disciplina d WHERE d.curso.id = :idCurso";
		TypedQuery<Disciplina> consulta = entityManager.createQuery(jpql, Disciplina.class);
		consulta.setParameter("idCurso", idCurso);
		
		return consulta.getResultList();
	}
	
	@Override
	public List<Disciplina> listar(EntityManager entityManager) {
		String jpql = "SELECT d FROM Disciplina d INNER JOIN FETCH d.curso";
		
		TypedQuery<Disciplina> consulta = entityManager.createQuery(jpql, Disciplina.class);
		
		return consulta.getResultList();
	}
	
	public List<Disciplina> listarPorCargaHoraria(
			Integer cargaHoraria, EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Disciplina> criteriaQuery = builder.createQuery(Disciplina.class);
		
		Root<Disciplina> disciplina = criteriaQuery.from(Disciplina.class);
		Predicate predicate = builder.equal(disciplina.get("cargaHoraria"), cargaHoraria);
		
		criteriaQuery.select(disciplina);
		criteriaQuery.where(predicate);
		
		TypedQuery<Disciplina> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}
	
	public List<Disciplina> pesquisar(
			String nome, Integer cargaHoraria, LocalDateTime dataCadastro, 
			EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Disciplina> criteriaQuery = builder.createQuery(Disciplina.class);
		
		Root<Disciplina> disciplina = criteriaQuery.from(Disciplina.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (nome != null) {
			Predicate predicate = builder.equal(disciplina.get("nome"), nome);
			predicates.add(predicate);
		}
		
		if (cargaHoraria != null) {
			Predicate predicate = builder.equal(disciplina.get("cargaHoraria"), cargaHoraria);
			predicates.add(predicate);
		}
		
		if (dataCadastro != null) {
			Predicate predicate = builder.greaterThanOrEqualTo(
					disciplina.get("dataCadastro"), dataCadastro);
			predicates.add(predicate);
		}
		
		criteriaQuery.select(disciplina);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Disciplina> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}
	
	public Long contarDisciplinas(EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		
		Root<Disciplina> disciplina = criteriaQuery.from(Disciplina.class);
		
		criteriaQuery.select(builder.count(disciplina));
		
		TypedQuery<Long> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getSingleResult();
	}
	
	public List<String> listarConcatenado(EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
		
		Root<Disciplina> disciplina = criteriaQuery.from(Disciplina.class);
		
		Expression<String> expressao = builder.concat(
			builder.concat(disciplina.get("nome"), " - "), 
			builder.concat(disciplina.get("cargaHoraria"), " Horas"));
		
		Order order = builder.asc(disciplina.get("nome"));
		
		criteriaQuery.select(expressao);
		criteriaQuery.orderBy(order);
		
		TypedQuery<String> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}
	
	public List<Disciplina> buscarPorNomeIgnoreCase(String nome, EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Disciplina> criteriaQuery = builder.createQuery(Disciplina.class);
		
		Root<Disciplina> disciplina = criteriaQuery.from(Disciplina.class);
		
		Predicate predicate = builder.like(
				builder.upper(disciplina.get("nome")), "%" + nome.toUpperCase() + "%");
		
		Order orderNome = builder.desc(disciplina.get("nome"));
		Order orderCargaHoraria = builder.asc(disciplina.get("cargaHoraria"));
		
		criteriaQuery.select(disciplina);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(orderNome, orderCargaHoraria);
		
		TypedQuery<Disciplina> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}
	
	public List<Disciplina> listarPorCursoComCriteria(Long idCurso, EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Disciplina> criteriaQuery = builder.createQuery(Disciplina.class);
		
		Root<Disciplina> disciplina = criteriaQuery.from(Disciplina.class);
		Join<Disciplina, Curso> curso = disciplina.join("curso");
		
		Predicate predicate = builder.equal(curso.get("id"), idCurso);
		
		criteriaQuery.select(disciplina);
		criteriaQuery.where(predicate);
		
		TypedQuery<Disciplina> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}

}

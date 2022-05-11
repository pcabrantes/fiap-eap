package br.com.fiap.jpa.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.fiap.jpa.entity.Curso;
import br.com.fiap.jpa.entity.Disciplina;

public class CursoDAOImpl extends HibernateGenericDAO<Curso, Long> {

	private static CursoDAOImpl instance = null;
	
	private CursoDAOImpl() {
		super(Curso.class);
	}
	
	public static CursoDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new CursoDAOImpl();
		}
		
		return instance;
	}
	
	public List<Curso> pesquisar(String nome, LocalDateTime dataCadastro, 
			EntityManager entityManager) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Curso> criteriaQuery = builder.createQuery(Curso.class);
		
		Root<Curso> curso = criteriaQuery.from(Curso.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (nome != null) {
			Predicate predicate = builder.like(curso.get("nome"), "%" + nome + "%");
			predicates.add(predicate);
		}
		
		if (dataCadastro != null) {
			Predicate predicate = builder.lessThanOrEqualTo(
					curso.get("dataCadastro"), dataCadastro);
			predicates.add(predicate);
		}
		
		criteriaQuery.select(curso);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Curso> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}
	
	public List<Curso> buscarPorNomeIgnoreCase(String nome, EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Curso> criteriaQuery = builder.createQuery(Curso.class);
		
		Root<Curso> curso = criteriaQuery.from(Curso.class);
		
		Predicate predicate = builder.like(
				builder.upper(curso.get("nome")), "%" + nome.toUpperCase() + "%");
		
		Order order = builder.asc(curso.get("nome"));
		
		criteriaQuery.select(curso);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(order);
		
		TypedQuery<Curso> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}

}

package br.com.fiap.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.fiap.jpa.entity.Aluno;

public class AlunoDAOImpl extends HibernateGenericDAO<Aluno, Long> {

	private static AlunoDAOImpl instance = null;
	
	public static AlunoDAOImpl getInstance() {
		if (instance == null) {
			instance = new AlunoDAOImpl();
		}
		
		return instance;
	}
	
	private AlunoDAOImpl() {
		super(Aluno.class);
	}
	
	public List<Aluno> listarPorUF(String uf, EntityManager entityManager) {
		TypedQuery<Aluno> consulta = entityManager.createNamedQuery("Aluno.listarPorUF", Aluno.class);
		consulta.setParameter("uf", uf);
		consulta.setParameter("ativo", true);
		
		return consulta.getResultList();
	}
	
	public List<Aluno> listarPorCurso(Long idCurso, EntityManager entityManager) {
		TypedQuery<Aluno> consulta = entityManager.createNamedQuery("Aluno.listarPorCurso", Aluno.class);
		consulta.setParameter("idCurso", idCurso);
		
		return consulta.getResultList();
	}
	
	public List<Aluno> listarDadosBasicos(EntityManager entityManager) {
		TypedQuery<Aluno> consulta = entityManager.createNamedQuery("Aluno.listarDadosBasicos", Aluno.class);
		
		return consulta.getResultList();
	}
	
	public List<Aluno> listarPaginado(int primeiroResultado, int qtdMax, EntityManager entityManager) {
		TypedQuery<Aluno> consulta = entityManager.createNamedQuery("Aluno.listarPaginado", Aluno.class);
		consulta.setFirstResult(primeiroResultado);
		consulta.setMaxResults(qtdMax);
		
		return consulta.getResultList();
	}
	
	@Override
	public List<Aluno> listar(EntityManager entityManager) {
		TypedQuery<Aluno> consulta = entityManager.createNamedQuery("Aluno.listar", Aluno.class);
		
		return consulta.getResultList();
	}
	
	public List<Aluno> listarComCriteria(EntityManager entityManager) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Aluno> criteriaQuery = builder.createQuery(Aluno.class);
		
		Root<Aluno> aluno = criteriaQuery.from(Aluno.class);
		criteriaQuery.select(aluno);
		
		TypedQuery<Aluno> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}

}

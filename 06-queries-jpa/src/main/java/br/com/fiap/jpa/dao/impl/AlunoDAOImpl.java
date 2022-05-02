package br.com.fiap.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		String jpql = "SELECT a FROM Aluno a WHERE a.endereco.uf = :uf AND a.ativo = :ativo";
		
		TypedQuery<Aluno> consulta = entityManager.createQuery(jpql, Aluno.class);
		consulta.setParameter("uf", uf);
		consulta.setParameter("ativo", true);
		
		return consulta.getResultList();
	}
	
	public List<Aluno> listarPorCurso(Long idCurso, EntityManager entityManager) {
		String jpql = "SELECT m.aluno FROM Matricula m WHERE m.curso.id = :idCurso";
		
		TypedQuery<Aluno> consulta = entityManager.createQuery(jpql, Aluno.class);
		consulta.setParameter("idCurso", idCurso);
		
		return consulta.getResultList();
	}
	
	public List<Aluno> listarDadosBasicos(EntityManager entityManager) {
		String jpql = "SELECT new Aluno(a.nome, a.cpf, a.dataNascimento) FROM Aluno a";
		
		TypedQuery<Aluno> consulta = entityManager.createQuery(jpql, Aluno.class);
		
		return consulta.getResultList();
	}
	
	public List<Aluno> listarPaginado(int primeiroResultado, int qtdMax, EntityManager entityManager) {
		String jpql = "SELECT new Aluno(a.nome, a.cpf, a.dataNascimento) FROM Aluno a";
		
		TypedQuery<Aluno> consulta = entityManager.createQuery(jpql, Aluno.class);
		consulta.setFirstResult(primeiroResultado);
		consulta.setMaxResults(qtdMax);
		
		return consulta.getResultList();
	}

}

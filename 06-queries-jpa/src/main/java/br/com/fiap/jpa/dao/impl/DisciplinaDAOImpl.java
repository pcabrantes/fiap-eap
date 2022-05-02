package br.com.fiap.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

}

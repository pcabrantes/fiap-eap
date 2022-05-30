package br.com.fiap.jpa.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.fiap.jpa.entity.Participante;

public class ParticipanteDAOImpl extends HibernateGenericDAO<Participante, Long>{

	private static ParticipanteDAOImpl instance = null;
	
	private ParticipanteDAOImpl() {
		super(Participante.class);
	}
	
	public static ParticipanteDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new ParticipanteDAOImpl();
		}
		
		return instance;
	}
	
	public List<Participante> pesquisar(String nome, String cpf, LocalDate dataCadastro, 
			EntityManager entityManager) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Participante> criteriaQuery = builder.createQuery(Participante.class);
		
		Root<Participante> participante = criteriaQuery.from(Participante.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (nome != null) {
			Predicate predicate = builder.like(
					builder.upper(participante.get("nome")), "%" + nome.toUpperCase() + "%");
			predicates.add(predicate);
		}
		
		if (cpf != null) {
			Predicate predicate = builder.equal(participante.get("cpf"), cpf);
			predicates.add(predicate);
		}
		
		if (dataCadastro != null) {
			Predicate predicate = builder.greaterThanOrEqualTo(
					participante.get("dataCadastro"), dataCadastro);
			predicates.add(predicate);
		}
		
		criteriaQuery.select(participante);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Participante> consulta = entityManager.createQuery(criteriaQuery);
		
		return consulta.getResultList();
	}
	
}

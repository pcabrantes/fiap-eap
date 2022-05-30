package br.com.fiap.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.jpa.entity.MovimentacaoEntrada;

public class MovimentacaoEntradaDAOImpl extends HibernateGenericDAO<MovimentacaoEntrada, Long> {

	private static MovimentacaoEntradaDAOImpl instance = null;
	
	private MovimentacaoEntradaDAOImpl() {
		super(MovimentacaoEntrada.class);
	}
	
	public static MovimentacaoEntradaDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new MovimentacaoEntradaDAOImpl();
		}
		
		return instance;
	}
	
	public List<MovimentacaoEntrada> listarPorTipo(String tipo, EntityManager entityManager) {
		String jpql = "SELECT m FROM MovimentacaoEntrada m WHERE m.tipoMovimentacao = :tipo";
		
		TypedQuery<MovimentacaoEntrada> consulta = entityManager.createQuery(jpql, MovimentacaoEntrada.class);
		consulta.setParameter("tipo", tipo);
		
		return consulta.getResultList();
	}
}

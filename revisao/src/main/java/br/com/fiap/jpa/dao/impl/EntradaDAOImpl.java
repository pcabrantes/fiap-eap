package br.com.fiap.jpa.dao.impl;

import br.com.fiap.jpa.entity.Entrada;

public class EntradaDAOImpl extends HibernateGenericDAO<Entrada, Long> {
	
	private static EntradaDAOImpl instance = null;
	
	private EntradaDAOImpl() {
		super(Entrada.class);
	}
	
	public static EntradaDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new EntradaDAOImpl();
		}
		
		return instance;
	}

}

package br.com.fiap.jpa.dao.impl;

import br.com.fiap.jpa.entity.Disciplina;

public class DisciplinaDAOImpl extends HibernateGenericDAO<Disciplina, Long> {

	private DisciplinaDAOImpl instance;
	
	private DisciplinaDAOImpl() {
		super(Disciplina.class);
	}
	
	public DisciplinaDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new DisciplinaDAOImpl();
		}
		
		return instance;
	}

}

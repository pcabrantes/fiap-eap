package br.com.fiap.jpa.dao.impl;

import br.com.fiap.jpa.entity.Curso;

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

}

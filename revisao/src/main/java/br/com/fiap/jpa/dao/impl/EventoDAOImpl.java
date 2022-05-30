package br.com.fiap.jpa.dao.impl;

import br.com.fiap.jpa.entity.Evento;

public class EventoDAOImpl extends HibernateGenericDAO<Evento, Long> {

	private static EventoDAOImpl instance = null;
	
	private EventoDAOImpl() {
		super(Evento.class);
	}
	
	public static EventoDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new EventoDAOImpl();
		}
		
		return instance; 
	}
}

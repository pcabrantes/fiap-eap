package br.com.fiap.jpa.service.impl;

import java.util.List;

import br.com.fiap.jpa.dao.impl.EventoDAOImpl;
import br.com.fiap.jpa.entity.Evento;
import br.com.fiap.jpa.service.GenericService;

public class EventoServiceImpl extends GenericService<Evento, Long> {

	private static EventoServiceImpl instance = null;
	
	private EventoDAOImpl eventoDAO;
	
	private EventoServiceImpl() {
		this.eventoDAO = EventoDAOImpl.getInstance();
	}
	
	public static EventoServiceImpl getInstance() {
		
		if (instance == null) {
			instance = new EventoServiceImpl();
		}
		
		return instance;
	}
	
	@Override
	public void inserir(Evento instance) {
		try {
			eventoDAO.salvar(instance, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
	}

	@Override
	public void atualizar(Evento instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Evento obter(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}

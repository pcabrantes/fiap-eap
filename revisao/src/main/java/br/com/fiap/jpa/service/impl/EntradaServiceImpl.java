package br.com.fiap.jpa.service.impl;

import java.util.List;

import br.com.fiap.jpa.dao.impl.EntradaDAOImpl;
import br.com.fiap.jpa.entity.Entrada;
import br.com.fiap.jpa.service.GenericService;

public class EntradaServiceImpl extends GenericService<Entrada, Long>{

	private static EntradaServiceImpl instance = null;
	
	private EntradaDAOImpl entradaDAO;
	
	private EntradaServiceImpl() {
		this.entradaDAO = EntradaDAOImpl.getInstance();
	}
	
	public static EntradaServiceImpl getInstance() {
		
		if (instance == null) {
			instance = new EntradaServiceImpl();
		}
		
		return instance;
	}

	@Override
	public void inserir(Entrada instance) {
		try {
			entradaDAO.salvar(instance, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
	}

	@Override
	public void atualizar(Entrada instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entrada obter(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entrada> listar() {
		// TODO Auto-generated method stub
		return null;
	}
}

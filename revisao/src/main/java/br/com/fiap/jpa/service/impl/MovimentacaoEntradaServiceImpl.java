package br.com.fiap.jpa.service.impl;

import java.util.List;

import br.com.fiap.jpa.dao.impl.MovimentacaoEntradaDAOImpl;
import br.com.fiap.jpa.entity.MovimentacaoEntrada;
import br.com.fiap.jpa.service.GenericService;

public class MovimentacaoEntradaServiceImpl extends GenericService<MovimentacaoEntrada, Long> {

	private static MovimentacaoEntradaServiceImpl instance = null;
	
	private MovimentacaoEntradaDAOImpl movimentacaoEntradaDAO;
	
	private MovimentacaoEntradaServiceImpl() {
		this.movimentacaoEntradaDAO = MovimentacaoEntradaDAOImpl.getInstance();
	}
	
	public static MovimentacaoEntradaServiceImpl getInstance() {
		
		if (instance == null) {
			instance = new MovimentacaoEntradaServiceImpl();
		}
		
		return instance;
	}
	
	@Override
	public void inserir(MovimentacaoEntrada instance) {
		try {
			movimentacaoEntradaDAO.salvar(instance, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
	}

	@Override
	public void atualizar(MovimentacaoEntrada instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MovimentacaoEntrada obter(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovimentacaoEntrada> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<MovimentacaoEntrada> listarTipo(String tipo) {
		List<MovimentacaoEntrada> movimentacoes = null;
		
		try {
			movimentacoes = movimentacaoEntradaDAO.listarPorTipo(tipo, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return movimentacoes;
	}

}

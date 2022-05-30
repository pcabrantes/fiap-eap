package br.com.fiap.jpa.service.impl;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.jpa.dao.impl.ParticipanteDAOImpl;
import br.com.fiap.jpa.entity.Participante;
import br.com.fiap.jpa.service.GenericService;

public class ParticipanteServiceImpl extends GenericService<Participante, Long> {
	
	private static ParticipanteServiceImpl instance = null;
	
	private ParticipanteDAOImpl participanteDAO;
	
	private ParticipanteServiceImpl() {
		this.participanteDAO = ParticipanteDAOImpl.getInstance();
	}
	
	public static ParticipanteServiceImpl getInstance() {
		
		if (instance == null) {
			instance = new ParticipanteServiceImpl();
		}
		
		return instance;
	}

	@Override
	public void inserir(Participante instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Participante instance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Participante obter(Long id) {
		Participante participante = null;
		
		try {
			participante = participanteDAO.obterPorId(id, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return participante;
	}

	@Override
	public List<Participante> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Participante> pesquisar(String nome, String cpf, LocalDate dataCadastro) {
		List<Participante> participantes = null;
		
		try {
			participantes = participanteDAO.pesquisar(nome, cpf, dataCadastro, getEntityManager());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		
		return participantes;
	}

}

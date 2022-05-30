package br.com.fiap.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.fiap.jpa.entity.Entrada;
import br.com.fiap.jpa.entity.Evento;
import br.com.fiap.jpa.entity.MovimentacaoEntrada;
import br.com.fiap.jpa.entity.Participante;
import br.com.fiap.jpa.service.impl.EntradaServiceImpl;
import br.com.fiap.jpa.service.impl.EventoServiceImpl;
import br.com.fiap.jpa.service.impl.MovimentacaoEntradaServiceImpl;
import br.com.fiap.jpa.service.impl.ParticipanteServiceImpl;

public class App {

	public static void main(String[] args) {
		
		EventoServiceImpl eventoService = EventoServiceImpl.getInstance();
		EntradaServiceImpl entradaService = EntradaServiceImpl.getInstance();
		ParticipanteServiceImpl participanteService = ParticipanteServiceImpl.getInstance();
		MovimentacaoEntradaServiceImpl movimentacaoEntradaService 
				= MovimentacaoEntradaServiceImpl.getInstance();
		
		/**
		 * Cadastrar um novo Evento
		 */
		Evento evento = new Evento("Congresso de TI", LocalDate.of(2022, 1, 1));
		
		eventoService.inserir(evento);

		/**
		 * Cadastrar 2 entradas, uma com o nome Entrada Norte e outra com o nome 
		 * Entrada Sul, associando elas ao evento cadastrado
		 */
		
		Entrada entrada1 = new Entrada(1, "Entrada Norte", evento);
		Entrada entrada2 = new Entrada(2, "Entrada Sul", evento);
		
		entradaService.inserir(entrada1);
		entradaService.inserir(entrada2);
		
		/**
		 * Associada a Entrada Norte, cadastre as movimentacoes com os seguintes dados:
		 * - Participante com id 1, Tipo E, Data da Movimentacao 2022-01-01 11:15:00 
		 * - Participante com id 2, Tipo E, Data da Movimentacao 2022-01-01 14:27:00 
		 * - Participante com id 1, Tipo S, Data da Movimentacao 2022-01-01 15:52:00
		 * - Participante com id 2, Tipo S, Data da Movimentacao 2022-01-01 16:31:00
		 */
		
		Participante participante1 = participanteService.obter(1L);
		Participante participante2 = participanteService.obter(2L);
		
		MovimentacaoEntrada movimentacao1 = new MovimentacaoEntrada("E", 
				LocalDateTime.of(2022, 1, 1, 11, 15, 0), entrada1, participante1);
		MovimentacaoEntrada movimentacao2 = new MovimentacaoEntrada("E", 
				LocalDateTime.of(2022, 1, 1, 14, 27, 0), entrada1, participante2);
		MovimentacaoEntrada movimentacao3 = new MovimentacaoEntrada("S", 
				LocalDateTime.of(2022, 1, 1, 15, 52, 0), entrada1, participante1);
		MovimentacaoEntrada movimentacao4 = new MovimentacaoEntrada("S",
				LocalDateTime.of(2022, 1, 1, 16, 31, 0), entrada1, participante2);
		
		movimentacaoEntradaService.inserir(movimentacao1);
		movimentacaoEntradaService.inserir(movimentacao2);
		movimentacaoEntradaService.inserir(movimentacao3);
		movimentacaoEntradaService.inserir(movimentacao4);
		
		/**
		 * Liste todas as movimentacoes com base no tipo da movimentacao
		 */
		movimentacaoEntradaService.listarTipo("E").forEach(System.out::println);
		
		/**
		 * Crie uma consulta que sera montada dinamicamente para pesquisar os participantes cadastrados com 
		 * base nos seguintes filtros: nome, cpf e data de cadastro
		 */
		
		participanteService.pesquisar("j", null, null).forEach(System.out::println);
		participanteService.pesquisar("j", "111.111.111-11", null).forEach(System.out::println);
	}
}

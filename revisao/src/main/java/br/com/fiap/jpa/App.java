package br.com.fiap.jpa;

import java.time.LocalDate;

import br.com.fiap.jpa.entity.Entrada;
import br.com.fiap.jpa.entity.Evento;
import br.com.fiap.jpa.service.impl.EntradaServiceImpl;
import br.com.fiap.jpa.service.impl.EventoServiceImpl;

public class App {

	public static void main(String[] args) {
		
		EventoServiceImpl eventoService = EventoServiceImpl.getInstance();
		EntradaServiceImpl entradaService = EntradaServiceImpl.getInstance();
		
		Evento evento = new Evento("Congresso de TI", LocalDate.of(2022, 1, 1));
		
		eventoService.inserir(evento);
		
		Entrada entrada1 = new Entrada(1, "Entrada Norte", evento);
		Entrada entrada2 = new Entrada(2, "Entrada Sul", evento);
		
		entradaService.inserir(entrada1);
		entradaService.inserir(entrada2);
	}
}

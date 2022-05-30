package br.com.fiap.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_gce_entrada")
@SequenceGenerator(name = "entrada", sequenceName = "sq_t_gce_entrada", allocationSize = 1)
public class Entrada implements Serializable {
	
	private static final long serialVersionUID = -4156730017576618081L;
	
	public Entrada() {
		
	}
	
	public Entrada(int numero, String nome, Evento evento) {
		this.numero = numero;
		this.nome = nome;
		this.evento = evento;
	}

	@Id
	@Column(name = "id_entrada")
	@GeneratedValue(generator = "entrada", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "nr_entrada", nullable = false)
	private int numero;
	
	@Column(name = "nm_entrada", length = 20, nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento evento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	@Override
	public String toString() {
		return "\nNumero: " + this.getNumero()
			+ "\nNome: " + this.getNome()
			+ "\nEvento: " + this.getEvento().getDescricao();
	}

}

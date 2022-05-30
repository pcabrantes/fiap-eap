package br.com.fiap.jpa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_gce_evento")
@SequenceGenerator(name = "evento", sequenceName = "SQ_T_GCE_EVENTO", allocationSize = 1)
public class Evento implements Serializable {
	
	private static final long serialVersionUID = 496363751778298271L;
	
	public Evento() {
		
	}
	
	public Evento(String descricao, LocalDate dataEvento) {
		this.descricao = descricao;
		this.dataEvento = dataEvento;
	}

	@Id
	@Column(name = "id_evento")
	@GeneratedValue(generator = "evento", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "ds_evento", length = 20, nullable = false)
	private String descricao;
	
	@Column(name = "dt_evento", nullable = false)
	private LocalDate dataEvento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}
	
	@Override
	public String toString() {
		return "\nEvento: " + this.getDescricao()
			+ "\nData: " + this.getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}

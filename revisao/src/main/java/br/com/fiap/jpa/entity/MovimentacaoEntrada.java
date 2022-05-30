package br.com.fiap.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
@Table(name = "t_gce_movimentacao_entrada")
@SequenceGenerator(name = "movimentacao", sequenceName = "sq_t_gce_movimentacao_entrada", allocationSize = 1)
public class MovimentacaoEntrada implements Serializable {
	
	private static final long serialVersionUID = -1892917407805500091L;

	public MovimentacaoEntrada() {
		
	}
	
	public MovimentacaoEntrada(String tipoMovimentacao, LocalDateTime dataMovimentacao, 
			Entrada entrada, Participante participante) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.dataMovimentacao = dataMovimentacao;
		this.entrada = entrada;
		this.participante = participante;
	}
	
	@Id
	@Column(name = "id_movimentacao")
	@GeneratedValue(generator = "movimentacao", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "tp_movimentacao", length = 1, nullable = false)
	private String tipoMovimentacao;
	
	@Column(name = "dt_movimentacao", nullable = false)
	private LocalDateTime dataMovimentacao;
	
	@ManyToOne
	@JoinColumn(name = "id_entrada")
	private Entrada entrada;
	
	@ManyToOne
	@JoinColumn(name = "id_participante")
	private Participante participante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	
	@Override
	public String toString() {
		return "\nTipo: " + this.getTipoMovimentacao()
			+ "\nData: " + this.getDataMovimentacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
			+ "\nEntrada: " + this.getEntrada().getNome()
			+ "\nParticipante: " + this.getParticipante().getNome();
	}

}

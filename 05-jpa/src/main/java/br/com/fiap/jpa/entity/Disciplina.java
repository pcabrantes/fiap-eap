package br.com.fiap.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_disciplina")
@SequenceGenerator(name = "disciplina", sequenceName = "SQ_TB_DISCIPLINA", allocationSize = 1)
public class Disciplina implements Serializable{

	
	private static final long serialVersionUID = 613485805604306620L;
	
	public Disciplina() {
		this.ativo = true;
		this.dataCadastro = LocalDateTime.now();
		this.dataAtualizacao = LocalDateTime.now();
	}
	
	public Disciplina(String nome, Integer cargaHoraria) {
		this();
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disciplina")
	private Long id;
	
	@Column(name = "ds_nome", length = 20, nullable = false)
	private String nome;
	
	@Column(name = "nr_carga_horaria")
	private Integer cargaHoraria;
	
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name = "dt_atualizacao")
	private LocalDateTime dataAtualizacao;

}

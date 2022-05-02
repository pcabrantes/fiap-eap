package br.com.fiap.jpa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_matricula")
@SequenceGenerator(name = "matricula", sequenceName = "SQ_TB_MATRICULA", allocationSize = 1)
public class Matricula implements Serializable {

	private static final long serialVersionUID = 4073510199087371083L;
	
	public Matricula() {
		this.ativo = true;
		this.dataAtualizacao = LocalDateTime.now();
		this.dataCadastro = LocalDateTime.now();
	}
	
	public Matricula(Aluno aluno, Curso curso, LocalDate dataMatricula) {
		this();
		this.aluno = aluno;
		this.curso = curso;
		this.dataMatricula = dataMatricula;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matricula")
	private Long id;
	
	@Column(name = "dt_matricula", nullable = false)
	private LocalDate dataMatricula;
	
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name = "dt_atualizacao")
	private LocalDateTime dataAtualizacao;
	
	@OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(LocalDate dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "\nAluno: " + this.getAluno().getNome()
				+ "\nCurso: " + this.getCurso().getNome()
				+ "\nData Matricula: " + this.getDataMatricula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}

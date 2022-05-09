package br.com.fiap.jpa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_aluno")
@SequenceGenerator(name = "aluno", sequenceName = "SQ_TB_ALUNO", allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "Aluno.listarPorUF", 
			query = "SELECT a FROM Aluno a WHERE a.endereco.uf = :uf AND a.ativo = :ativo"),
	@NamedQuery(name = "Aluno.listarPorCurso", query = "SELECT m.aluno FROM Matricula m WHERE m.curso.id = :idCurso"),
	@NamedQuery(name = "Aluno.listarDadosBasicos", query = "SELECT new Aluno(a.nome, a.cpf, a.dataNascimento) FROM Aluno a"),
	@NamedQuery(name = "Aluno.listarPaginado", query = "SELECT new Aluno(a.nome, a.cpf, a.dataNascimento) FROM Aluno a"),
	@NamedQuery(name = "Aluno.listar", query = "SELECT a FROM Aluno a INNER JOIN FETCH a.endereco")
})
public class Aluno implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Aluno() {
		this.ativo = true;
		this.dataCadastro = LocalDateTime.now();
		this.dataAtualizacao = LocalDateTime.now();
	}
	
	public Aluno(String nome, String cpf, LocalDate dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	public Aluno(String nome, String matricula, String cpf, LocalDate dataNascimento) {
		this();
		this.nome = nome;
		this.matricula = matricula;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluno")
	private Long id;
	
	@Column(name = "ds_nome", length = 80, nullable = false)
	private String nome;
	
	@Column(name = "nr_matricula", length = 10, nullable = false, unique = true)
	private String matricula;
	
	@Column(name = "nr_cpf", length = 14, nullable = false, unique = true)
	private String cpf;
	
	@Column(name = "dt_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name = "dt_atualizacao")
	private LocalDateTime dataAtualizacao;
	
	@OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Endereco endereco;
	
	/*
	@ManyToMany
	@JoinTable(
		name = "tb_aluno_curso",
		joinColumns = @JoinColumn(name = "aluno_id"),
		inverseJoinColumns = @JoinColumn(name = "curso_id")
	)
	private List<Curso> cursos;
	
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	*/

	
	@OneToMany(mappedBy = "aluno")
	private List<Matricula> matriculas;
	

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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
		
		return "\nMatricula: " + this.getMatricula() 
			+ "\nNome: " + this.getNome()
			+ "\nCPF: " + this.getCpf() 
			+ "\nNascimento: " + this.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
			+ "\nEndereço: " + this.getEndereco();
	}

}

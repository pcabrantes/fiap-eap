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
@Table(name = "t_gce_participante")
@SequenceGenerator(name = "participante", sequenceName = "sq_t_gce_participante", allocationSize = 1)
public class Participante implements Serializable {
	
	private static final long serialVersionUID = 654289321334111813L;
	
	public Participante() {
		
	}
	
	public Participante(String nome, LocalDate dataNascimento, String cpf, 
			String rg, LocalDate dataCadastro) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.rg = rg;
		this.dataCadastro = dataCadastro;
	}
	

	@Id
	@Column(name = "id_participante")
	@GeneratedValue(generator = "participante", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "nm_participante", length = 60, nullable = false)
	private String nome;
	
	@Column(name = "dt_nasc", nullable = false)
	private LocalDate dataNascimento;
	
	@Column(name = "nr_cpf", length = 14, nullable = false)
	private String cpf;
	
	@Column(name = "nr_rg", length = 12, nullable = false)
	private String rg;
	
	@Column(name = "dt_cadastro", nullable = false)
	private LocalDate dataCadastro;

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "\nNome: " + this.getNome()
			+ "\nNascimento: " + this.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
			+ "\nCPF: " + this.getCpf()
			+ "\nRG: " + this.getRg()
			+ "\nCadastro: " + this.getDataCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}

package br.com.fiap.jpa.dto;

public class AlunoCidadeDTO {

	public AlunoCidadeDTO(String nomeAluno, String cidade) {
		this.nomeAluno = nomeAluno;
		this.cidade = cidade;
	}
	
	private String nomeAluno;
	private String cidade;
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return this.getNomeAluno() + " - " + this.getCidade();
	}
}

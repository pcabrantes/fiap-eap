package br.com.fiap.jpa.dto;

public class AlunoCursoDTO {
	
	private String matricula;
	private String nomeAluno;
	private String nomeCurso;
	
	public AlunoCursoDTO() {
		
	}
	
	public AlunoCursoDTO(String matricula, String nomeAluno, String nomeCurso) {
		this.matricula = matricula;
		this.nomeAluno = nomeAluno;
		this.nomeCurso = nomeCurso;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	@Override
	public String toString() {
		return "\nMatricula: " + this.getMatricula()
			+ "\nAluno: " + this.getNomeAluno() 
			+ "\nCurso: " + this.getNomeCurso();
	}

}

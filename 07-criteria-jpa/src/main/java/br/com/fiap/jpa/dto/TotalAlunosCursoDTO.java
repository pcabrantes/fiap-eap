package br.com.fiap.jpa.dto;

public class TotalAlunosCursoDTO {
	
	public TotalAlunosCursoDTO() {
		
	}
	
	public TotalAlunosCursoDTO(String nomeCurso, long qtdAlunos) {
		this.nomeCurso = nomeCurso;
		this.qtdAlunos = qtdAlunos;
	}
	
	private String nomeCurso;
	private long qtdAlunos;
	
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public long getQtdAlunos() {
		return qtdAlunos;
	}
	public void setQtdAlunos(long qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}
	
	@Override
	public String toString() {
		return "\n" + this.getNomeCurso() + ": " + this.getQtdAlunos();
	}

}

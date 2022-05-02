package br.com.fiap.jpa;

import br.com.fiap.jpa.service.impl.AlunoServiceImpl;
import br.com.fiap.jpa.service.impl.CursoServiceImpl;
import br.com.fiap.jpa.service.impl.DisciplinaServiceImpl;
import br.com.fiap.jpa.service.impl.MatriculaServiceImpl;

public class App {

	public static void main(String[] args) {
		
		//Instanciando service para executar opera√ß√µes na entidade Aluno
		AlunoServiceImpl alunoService = AlunoServiceImpl.getInstance();
		
		CursoServiceImpl cursoService = CursoServiceImpl.getInstance();
		DisciplinaServiceImpl disciplinaService = DisciplinaServiceImpl.getInstance();
		MatriculaServiceImpl matriculaService = MatriculaServiceImpl.getInstance();
		
		System.out.println("============ Listar todos os alunos ==============");
		
		alunoService.listar().forEach(System.out::println);
		
		System.out.println("============ Listar alunos que residem em SP ==============");
		
		alunoService.listarPorUf("SP").forEach(System.out::println);
		
		System.out.println("============ Listar as disciplinas do curso 1 ==============");
		
		disciplinaService.listarPorCurso(1L).forEach(System.out::println);
		
		System.out.println("============ Listar os alunos do curso 1 ==============");
		
		alunoService.listarPorCurso(1L).forEach(System.out::println);
		
		System.out.println("============ Listar dados b·sicos dos alunos ==============");
		
		alunoService.listarDadosBasicos().forEach(System.out::println);
		
		System.out.println("============ Listando os alunos associados aos cursos ============");
		
		matriculaService.listarAlunosCursos().forEach(System.out::println);
		
		System.out.println("============ Listar p·gina 1 de alunos ==============");
		
		alunoService.listarPaginado(1).forEach(System.out::println);
		
		System.out.println("============ Listar p·gina 2 de alunos ==============");
		
		alunoService.listarPaginado(2).forEach(System.out::println);
		
		System.out.println("============ Total de alunos por curso ==============");
		
		matriculaService.contarPorCurso().forEach(System.out::println);
	}
}

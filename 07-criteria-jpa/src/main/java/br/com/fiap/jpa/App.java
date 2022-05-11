package br.com.fiap.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
		
		System.out.println("============ Listar Disciplinas por carga hor·ria ==========");
		
		disciplinaService.listarPorCargaHoraria(70).forEach(System.out::println);
		
		System.out.println("=========== Pesquisar Disciplinas por carga horaria =========");
		
		disciplinaService.pesquisar(null, 70, null).forEach(System.out::println);
		
		System.out.println("=========== `Pesquisar Disciplnas por CH e Data de Cad. ======");
		
		disciplinaService.pesquisar(null, 70, LocalDate.of(2022, 3, 1).atStartOfDay())
			.forEach(System.out::println);
		
		System.out.println("=========== `Pesquisar Disciplnas pelo nome ======");
		
		disciplinaService.pesquisar("COMPLIANCE & QUALITY ASSURANCE", null, null)
			.forEach(System.out::println);
	
		System.out.println("========== Pesquisar cursos por nome ===========");
		
		cursoService.pesquisar("Sistemas", null).forEach(System.out::println);
		
		System.out.println("========= Contar disciplinas =========");
		
		System.out.println(disciplinaService.contarDisciplinas());
		
		System.out.println("========= Listar disciplinas concatenado ===========");
		
		disciplinaService.listarConcatenado().forEach(System.out::println);
		
		System.out.println("========= Buscar disciplinas por nome ignore case ==========");
		
		disciplinaService.buscarPorNomeIgnoreCase("app").forEach(System.out::println);
		
		System.out.println("========= Buscar cursos por nome ignore case ============");
		
		cursoService.buscarPorNomeIgnoreCase("desenvolvimento").forEach(System.out::println);
		
		System.out.println("========= Listar disciplinas por curso com criteria ===========");
		
		disciplinaService.listarPorCursoComCriteria(1L).forEach(System.out::println);
		
		System.out.println("========= Listar matriculas por curso ============");
		
		matriculaService.listarPorCurso(1L).forEach(System.out::println);
		
		System.out.println("========= Listar alunos fetch endereco ============");
		
		alunoService.listarFetchEndereco("teste").forEach(System.out::println);
		
		System.out.println("========= Listar alunos cursos com criteria ========");
		
		matriculaService.listarAlunosCursoComCriteria().forEach(System.out::println);
		
		System.out.println("======== Listar alunos cidades ==========");
		
		alunoService.listarAlunosCidades().forEach(System.out::println);
	}
}

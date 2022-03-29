package br.com.fiap.jpa;

import java.time.LocalDate;

import br.com.fiap.jpa.entity.Aluno;
import br.com.fiap.jpa.entity.Endereco;
import br.com.fiap.jpa.service.impl.AlunoServiceImpl;

public class App {

	public static void main(String[] args) {
		
		//Instanciando service para executar operações na entidade Aluno
		AlunoServiceImpl alunoService = AlunoServiceImpl.getInstance();
		
		//Criando 3 instâncias de Aluno
		Aluno aluno1 = new Aluno("Aluno 1", "1111", "111.111.111-11", LocalDate.of(1980, 1, 1));
		Aluno aluno2 = new Aluno("Aluno 2", "2222", "222.222.222-22", LocalDate.of(1990, 1, 1));
		Aluno aluno3 = new Aluno("Aluno 3", "3333", "333.333.333-33", LocalDate.of(2000, 1, 1));
		
		//Inserindo no banco os alunos 1 e 2
		alunoService.inserir(aluno1);
		alunoService.inserir(aluno2);
		
		Endereco endereco = new Endereco("Av. Paulista", 777, "5 andar", "Bela Vista", "São Paulo", "SP", "04632-098");
		
		//Inserindo um aluno com endereço
		alunoService.inserirComEndereco(aluno3, endereco);
		
		alunoService.listar().forEach(System.out::println);
		
		//Excluindo o aluno com id 3 e, em cascata, removendo também o endereço associado a ele
		alunoService.remover(3L);
	}
}

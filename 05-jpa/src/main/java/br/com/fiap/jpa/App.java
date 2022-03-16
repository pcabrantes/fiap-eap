package br.com.fiap.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.fiap.jpa.entity.Aluno;

public class App {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FIAP_PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Aluno aluno1 = new Aluno(); // Estado New/Transient
		aluno1.setNome("Aluno 1");
		aluno1.setMatricula("11111");
		aluno1.setCpf("111.111.111-11");
		aluno1.setDataNascimento(LocalDate.of(1980, 1, 1));
		
		Aluno aluno2 = new Aluno(); // Estado New/Transient
		aluno2.setNome("Aluno 2");
		aluno2.setMatricula("22222");
		aluno2.setCpf("222.222.222-22");
		aluno2.setDataNascimento(LocalDate.of(1990, 1, 1));
		
		Aluno aluno3 = new Aluno(); // Estado New/Transient
		aluno3.setNome("Aluno 3");
		aluno3.setMatricula("33333");
		aluno3.setCpf("333.333.333-33");
		aluno3.setDataNascimento(LocalDate.of(2000, 1, 1));
		
		//Salvando os alunos no banco de dados
		entityManager.persist(aluno1); // Estado Managed
		entityManager.persist(aluno2); // Estado Managed
		entityManager.persist(aluno3); // Estado Managed
		
		//Buscando no banco o aluno que tenha id = 3
		Aluno aluno = entityManager.find(Aluno.class, 3L);

		//Se o aluno for encontrado, atualiza seus dados
		if (aluno != null) {
			aluno.setNome("Novo nome do aluno");
			aluno.setDataAtualizacao(LocalDateTime.now());
		}
		
		//Query para consultar todos os registros da tabela tb_aluno
		Query query = entityManager.createQuery("SELECT a FROM Aluno a");
		List<Aluno> resultado = query.getResultList();
		
		Aluno maisNovo = null;
		
		//Imprimindo o resultado e identificando o aluno mais novo
		for (Aluno alunoDB: resultado) {
			System.out.println(alunoDB);
			
			if (maisNovo == null || alunoDB.getDataNascimento().isBefore(maisNovo.getDataNascimento())) {
				maisNovo = alunoDB;
			}
		}
		
		//Removendo o aluno mais novo
		if (maisNovo != null) {
			System.out.println("\nAluno mais novo:\n" + maisNovo);
			entityManager.remove(maisNovo); //Estado Removed
		}
		
		entityManager.getTransaction().commit();
				
		entityManager.close(); // Estado Detached
		entityManagerFactory.close();
	}
}

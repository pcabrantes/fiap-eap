package br.com.fiap.jpa;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.fiap.jpa.dao.PersistenceManager;
import br.com.fiap.jpa.dao.impl.AlunoDAOImpl;
import br.com.fiap.jpa.entity.Aluno;

public class App {

	public static void main(String[] args) {
		

		AlunoDAOImpl dao = AlunoDAOImpl.getInstance();
		EntityManager entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		Aluno aluno3 = new Aluno("Aluno3", "33333", "333.333.333-33", LocalDate.of(1980, 1, 1));
		dao.salvar(new Aluno("Aluno1", "11111", "111.111.111-11", LocalDate.of(1980, 1, 1)), entityManager);
		dao.salvar(new Aluno("Aluno2", "22222", "222.222.222-22", LocalDate.of(1980, 1, 1)), entityManager);
		dao.salvar(aluno3, entityManager);
		
		aluno3.setNome("Alterado");
		
		dao.atualizar(aluno3, entityManager);
		
		dao.listar(entityManager).forEach(System.out::println);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
}

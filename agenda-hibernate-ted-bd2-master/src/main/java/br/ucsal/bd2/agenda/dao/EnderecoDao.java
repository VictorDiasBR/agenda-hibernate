package br.ucsal.bd2.agenda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ucsal.bd2.agenda.domain.Contato;
import br.ucsal.bd2.agenda.domain.Endereco;

public class EnderecoDao {

	private static EnderecoDao instance;
    protected EntityManager entityManager;
     
    public static EnderecoDao getInstance(){
              if (instance == null){
                       instance = new EnderecoDao();
              }
               
              return instance;
    }

    public EnderecoDao() {
              entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
              EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
              if (entityManager == null) {
                       entityManager = factory.createEntityManager();
              }

              return entityManager;
    }

    public Endereco getById(Integer id) {
              return entityManager.find(Endereco.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Endereco> findAll() {
              return entityManager.createQuery("FROM " + Endereco.class.getName()).getResultList();
    }

    public void persist(Endereco endereco) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.persist(endereco);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void merge(Endereco endereco) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.merge(endereco);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void remove(Endereco endereco) {
              try {
                       entityManager.getTransaction().begin();
                       endereco = entityManager.find(Endereco.class, endereco.getId());
                       entityManager.remove(endereco);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void removeById(Integer id) {
              try {
                       Endereco endereco = getById(id);
                       remove(endereco);
              } catch (Exception ex) {
                       ex.printStackTrace();
              }
    }

}

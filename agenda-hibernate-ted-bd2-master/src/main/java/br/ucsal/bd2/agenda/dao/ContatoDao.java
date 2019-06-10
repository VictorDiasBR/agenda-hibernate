package br.ucsal.bd2.agenda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.ucsal.bd2.agenda.domain.Contato;

public class ContatoDao {

	private static ContatoDao instance;
    protected EntityManager entityManager;
     
    public static ContatoDao getInstance(){
              if (instance == null){
                       instance = new ContatoDao();
              }
               
              return instance;
    }

    public ContatoDao() {
              entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
              EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
              if (entityManager == null) {
                       entityManager = factory.createEntityManager();
              }

              return entityManager;
    }

    public Contato getById(Integer id) {
              return entityManager.find(Contato.class, id);
    }
    
    public Contato getByNome(String nome) {
    	TypedQuery<Contato> query = entityManager.createQuery("select c from tab_contato c where c.nome=:nome", Contato.class);
         query.setParameter("nome", nome);
         Contato contato = query.getSingleResult();
         return contato;
    }

    @SuppressWarnings("unchecked")
    public List<Contato> findAll() {
              return entityManager.createQuery("FROM " + Contato.class.getName()).getResultList();
    }

    public void persist(Contato contato) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.persist(contato);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void merge(Contato contato) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.merge(contato);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void remove(Contato contato) {
              try {
                       entityManager.getTransaction().begin();
                       contato = entityManager.find(Contato.class, contato.getId());
                       entityManager.remove(contato);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void removeById(Integer id) {
              try {
                       Contato contato = getById(id);
                       remove(contato);
              } catch (Exception ex) {
                       ex.printStackTrace();
              }
    }


}

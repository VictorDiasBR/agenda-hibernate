package br.ucsal.bd2.agenda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.ucsal.bd2.agenda.domain.Cidade;
import br.ucsal.bd2.agenda.domain.Contato;

public class CidadeDao {
	private static CidadeDao instance;
    protected EntityManager entityManager;
     
    public static CidadeDao getInstance(){
              if (instance == null){
                       instance = new CidadeDao();
              }
               
              return instance;
    }

    public CidadeDao() {
              entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
              EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
              if (entityManager == null) {
                       entityManager = factory.createEntityManager();
              }

              return entityManager;
    }

    public Cidade getById( String id) {
              return entityManager.find(Cidade.class, id);
    }
    
    public Cidade getByNome(String nome) {
    	TypedQuery<Cidade> query = entityManager.createQuery("select c from tab_cidade c where c.nome=:nome", Cidade.class);
         query.setParameter("nome", nome);
         Cidade cidade = query.getSingleResult();
         return cidade;
    }

    @SuppressWarnings("unchecked")
    public List<Cidade> findAll() {
              return entityManager.createQuery("FROM " + Cidade.class.getName()).getResultList();
    }

    public void persist(Cidade cidade) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.persist(cidade);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void merge(Cidade cidade) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.merge(cidade);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void remove(Cidade cidade) {
              try {
                       entityManager.getTransaction().begin();
                      cidade = entityManager.find(Cidade.class, cidade.getSigla());
                       entityManager.remove(cidade);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void removeById(String id) {
              try {
                      Cidade cidade = getById(id);
                       remove(cidade);
              } catch (Exception ex) {
                       ex.printStackTrace();
              }
    }

}

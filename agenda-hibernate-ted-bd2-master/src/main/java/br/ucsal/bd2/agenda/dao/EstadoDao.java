package br.ucsal.bd2.agenda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.ucsal.bd2.agenda.domain.Contato;
import br.ucsal.bd2.agenda.domain.Estado;

public class EstadoDao {
	private static EstadoDao instance;
    protected EntityManager entityManager;
     
    public static EstadoDao getInstance(){
              if (instance == null){
                       instance = new EstadoDao();
              }
               
              return instance;
    }

    public EstadoDao() {
              entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
              EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
              if (entityManager == null) {
                       entityManager = factory.createEntityManager();
              }

              return entityManager;
    }

    public Estado getById( String id) {
              return entityManager.find(Estado.class, id);
    }
    
    public Estado getByNome(String nome) {
    	TypedQuery<Estado> query = entityManager.createQuery("select e from tab_estado e where e.nome=:nome", Estado.class);
         query.setParameter("nome", nome);
         Estado estado = query.getSingleResult();
         return estado;
    }

    @SuppressWarnings("unchecked")
    public List<Estado> findAll() {
              return entityManager.createQuery("FROM " + Estado.class.getName()).getResultList();
    }

    public void persist(Estado estado) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.persist(estado);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void merge(Estado estado) {
              try {
                       entityManager.getTransaction().begin();
                       entityManager.merge(estado);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void remove(Estado estado) {
              try {
                       entityManager.getTransaction().begin();
                      estado = entityManager.find(Estado.class, estado.getSigla());
                       entityManager.remove(estado);
                       entityManager.getTransaction().commit();
              } catch (Exception ex) {
                       ex.printStackTrace();
                       entityManager.getTransaction().rollback();
              }
    }

    public void removeById(String id) {
              try {
                       Estado estado = getById(id);
                       remove(estado);
              } catch (Exception ex) {
                       ex.printStackTrace();
              }
    }


}

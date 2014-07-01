/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import model.Especialidade;
import model.Profissional;
import model.Sala;

/**
 *
 * @author Manoela
 */
public class SalaJpaController implements Serializable {

    public SalaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sala sala) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sala);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sala sala) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sala = em.merge(sala);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sala.getId();
                if (findSala(id) == null) {
                    throw new NonexistentEntityException("The sala with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sala sala;
            try {
                sala = em.getReference(Sala.class, id);
                sala.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sala with id " + id + " no longer exists.", enfe);
            }
            em.remove(sala);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

   
    
    public List<Sala> getListSala(Sala s) {
        
////        return (List<Sala>) em.createQuery("select s from Sala s where s.numero =" + numero).getResultList();
        //String sql = "select s from Sala s where s.numero =" + numero;  
        List<Sala> listAll = new ArrayList<Sala>();  
//        Sala s;  

        try {  

            EntityManager em = getEntityManager();
            listAll=em.createQuery("select e from Especialidade e where e.id =" + s.getEspecialidade().getId().toString()).getResultList();
            
                //s = new Sala();  
                //listAll.add(s);  
            
                System.out.println("ADICIONOU 1"); 
                System.out.println(listAll);
  

        } catch (Exception e) {  
            System.out.println("Listando... "+e.getMessage());  
        }  

        return listAll;
    }
    
    public List<Sala> findSalaEntities() {
        return findSalaEntities(true, -1, -1);
    }

    public List<Sala> findSalaEntities(int maxResults, int firstResult) {
        return findSalaEntities(false, maxResults, firstResult);
    }

    private List<Sala> findSalaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sala.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Sala findSala(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sala.class, id);
        } finally {
            em.close();
        }
    }

     public List getListEspecialidade(Sala s) {
        EntityManager em = getEntityManager();
        List especialidades = em.createQuery("select e from Especialidade e where e.id = " + s.getEspecialidade().getId().toString()).getResultList();
         System.out.println(especialidades);
        return especialidades;
    }
    
    public int getSalaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sala> rt = cq.from(Sala.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    
    
    public List getListSala(Especialidade e) {
        EntityManager em = getEntityManager();
        List salas = em.createQuery("select s from Sala s where s.especialidade.id = " + e.getId().toString()).getResultList();
        return salas;
    }
    
    public static List<Especialidade> getLista() {
        List<Especialidade> objetos = new ArrayList();
        return objetos;
    }
    
     public List getSala(String e) {
        EntityManager em = getEntityManager();
        List especialidades = em.createQuery("select s.especialidade from Sala s where s.numero =" + e).getResultList();
        return especialidades;
    }
    
}

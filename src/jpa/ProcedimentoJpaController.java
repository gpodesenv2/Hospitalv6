/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import model.Procedimento;

/**
 *
 * @author Manoela
 */
public class ProcedimentoJpaController implements Serializable {

    public ProcedimentoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procedimento procedimento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(procedimento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procedimento procedimento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            procedimento = em.merge(procedimento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = procedimento.getId();
                if (findProcedimento(id) == null) {
                    throw new NonexistentEntityException("The procedimento with id " + id + " no longer exists.");
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
            Procedimento procedimento;
            try {
                procedimento = em.getReference(Procedimento.class, id);
                procedimento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procedimento with id " + id + " no longer exists.", enfe);
            }
            em.remove(procedimento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procedimento> findProcedimentoEntities() {
        return findProcedimentoEntities(true, -1, -1);
    }

    public List<Procedimento> findProcedimentoEntities(int maxResults, int firstResult) {
        return findProcedimentoEntities(false, maxResults, firstResult);
    }

    private List<Procedimento> findProcedimentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procedimento.class));
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

    public Procedimento findProcedimento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procedimento.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcedimentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procedimento> rt = cq.from(Procedimento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
  
public List getProcedimento(){
        
        EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query procedimento = em.createQuery("select  c  from Procedimento c ");
        List  obs = procedimento.getResultList();
        return obs;
        
    }
}
    


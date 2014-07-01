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
import model.CID;

/**
 *
 * @author Manoela
 */
public class CIDJpaController implements Serializable {
    

    public CIDJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    
       private EntityManagerFactory emf = null;
 

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CID CID) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CID);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CID CID) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CID = em.merge(CID);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CID.getId();
                if (findCID(id) == null) {
                    throw new NonexistentEntityException("The cID with id " + id + " no longer exists.");
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
            CID CID;
            try {
                CID = em.getReference(CID.class, id);
                CID.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CID with id " + id + " no longer exists.", enfe);
            }
            em.remove(CID);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CID> findCIDEntities() {
        return findCIDEntities(true, -1, -1);
    }

    public List<CID> findCIDEntities(int maxResults, int firstResult) {
        return findCIDEntities(false, maxResults, firstResult);
    }

    private List<CID> findCIDEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CID.class));
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

    public CID findCID(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CID.class, id);
        } finally {
            em.close();
        }
    }

    public int getCIDCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CID> rt = cq.from(CID.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List getCID (){
        
        EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query CID = em.createQuery("select  c  from CID c ");
        List  obs = CID.getResultList();
        return obs;
        
    }
    
}

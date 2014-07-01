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
import model.MotivoAlta;

/**
 *
 * @author Manoela
 */
public class MotivoAltaJpaController implements Serializable {

    public MotivoAltaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MotivoAlta motivoAlta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(motivoAlta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MotivoAlta motivoAlta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            motivoAlta = em.merge(motivoAlta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = motivoAlta.getId();
                if (findMotivoAlta(id) == null) {
                    throw new NonexistentEntityException("The motivoAlta with id " + id + " no longer exists.");
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
            MotivoAlta motivoAlta;
            try {
                motivoAlta = em.getReference(MotivoAlta.class, id);
                motivoAlta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The motivoAlta with id " + id + " no longer exists.", enfe);
            }
            em.remove(motivoAlta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MotivoAlta> findMotivoAltaEntities() {
        return findMotivoAltaEntities(true, -1, -1);
    }

    public List<MotivoAlta> findMotivoAltaEntities(int maxResults, int firstResult) {
        return findMotivoAltaEntities(false, maxResults, firstResult);
    }

    private List<MotivoAlta> findMotivoAltaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MotivoAlta.class));
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

    public MotivoAlta findMotivoAlta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MotivoAlta.class, id);
        } finally {
            em.close();
        }
    }

    public int getMotivoAltaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MotivoAlta> rt = cq.from(MotivoAlta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List getMotivoAlta (){
        
        EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query motivoAlta = em.createQuery("select m.descricao from MotivoAlta m");
        List  obs = motivoAlta.getResultList();
        return obs;
        
    }
    
}

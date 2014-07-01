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
import model.Alta;
import model.Paciente;

/**
 *
 * @author Manoela
 */
public class AltaJpaController implements Serializable {

    public AltaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alta alta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(alta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alta alta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            alta = em.merge(alta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = alta.getId();
                if (findAlta(id) == null) {
                    throw new NonexistentEntityException("The alta with id " + id + " no longer exists.");
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
            Alta alta;
            try {
                alta = em.getReference(Alta.class, id);
                alta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alta with id " + id + " no longer exists.", enfe);
            }
            em.remove(alta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alta> findAltaEntities() {
        return findAltaEntities(true, -1, -1);
    }

    public List<Alta> findAltaEntities(int maxResults, int firstResult) {
        return findAltaEntities(false, maxResults, firstResult);
    }

    private List<Alta> findAltaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alta.class));
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

    public Alta findAlta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alta.class, id);
        } finally {
            em.close();
        }
    }

    public int getAltaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alta> rt = cq.from(Alta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Alta getAlta (Paciente id){
        
        EntityManager em = getEntityManager();
        //System.out.println("passou1");
        return (Alta) em.createQuery ("select m from Alta m where m.paciente.id = "+ id.getId().toString()).getSingleResult();        
    }
}

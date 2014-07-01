/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import com.toedter.calendar.JDateChooser;
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
import model.Internacao;
import model.Prescricao;

/**
 *
 * @author Manoela
 */
public class PrescricaoJpaController implements Serializable {

    public PrescricaoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prescricao prescricao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(prescricao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prescricao prescricao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prescricao = em.merge(prescricao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prescricao.getId();
                if (findPrescricao(id) == null) {
                    throw new NonexistentEntityException("The prescricao with id " + id + " no longer exists.");
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
            Prescricao prescricao;
            try {
                prescricao = em.getReference(Prescricao.class, id);
                prescricao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prescricao with id " + id + " no longer exists.", enfe);
            }
            em.remove(prescricao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prescricao> findPrescricaoEntities() {
        return findPrescricaoEntities(true, -1, -1);
    }

    public List<Prescricao> findPrescricaoEntities(int maxResults, int firstResult) {
        return findPrescricaoEntities(false, maxResults, firstResult);
    }

    private List<Prescricao> findPrescricaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prescricao.class));
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

    public Prescricao findPrescricao(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prescricao.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrescricaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prescricao> rt = cq.from(Prescricao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List getListPresMed(JDateChooser data, Internacao i, Prescricao p){
        EntityManager em = getEntityManager();
        Query prescricao = em.createQuery("select m  from Prescricao m where m.dataPresc =:d and m.internacao.id =:int and m.medicamento.id <> null");
        prescricao.setParameter("d", data.getCalendar().getTime());
        prescricao.setParameter("int", i.getId());
        
        List  obs = prescricao.getResultList();
        return obs;
      
    } 
    public List getListPresProc(JDateChooser data, Internacao i, Prescricao p){
        EntityManager em = getEntityManager();
        Query prescricao = em.createQuery("select m  from Prescricao m where m.dataPresc =:d and m.internacao.id =:int and m.procedimento.id <> null");
        prescricao.setParameter("d", data.getCalendar().getTime());
        prescricao.setParameter("int", i.getId());
        
        List  obs = prescricao.getResultList();
        return obs;
      
    } 
       
    
}

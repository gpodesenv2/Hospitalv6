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
import model.Localizacao;
import model.TipoLeito;

/**
 *
 * @author Manoela
 */
public class LocalizacaoJpaController implements Serializable {

    public LocalizacaoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Localizacao localizacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(localizacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Localizacao localizacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            localizacao = em.merge(localizacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = localizacao.getId();
                if (findLocalizacao(id) == null) {
                    throw new NonexistentEntityException("The localizacao with id " + id + " no longer exists.");
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
            Localizacao localizacao;
            try {
                localizacao = em.getReference(Localizacao.class, id);
                localizacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localizacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(localizacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Localizacao> findLocalizacaoEntities() {
        return findLocalizacaoEntities(true, -1, -1);
    }

    public List<Localizacao> findLocalizacaoEntities(int maxResults, int firstResult) {
        return findLocalizacaoEntities(false, maxResults, firstResult);
    }

    private List<Localizacao> findLocalizacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Localizacao.class));
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

    public Localizacao findLocalizacao(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Localizacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalizacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Localizacao> rt = cq.from(Localizacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    
    public List getListLocal(Localizacao e) {
    EntityManager em = getEntityManager();
        List local = em.createQuery("select m.local.id from TipoLeito m where m.id= " + e.getId().toString()).getResultList();
        return local;
    }
    
    
    public List getListLocalizacao(String e) {
    EntityManager em = getEntityManager();
        List local = em.createQuery("select m.local.id from TipoLeito m where m.descricao="+e.toString()).getResultList();
        return local;
    }
    
    public List getLocal() {
    EntityManager em = getEntityManager();
        List local = em.createQuery("select m from Localizacao m").getResultList();
        return local;
    }
     
    
}

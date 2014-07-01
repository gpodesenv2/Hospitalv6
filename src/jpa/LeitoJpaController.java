/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import model.Leito;
import model.Localizacao;
import model.TipoLeito;

/**
 *
 * @author Manoela
 */
public class LeitoJpaController implements Serializable {

    public LeitoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Leito leito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(leito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Leito leito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            leito = em.merge(leito);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = leito.getId();
                if (findLeito(id) == null) {
                    throw new NonexistentEntityException("The leito with id " + id + " no longer exists.");
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
            Leito leito;
            try {
                leito = em.getReference(Leito.class, id);
                leito.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The leito with id " + id + " no longer exists.", enfe);
            }
            em.remove(leito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Leito> findLeitoEntities() {
        return findLeitoEntities(true, -1, -1);
    }

    public List<Leito> findLeitoEntities(int maxResults, int firstResult) {
        return findLeitoEntities(false, maxResults, firstResult);
    }

    private List<Leito> findLeitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Leito.class));
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

    public Leito findLeito(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Leito.class, id);
        } finally {
            em.close();
        }
    }

    public int getLeitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Leito> rt = cq.from(Leito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List getLeito (){
        
        EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query leito = em.createQuery("select l.* from Leito l");
        List  obs = leito.getResultList();
        return obs;
        
    }

    public ResultSet rs = null;

    public void executaSQL(String SQL) {
        EntityManager em = getEntityManager();
        em.createQuery(SQL);
    }
    
    public List<Leito> getTipo (){
        
        EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query leito = em.createQuery("select l.tipo from Leito l");
        List  obs = leito.getResultList();
        return obs;
        
    }

    public List getLocal (TipoLeito tipo){
        
        EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query leito = em.createQuery("select l from TipoLeito l where l.local.id = :hi");
        //"select b from Bairro b where b.cidade.id = " + c.getId().toString()).getResultList();
        leito.setParameter("hi", tipo.getId().toString());
        List  obs = leito.getResultList();
        return obs;
        
    }

    public List getLeitoLocal (Localizacao local, String tipo){
        
        EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query leito = em.createQuery("select l from Leito l where l.localizacao.id = :e and l.tipo = :t");
        //System.out.println("passou2");
        leito.setParameter("t", tipo.toString());
        leito.setParameter("e", local.getId());
        List  obs = leito.getResultList();
        return obs;
        
    }
    
        
    
}

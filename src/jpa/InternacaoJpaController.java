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
import model.Leito;
import model.Localizacao;
import model.Paciente;

/**
 *
 * @author Manoela
 */
public class InternacaoJpaController implements Serializable {

    public InternacaoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Internacao internacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(internacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Internacao internacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            internacao = em.merge(internacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = internacao.getId();
                if (findInternacao(id) == null) {
                    throw new NonexistentEntityException("The internacao with id " + id + " no longer exists.");
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
            Internacao internacao;
            try {
                internacao = em.getReference(Internacao.class, id);
                internacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The internacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(internacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Internacao> findInternacaoEntities() {
        return findInternacaoEntities(true, -1, -1);
    }

    public List<Internacao> findInternacaoEntities(int maxResults, int firstResult) {
        return findInternacaoEntities(false, maxResults, firstResult);
    }

    private List<Internacao> findInternacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Internacao.class));
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

    public Internacao findInternacao(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Internacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getInternacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Internacao> rt = cq.from(Internacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Internacao getInter(String id) {
        EntityManager em = getEntityManager();
        return (Internacao) em.createQuery("select i from Internacao i where i.id ="+id).getSingleResult();
    }
    
    public Internacao getInterPaciente(String id) {
        EntityManager em = getEntityManager();
        return (Internacao) em.createQuery("select i from Internacao i where i.prontuario="+id).getSingleResult();
    }
    
    public List getListInternacao(Leito leito ) {
    EntityManager em = getEntityManager();
        
        Query internacao = em.createQuery("select i  from Internacao i where i.leito.id = " + leito.getId().toString());
        
        List  obs = internacao.getResultList();//.getResultList();
        return obs;
    }
    
    
    public List getListPaciente (String tipo, Localizacao local, JDateChooser datai, JDateChooser dataf ) {
     EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query internacao = em.createQuery("select i from Internacao i where i.tipoInter = :tp and i.local.id = :lo and i.dataEntrada > :di and i.dataEntrada < :df");
        //System.out.println("passou2");
        internacao.setParameter("di", datai.getCalendar().getTime());
        internacao.setParameter("df", dataf.getCalendar().getTime());
        internacao.setParameter("lo", local.getId());
        internacao.setParameter("tp", tipo.toString());
       // System.out.println("passou3");
        List  obs = internacao.getResultList();//.getResultList();
        return obs;
    }
    
    
    public List getListLeito (String tipo, Localizacao local, JDateChooser datai, JDateChooser dataf ) {
    EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query internacao = em.createQuery("select i.leito.id from Internacao i where i.tipoInter = :tp and i.local.id = :lo and i.dataEntrada > :di and i.dataEntrada < :df");
        //System.out.println("passou2");
        internacao.setParameter("di", datai.getCalendar().getTime());
        internacao.setParameter("df", dataf.getCalendar().getTime());
        internacao.setParameter("lo", local.getId());
        internacao.setParameter("tp", tipo.toString());
       // System.out.println("passou3");
        List  obs = internacao.getResultList();//.getResultList();
        return obs;
    }
    
    public List getPaciente (String prontuario) {
    EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query internacao = em.createQuery("select i.prontuario from Internacao i where i.prontuario=:tp");
        //System.out.println("passou2");        
        internacao.setParameter("tp", prontuario.toString());
       // System.out.println("passou3");
        List  obs = internacao.getResultList();//.getResultList();
        return obs;
    }
    
    public List getInternacao (Paciente paciente) {
    EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query internacao = em.createQuery("select i from Internacao i where i.prontuario=:tp");
        //System.out.println("passou2");        
        internacao.setParameter("tp", paciente.toString());
       // System.out.println("passou3");
        List  obs = internacao.getResultList();//.getResultList();
        return obs;
    }
}

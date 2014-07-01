/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import com.toedter.calendar.JDateChooser;
import java.util.GregorianCalendar;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import model.Agendamento;
import model.Evolucao;
import model.Paciente;

/**
 *
 * @author Manoela
 */
public class EvolucaoJpaController implements Serializable {

    public EvolucaoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evolucao evolucao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(evolucao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Evolucao evolucao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            evolucao = em.merge(evolucao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = evolucao.getId();
                if (findEvolucao(id) == null) {
                    throw new NonexistentEntityException("The evolucao with id " + id + " no longer exists.");
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
            Evolucao evolucao;
            try {
                evolucao = em.getReference(Evolucao.class, id);
                evolucao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evolucao with id " + id + " no longer exists.", enfe);
            }
            em.remove(evolucao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Evolucao> findEvolucaoEntities() {
        return findEvolucaoEntities(true, -1, -1);
    }

    public List<Evolucao> findEvolucaoEntities(int maxResults, int firstResult) {
        return findEvolucaoEntities(false, maxResults, firstResult);
    }

    private List<Evolucao> findEvolucaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Evolucao.class));
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

    public Evolucao findEvolucao(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evolucao.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvolucaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Evolucao> rt = cq.from(Evolucao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
 
    public Evolucao getEvolucao(Paciente paciente){
        EntityManager em = getEntityManager();
        Query evolucao = em.createQuery("select e.evolucao from Evolucao e where e.paciente.id ="+ paciente.getId());
        System.out.println("aqui");
         Evolucao e = (Evolucao)evolucao.getSingleResult();
        System.out.println("aqui3");
            return e;
    }
    
  public List getListEvolucao(Paciente paciente){
     EntityManager em = getEntityManager();
        Query pacientes = em.createQuery("select a from Evolucao a where a.paciente.id ="+ paciente.getId());
        List  obs = pacientes.getResultList();
        return obs; 
  }
  
  public List getListDataEvolucao(Agendamento ag){
     EntityManager em = getEntityManager();
        Query pacientes = em.createQuery("select a.dataEvolucao from Evolucao a where a.paciente.id ="+ ag.getId());
        List  obs = pacientes.getResultList();
        return obs; 
  }
  
  public Evolucao getEvolucaoData(JDateChooser data, Agendamento a){
        EntityManager em = getEntityManager();
        Query evolucao = em.createQuery("select e from Evolucao e where e.agendamento.id=:ag and e.dataEvolucao=:de");
        evolucao.setParameter("ag", a.getId());
        evolucao.setParameter("de", data.getCalendar().getTime());
        System.out.println("aqui");
         Evolucao e = (Evolucao)evolucao.getSingleResult();
        System.out.println("aqui3");
            return e;
    }
  
}

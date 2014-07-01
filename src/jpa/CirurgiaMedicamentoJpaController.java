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
import model.CirurgiaMedicamento;
import model.Especialidade;
import model.Medicamento;

/**
 *
 * @author Manoela
 */
public class CirurgiaMedicamentoJpaController implements Serializable {

    public CirurgiaMedicamentoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CirurgiaMedicamento cirurgiaMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cirurgiaMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CirurgiaMedicamento cirurgiaMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cirurgiaMedicamento = em.merge(cirurgiaMedicamento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cirurgiaMedicamento.getId();
                if (findCirurgiaMedicamento(id) == null) {
                    throw new NonexistentEntityException("The cirurgiaMedicamento with id " + id + " no longer exists.");
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
            CirurgiaMedicamento cirurgiaMedicamento;
            try {
                cirurgiaMedicamento = em.getReference(CirurgiaMedicamento.class, id);
                cirurgiaMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cirurgiaMedicamento with id " + id + " no longer exists.", enfe);
            }
            em.remove(cirurgiaMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CirurgiaMedicamento> findCirurgiaMedicamentoEntities() {
        return findCirurgiaMedicamentoEntities(true, -1, -1);
    }

    public List<CirurgiaMedicamento> findCirurgiaMedicamentoEntities(int maxResults, int firstResult) {
        return findCirurgiaMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<CirurgiaMedicamento> findCirurgiaMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CirurgiaMedicamento.class));
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

    public CirurgiaMedicamento findCirurgiaMedicamento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CirurgiaMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCirurgiaMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CirurgiaMedicamento> rt = cq.from(CirurgiaMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List getMedicamentos(String codAgendamento) {
        EntityManager em = getEntityManager();
        Query medicamentos = em.createQuery("select a from CirurgiaMedicamento a where a.agendamento.id=" + codAgendamento);
        List  obs = medicamentos.getResultList();
        return obs; 
    }
    
    public List getListRelatorio(Medicamento medicamento, JDateChooser datai, JDateChooser dataf ) {
     EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query medicamentos = em.createQuery("select a.medicamento from CirurgiaMedicamento a where a.medicamento.id = :m and a.dataCirurgia > :di and a.dataCirurgia < :df");
        //System.out.println("passou2");
        medicamentos.setParameter("di", datai.getCalendar().getTime());
        medicamentos.setParameter("df", dataf.getCalendar().getTime());
        medicamentos.setParameter("m", medicamento.getId());
       // System.out.println("passou3");
        List  obs = medicamentos.getResultList();//.getResultList();
        return obs;
    }
    
}

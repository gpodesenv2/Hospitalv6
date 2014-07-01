/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
import model.Especialidade;
import model.Profissional;
import model.Sala;

/**
 *
 * @author Manoela
 */
public class AgendamentoJpaController implements Serializable {

    
    public AgendamentoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
        
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ResultSet rs;
    
    public void create(Agendamento agendamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(agendamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agendamento agendamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            agendamento = em.merge(agendamento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = agendamento.getId();
                if (findAgendamento(id) == null) {
                    throw new NonexistentEntityException("The agendamento with id " + id + " no longer exists.");
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
            Agendamento agendamento;
            try {
                agendamento = em.getReference(Agendamento.class, id);
                agendamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agendamento with id " + id + " no longer exists.", enfe);
            }
            em.remove(agendamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agendamento> findAgendamentoEntities() {
        return findAgendamentoEntities(true, -1, -1);
    }

    public List<Agendamento> findAgendamentoEntities(int maxResults, int firstResult) {
        return findAgendamentoEntities(false, maxResults, firstResult);
    }

    private List<Agendamento> findAgendamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agendamento.class));
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

    public Agendamento findAgendamento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agendamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgendamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agendamento> rt = cq.from(Agendamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List getListAgendamento(Calendar data, String hora, Sala sala ) {
    EntityManager em = getEntityManager();
        
        Query agendamento = em.createQuery("select a  from Agendamento a where a.dataInicio = :di and a.horaInicio = :hi and a.sala.id = " + sala.getId().toString());
        agendamento.setParameter("di", data.getTime()); 
        //agendamento.setParameter("df", dataFim.getTime()); 
        agendamento.setParameter("hi", hora.toString());
        //agendamento.setParameter("hf", horaFim.toString());
        List  obs = agendamento.getResultList();//.getResultList();
        return obs;
    }
    
    public boolean getDataInicio(Calendar data) {
    EntityManager em = getEntityManager();
        
        Query agendamento = em.createQuery("select a.dataInicio from Agendamento a where a.dataInicio = :di");
        agendamento.setParameter("di", data.getTime());
        if("di"==null){
            return false;
        }
        else{
        return true;
        }
    }
    
    public boolean getHoraInicio(String hora) {
    EntityManager em = getEntityManager();
        
        Query agendamento = em.createQuery("select a.horaInicio from Agendamento a where a.horaInicio = :hi");
        agendamento.setParameter("hi", hora.toString());
        if("hi"==null){
            return false;
        }
        else{
        return true;
        }
    }
    
    
    public Agendamento getAgendamento(String codPront) {
        EntityManager em = getEntityManager();
        return (Agendamento) em.createQuery("select a from Agendamento a where a.paciente.id =" + codPront).getSingleResult();
    }
    
    
    public List<Agendamento> getAgendamentoPac(String codPront) {
        EntityManager em = getEntityManager();
        Query pacientes = em.createQuery("select a from Agendamento a where a.paciente.id =" + codPront);
        List  obs = pacientes.getResultList();
        return obs; 
    }

    
    
     public List getListRelatorio(Especialidade especialidade, Profissional medico, JDateChooser data ) {
     EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query agendamento = em.createQuery("select a.procedimento from Agendamento a where a.medico.id = :m and a.especialidade.id = :e and a.dataInicio = :di");
        //System.out.println("passou2");
        agendamento.setParameter("di", data.getCalendar().getTime());
        agendamento.setParameter("m", medico.getId());
        agendamento.setParameter("e", especialidade.getId());
       // System.out.println("passou3");
        List  obs = agendamento.getResultList();//.getResultList();
        return obs;
    }
     
     public List getListProcedimento(Especialidade especialidade) {
     EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query agendamento = em.createQuery("select a.procedimento from Agendamento a where a.especialidade.id = :e");
        //System.out.println("passou2");
        agendamento.setParameter("e", especialidade.getId());
       // System.out.println("passou3");
        List  obs = agendamento.getResultList();//.getResultList();
        return obs;
    }
     public List getListMedico(Especialidade especialidade) {
     EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query agendamento = em.createQuery("select a.medico from Agendamento a where a.especialidade.id = :e");
        //System.out.println("passou2");
        agendamento.setParameter("e", especialidade.getId());
       // System.out.println("passou3");
        List  obs = agendamento.getResultList();//.getResultList();
        return obs;
    }
    
     public List getListData(Especialidade especialidade) {
     EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query agendamento = em.createQuery("select a.dataInicio from Agendamento a where a.especialidade.id = :e");
        agendamento.setParameter("e", especialidade.getId());       
        List  obs = agendamento.getResultList();
        return obs;
    }
     
     public List getListEspecialidade(JDateChooser datai, JDateChooser dataf) {
     EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query agendamento = em.createQuery("select a.especialidade from Agendamento a where a.dataInicio > :di and a.dataFim < :df"); 
        agendamento.setParameter("di", datai.getCalendar().getTime());
        agendamento.setParameter("df", dataf.getCalendar().getTime());
        List  obs = agendamento.getResultList();
        return obs;
    }
     public List getListMedico(JDateChooser datai, JDateChooser dataf) {
     EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query agendamento = em.createQuery("select a.medico from Agendamento a where a.dataInicio > :di and a.dataFim < :df"); 
        agendamento.setParameter("di", datai.getCalendar().getTime());
        agendamento.setParameter("df", dataf.getCalendar().getTime());
        List  obs = agendamento.getResultList();
        return obs;
    }
     public List getListProcedimento() {
     EntityManager em = getEntityManager();
        //System.out.println("passou1");
        Query agendamento = em.createQuery("select a.procedimento from Agendamento a");
        List  obs = agendamento.getResultList();
        return obs;
    }
     
     public List getListRelatorio2(JDateChooser datai, JDateChooser dataf) {
     EntityManager em = getEntityManager();
        System.out.println("passou1");
        Query agendamento = em.createQuery("select a.procedimento from Agendamento a where a.dataInicio > :di and a.dataFim < :df"); 
        agendamento.setParameter("di", datai.getCalendar().getTime());
        agendamento.setParameter("df", dataf.getCalendar().getTime());
        System.out.println("passou2");
        List  obs = agendamento.getResultList();
        return obs;
    }
//    public String getDisponibilidade(Paciente p, Profissional m , Sala s, ) {
//    EntityManager em = getEntityManager();
//        List mensagem = em.createQuery("select m from Profissional m where m.especialidade.id = " + e.getId().toString());
//        
//        return mensagem;
//    }
     
     public List getAgendamentoData(JDateChooser data, Profissional medico) {
        EntityManager em = getEntityManager(); 
        //Query agendamento = em.createQuery("select a.dataInicio, a.horaInicio, a.procedimento, a.paciente.id, a.profissional.id from Agendamento a where a.dataInicio =:di and a.profissional.id =:pr");

        Query agendamento = em.createQuery("select a from Agendamento a where a.dataInicio =:di and a.profissional.id =:pr");

        agendamento.setParameter("di", data.getCalendar().getTime());
        agendamento.setParameter("pr", medico.getId());         
        List obs = agendamento.getResultList();
        //System.out.println(">>>>>>>>>> Dentro do jpa:  "+obs.toString());
        return obs;
    }
     
     
}

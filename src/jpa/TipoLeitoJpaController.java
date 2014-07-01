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
public class TipoLeitoJpaController implements Serializable {

    public TipoLeitoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU2");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoLeito tipoLeito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoLeito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoLeito tipoLeito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoLeito = em.merge(tipoLeito);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoLeito.getId();
                if (findTipoLeito(id) == null) {
                    throw new NonexistentEntityException("The tipoLeito with id " + id + " no longer exists.");
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
            TipoLeito tipoLeito;
            try {
                tipoLeito = em.getReference(TipoLeito.class, id);
                tipoLeito.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoLeito with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoLeito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoLeito> findTipoLeitoEntities() {
        return findTipoLeitoEntities(true, -1, -1);
    }

    public List<TipoLeito> findTipoLeitoEntities(int maxResults, int firstResult) {
        return findTipoLeitoEntities(false, maxResults, firstResult);
    }

    private List<TipoLeito> findTipoLeitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoLeito.class));
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

    public TipoLeito findTipoLeito(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoLeito.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoLeitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoLeito> rt = cq.from(TipoLeito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List getListTipo(Localizacao e) {
    EntityManager em = getEntityManager();
        List tipo = em.createQuery("select m from TipoLeito m where m.local.id = " + e.getId().toString()).getResultList();
        return tipo;
    }
    
//    public List getListMedico(Especialidade e) {
//    EntityManager em = getEntityManager();
//        List medicos = em.createQuery("select m from Medico m where m.especialidade.id = " + e.getId().toString()).getResultList();
//        return medicos;
//    }
    
}

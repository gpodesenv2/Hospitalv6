/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Manoela
 */
@Entity
public class Alta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataAlta;
    private String hora;    
    private String internacao;
    
    @ManyToOne
    private Leito leito;
    @ManyToOne
    private Localizacao local;
    @ManyToOne
    private MotivoAlta motivo;   
    @ManyToOne
    private Paciente paciente;
   @ManyToOne
    private Profissional profissional;
   @ManyToOne
    private Procedimento procedimento;
   @ManyToOne
    private CID cid;

    public Leito getLeito() {
        return leito;
    }

    public void setLeito(Leito leito) {
        this.leito = leito;
    }

    public Localizacao getLocal() {
        return local;
    }

    public void setLocal(Localizacao local) {
        this.local = local;
    }
    
    public MotivoAlta getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoAlta motivo) {
        this.motivo = motivo;
    }

    public String getInternacao() {
        return internacao;
    }

    public void setInternacao(String internacao) {
        this.internacao = internacao;
    }
       
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
   
    public Calendar getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Calendar data) {
        this.dataAlta = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    
    public Profissional getMedico() {
        return profissional;
    }

    public void setMedico(Profissional profissional) {
        this.profissional = profissional;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public CID getCid() {
        return cid;
    }

    public void setCid(CID cid) {
        this.cid = cid;
    }
      
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alta)) {
            return false;
        }
        Alta other = (Alta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Alta[ id=" + id + " ]";
    }
    
}

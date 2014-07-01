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
public class Prescricao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hora;
    private String periodoProced;
    private String periodoMedicamento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataPresc;
        
    
    @ManyToOne
    private Internacao internacao;
    @ManyToOne
    private Medicamento medicamento;
    @ManyToOne
    private Profissional medico;    
    @ManyToOne
    private Procedimento procedimento;

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPeriodoProced() {
        return periodoProced;
    }

    public void setPeriodoProced(String periodoProced) {
        this.periodoProced = periodoProced;
    }

    public String getPeriodoMedicamento() {
        return periodoMedicamento;
    }

    public void setPeriodoMedicamento(String periodoMedicamento) {
        this.periodoMedicamento = periodoMedicamento;
    }

    public Calendar getDataPresc() {
        return dataPresc;
    }

    public void setDataPresc(Calendar dataPresc) {
        this.dataPresc = dataPresc;
    }
   
    public Profissional getMedico() {
        return medico;
    }

    public void setMedico(Profissional medico) {
        this.medico = medico;
    }
    
    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
    
    public Internacao getInternacao() {
        return internacao;
    }

    public void setInternacao(Internacao internacao) {
        this.internacao = internacao;
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
        if (!(object instanceof Prescricao)) {
            return false;
        }
        Prescricao other = (Prescricao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Prescricao[ id=" + id + " ]";
    }
    
}

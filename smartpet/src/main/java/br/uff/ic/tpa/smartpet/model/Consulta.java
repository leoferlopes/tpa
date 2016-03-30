/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leonardo
 */
@Entity
@Table(name = "Consulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulta.recuperaListaDeConsultas", query = "SELECT c FROM Consulta c"),
    @NamedQuery(name = "Consulta.findByCodigoConsulta", query = "SELECT c FROM Consulta c WHERE c.codigoConsulta = :codigoConsulta"),
    @NamedQuery(name = "Consulta.findByDataHora", query = "SELECT c FROM Consulta c WHERE c.dataHora = :dataHora"),
    @NamedQuery(name = "Consulta.findByPreco", query = "SELECT c FROM Consulta c WHERE c.preco = :preco")})
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoConsulta")
    private Integer codigoConsulta;
    @Basic(optional = false)
    @Column(name = "dataHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @Column(name = "preco")
    private Integer preco;
    @Lob
    @Column(name = "receita")
    private String receita;
    @JoinColumn(name = "paciente", referencedColumnName = "idPaciente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Paciente paciente;
    @JoinColumn(name = "veterinario", referencedColumnName = "idVeterinario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Veterinario veterinario;

    public Consulta() {
    }

    public Consulta(Integer codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public Consulta(Integer codigoConsulta, Date dataHora) {
        this.codigoConsulta = codigoConsulta;
        this.dataHora = dataHora;
    }

    public Integer getCodigoConsulta() {
        return codigoConsulta;
    }

    public void setCodigoConsulta(Integer codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoConsulta != null ? codigoConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.codigoConsulta == null && other.codigoConsulta != null) || (this.codigoConsulta != null && !this.codigoConsulta.equals(other.codigoConsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.uff.ic.tpa.smartpet.model.Consulta[ codigoConsulta=" + codigoConsulta + " ]";
    }
    
}

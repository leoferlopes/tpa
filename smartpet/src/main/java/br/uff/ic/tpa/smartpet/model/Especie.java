/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leonardo
 */
@Entity
@Table(name = "Especie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especie.recuperaListaDeEspecies", query = "SELECT e FROM Especie e"),
    @NamedQuery(name = "Especie.findByIdEspecie", query = "SELECT e FROM Especie e WHERE e.idEspecie = :idEspecie"),
    @NamedQuery(name = "Especie.findByNome", query = "SELECT e FROM Especie e WHERE e.nome = :nome")})
public class Especie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEspecie")
    private Integer idEspecie;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(/*cascade = CascadeType.ALL, */mappedBy = "especie", fetch = FetchType.EAGER)
    private List<Paciente> pacienteList;

    public Especie() {
    }

    public Especie(Integer idEspecie) {
        this.idEspecie = idEspecie;
    }

    public Especie(Integer idEspecie, String nome) {
        this.idEspecie = idEspecie;
        this.nome = nome;
    }

    public Integer getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Integer idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecie != null ? idEspecie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especie)) {
            return false;
        }
        Especie other = (Especie) object;
        if ((this.idEspecie == null && other.idEspecie != null) || (this.idEspecie != null && !this.idEspecie.equals(other.idEspecie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.uff.ic.tpa.smartpet.model.Especie[ idEspecie=" + idEspecie + " ]";
    }
    
}

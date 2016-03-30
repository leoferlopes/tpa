/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leonardo
 */
@Entity
@Table(name = "Dono")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dono.findAll", query = "SELECT d FROM Dono d"),
    @NamedQuery(name = "Dono.findByIdDono", query = "SELECT d FROM Dono d WHERE d.idDono = :idDono"),
    @NamedQuery(name = "Dono.findByNome", query = "SELECT d FROM Dono d WHERE d.nome = :nome"),
    @NamedQuery(name = "Dono.findByTelefone", query = "SELECT d FROM Dono d WHERE d.telefone = :telefone"),
    @NamedQuery(name = "Dono.findByEmail", query = "SELECT d FROM Dono d WHERE d.email = :email"),
    @NamedQuery(name = "Dono.findByDataNascimento", query = "SELECT d FROM Dono d WHERE d.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Dono.findByEndereco", query = "SELECT d FROM Dono d WHERE d.endereco = :endereco")})
public class Dono implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDono")
    private Integer idDono;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "dataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @OneToMany(/*cascade = CascadeType.ALL, */mappedBy = "dono", fetch = FetchType.EAGER)
    private List<Paciente> pacienteList;

    public Dono() {
    }

    public Dono(Integer idDono) {
        this.idDono = idDono;
    }

    public Dono(Integer idDono, String nome, String telefone, String email, Date dataNascimento, String endereco) {
        this.idDono = idDono;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Integer getIdDono() {
        return idDono;
    }

    public void setIdDono(Integer idDono) {
        this.idDono = idDono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
        hash += (idDono != null ? idDono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dono)) {
            return false;
        }
        Dono other = (Dono) object;
        if ((this.idDono == null && other.idDono != null) || (this.idDono != null && !this.idDono.equals(other.idDono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.uff.ic.tpa.smartpet.model.Dono[ idDono=" + idDono + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author szotyi
 */
@Entity
@Table(name = "pdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pdata.findAll", query = "SELECT p FROM Pdata p")
    , @NamedQuery(name = "Pdata.findById", query = "SELECT p FROM Pdata p WHERE p.id = :id")
    , @NamedQuery(name = "Pdata.findByTaxNum", query = "SELECT p FROM Pdata p WHERE p.taxNum = :taxNum")
    , @NamedQuery(name = "Pdata.findByIdentificationNum", query = "SELECT p FROM Pdata p WHERE p.identificationNum = :identificationNum")
    , @NamedQuery(name = "Pdata.findByInsurranceNum", query = "SELECT p FROM Pdata p WHERE p.insurranceNum = :insurranceNum")
    , @NamedQuery(name = "Pdata.findByPersonalNum", query = "SELECT p FROM Pdata p WHERE p.personalNum = :personalNum")
    , @NamedQuery(name = "Pdata.findByOther", query = "SELECT p FROM Pdata p WHERE p.other = :other")
    , @NamedQuery(name = "Pdata.findByOtherType", query = "SELECT p FROM Pdata p WHERE p.otherType = :otherType")})
public class Pdata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "tax_num")
    private String taxNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "identification_num")
    private String identificationNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "insurrance_num")
    private String insurranceNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "personal_num")
    private String personalNum;
    @Size(max = 45)
    @Column(name = "other")
    private String other;
    @Size(max = 45)
    @Column(name = "other_type")
    private String otherType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pdata", fetch = FetchType.LAZY)
    private List<Employee> employeeList = new ArrayList<>();

    public Pdata() {
    }

    public Pdata(Integer id) {
        this.id = id;
    }

    public Pdata(Integer id, String taxNum, String identificationNum, String insurranceNum, String personalNum, String other, String otherType) {
        this.id = id;
        this.taxNum = taxNum;
        this.identificationNum = identificationNum;
        this.insurranceNum = insurranceNum;
        this.personalNum = personalNum;
        this.other = other;
        this.otherType = otherType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
    }

    public String getIdentificationNum() {
        return identificationNum;
    }

    public void setIdentificationNum(String identificationNum) {
        this.identificationNum = identificationNum;
    }

    public String getInsurranceNum() {
        return insurranceNum;
    }

    public void setInsurranceNum(String insurranceNum) {
        this.insurranceNum = insurranceNum;
    }

    public String getPersonalNum() {
        return personalNum;
    }

    public void setPersonalNum(String personalNum) {
        this.personalNum = personalNum;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
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
        if (!(object instanceof Pdata)) {
            return false;
        }
        Pdata other = (Pdata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.evo.hradmin.model.Pdata[ id=" + id + " ]";
    }
    
}

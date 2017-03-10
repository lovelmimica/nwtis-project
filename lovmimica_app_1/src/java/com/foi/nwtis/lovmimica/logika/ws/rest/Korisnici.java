/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.ws.rest;

import com.foi.nwtis.lovmimica.datatypes.User;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lovelmimica
 */
@Entity
@Table(name = "korisnici")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnici.findAll", query = "SELECT k FROM Korisnici k")
    , @NamedQuery(name = "Korisnici.findById", query = "SELECT k FROM Korisnici k WHERE k.id = :id")
    , @NamedQuery(name = "Korisnici.findByNaziv", query = "SELECT k FROM Korisnici k WHERE k.naziv = :naziv")
    , @NamedQuery(name = "Korisnici.findByLozinka", query = "SELECT k FROM Korisnici k WHERE k.lozinka = :lozinka")
    , @NamedQuery(name = "Korisnici.findByKategorija", query = "SELECT k FROM Korisnici k WHERE k.kategorija = :kategorija")
    , @NamedQuery(name = "Korisnici.findByAdmin", query = "SELECT k FROM Korisnici k WHERE k.admin = :admin")})
public class Korisnici implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "naziv")
    private String naziv;
    @Size(max = 255)
    @Column(name = "lozinka")
    private String lozinka;
    @Column(name = "kategorija")
    private Integer kategorija;
    @Column(name = "admin")
    private Boolean admin;
    @OneToMany(mappedBy = "korisnik")
    private Collection<Adrese> adreseCollection;

    public Korisnici() {
    }
    public Korisnici(User user){
        this.id = user.getId();
        this.naziv = user.getName();
        this.lozinka = user.getPassword();
        this.kategorija = user.getCategory();
        this.admin = user.isAdmin();
        
    }

    public Korisnici(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Integer getKategorija() {
        return kategorija;
    }

    public void setKategorija(Integer kategorija) {
        this.kategorija = kategorija;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @XmlTransient
    public Collection<Adrese> getAdreseCollection() {
        return adreseCollection;
    }

    public void setAdreseCollection(Collection<Adrese> adreseCollection) {
        this.adreseCollection = adreseCollection;
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
        if (!(object instanceof Korisnici)) {
            return false;
        }
        Korisnici other = (Korisnici) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foi.nwtis.lovmimica.logika.ws.rest.Korisnici[ id=" + id + " ]";
    }
    
}

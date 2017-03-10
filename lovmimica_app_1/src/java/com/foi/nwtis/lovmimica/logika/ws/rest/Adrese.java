/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.ws.rest;

import com.foi.nwtis.lovmimica.bp.PresentUsers;
import com.foi.nwtis.lovmimica.datatypes.Address;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "adrese")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adrese.findAll", query = "SELECT a FROM Adrese a")
    , @NamedQuery(name = "Adrese.findById", query = "SELECT a FROM Adrese a WHERE a.id = :id")
    , @NamedQuery(name = "Adrese.findByNaziv", query = "SELECT a FROM Adrese a WHERE a.naziv = :naziv")
    , @NamedQuery(name = "Adrese.findBySirina", query = "SELECT a FROM Adrese a WHERE a.sirina = :sirina")
    , @NamedQuery(name = "Adrese.findByDuzina", query = "SELECT a FROM Adrese a WHERE a.duzina = :duzina")})
public class Adrese implements Serializable {

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
    @Column(name = "sirina")
    private String sirina;
    @Size(max = 255)
    @Column(name = "duzina")
    private String duzina;
    @JoinColumn(name = "korisnik", referencedColumnName = "id")
    @ManyToOne
    private Korisnici korisnik;
    @OneToMany(mappedBy = "adresa")
    private Collection<MeteoPodaci> meteoPodaciCollection;

    public Adrese() {
    }
    public Adrese(Address address){
        this.id = address.getId();
        this.naziv = address.getName();
        this.sirina = address.getLatitude();
        this.duzina = address.getLongitude();
    }

    public Adrese(Integer id) {
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

    public String getSirina() {
        return sirina;
    }

    public void setSirina(String sirina) {
        this.sirina = sirina;
    }

    public String getDuzina() {
        return duzina;
    }

    public void setDuzina(String duzina) {
        this.duzina = duzina;
    }

    public Korisnici getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnici korisnik) {
        this.korisnik = korisnik;
    }

    @XmlTransient
    public Collection<MeteoPodaci> getMeteoPodaciCollection() {
        return meteoPodaciCollection;
    }

    public void setMeteoPodaciCollection(Collection<MeteoPodaci> meteoPodaciCollection) {
        this.meteoPodaciCollection = meteoPodaciCollection;
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
        if (!(object instanceof Adrese)) {
            return false;
        }
        Adrese other = (Adrese) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foi.nwtis.lovmimica.logika.ws.rest.Adrese[ id=" + id + " ]";
    }
    
}

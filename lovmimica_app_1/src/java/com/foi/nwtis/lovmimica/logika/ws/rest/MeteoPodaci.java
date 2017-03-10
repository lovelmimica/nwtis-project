/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.ws.rest;

import com.foi.nwtis.lovmimica.bp.PresentAddresses;
import com.foi.nwtis.lovmimica.datatypes.Address;
import com.foi.nwtis.lovmimica.datatypes.MeteoData;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lovelmimica
 */
@Entity
@Table(name = "meteo_podaci")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeteoPodaci.findAll", query = "SELECT m FROM MeteoPodaci m")
    , @NamedQuery(name = "MeteoPodaci.findById", query = "SELECT m FROM MeteoPodaci m WHERE m.id = :id")
    , @NamedQuery(name = "MeteoPodaci.findByTemperatura", query = "SELECT m FROM MeteoPodaci m WHERE m.temperatura = :temperatura")
    , @NamedQuery(name = "MeteoPodaci.findByTlak", query = "SELECT m FROM MeteoPodaci m WHERE m.tlak = :tlak")
    , @NamedQuery(name = "MeteoPodaci.findByVlaga", query = "SELECT m FROM MeteoPodaci m WHERE m.vlaga = :vlaga")
    , @NamedQuery(name = "MeteoPodaci.findByVjetar", query = "SELECT m FROM MeteoPodaci m WHERE m.vjetar = :vjetar")
    , @NamedQuery(name = "MeteoPodaci.findByOborine", query = "SELECT m FROM MeteoPodaci m WHERE m.oborine = :oborine")
    , @NamedQuery(name = "MeteoPodaci.findByVrijemeAzuriranja", query = "SELECT m FROM MeteoPodaci m WHERE m.vrijemeAzuriranja = :vrijemeAzuriranja")
    , @NamedQuery(name = "MeteoPodaci.findByVrijemePreuzimanja", query = "SELECT m FROM MeteoPodaci m WHERE m.vrijemePreuzimanja = :vrijemePreuzimanja")
    , @NamedQuery(name = "MeteoPodaci.findByPrognoza", query = "SELECT m FROM MeteoPodaci m WHERE m.prognoza = :prognoza")
    , @NamedQuery(name = "MeteoPodaci.findByVrijemePrognoze", query = "SELECT m FROM MeteoPodaci m WHERE m.vrijemePrognoze = :vrijemePrognoze")})
public class MeteoPodaci implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "temperatura")
    private Float temperatura;
    @Column(name = "tlak")
    private Float tlak;
    @Column(name = "vlaga")
    private Float vlaga;
    @Column(name = "vjetar")
    private Float vjetar;
    @Column(name = "oborine")
    private Float oborine;
    @Column(name = "vrijeme_azuriranja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrijemeAzuriranja;
    @Column(name = "vrijeme_preuzimanja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrijemePreuzimanja;
    @Column(name = "prognoza")
    private Boolean prognoza;
    @Column(name = "vrijeme_prognoze")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrijemePrognoze;
    @JoinColumn(name = "adresa", referencedColumnName = "id")
    @ManyToOne
    private Adrese adresa;

    public MeteoPodaci() {
    }
    public MeteoPodaci(MeteoData md){
        this.id = md.getId();
        this.oborine = md.getClouds();
        this.vlaga = md.getHumidity();
        this.vjetar = md.getWind();
        this.tlak = md.getPressure();
        this.temperatura = md.getTemperature();
        this.vrijemePreuzimanja = md.getDownloadTime();
        this.vrijemeAzuriranja = md.getUpdateTime();
        this.prognoza = md.isForecast();
        this.vrijemePrognoze = md.getForecastTime();
        
        //dodjeljivanje odgovarajuce adrese
        Address addressTemp = PresentAddresses.getById(md.getAddressId());
        this.adresa = new Adrese(addressTemp);
    }

    public MeteoPodaci(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Float temperatura) {
        this.temperatura = temperatura;
    }

    public Float getTlak() {
        return tlak;
    }

    public void setTlak(Float tlak) {
        this.tlak = tlak;
    }

    public Float getVlaga() {
        return vlaga;
    }

    public void setVlaga(Float vlaga) {
        this.vlaga = vlaga;
    }

    public Float getVjetar() {
        return vjetar;
    }

    public void setVjetar(Float vjetar) {
        this.vjetar = vjetar;
    }

    public Float getOborine() {
        return oborine;
    }

    public void setOborine(Float oborine) {
        this.oborine = oborine;
    }

    public Date getVrijemeAzuriranja() {
        return vrijemeAzuriranja;
    }

    public void setVrijemeAzuriranja(Date vrijemeAzuriranja) {
        this.vrijemeAzuriranja = vrijemeAzuriranja;
    }

    public Date getVrijemePreuzimanja() {
        return vrijemePreuzimanja;
    }

    public void setVrijemePreuzimanja(Date vrijemePreuzimanja) {
        this.vrijemePreuzimanja = vrijemePreuzimanja;
    }

    public Boolean getPrognoza() {
        return prognoza;
    }

    public void setPrognoza(Boolean prognoza) {
        this.prognoza = prognoza;
    }

    public Date getVrijemePrognoze() {
        return vrijemePrognoze;
    }

    public void setVrijemePrognoze(Date vrijemePrognoze) {
        this.vrijemePrognoze = vrijemePrognoze;
    }

    public Adrese getAdresa() {
        return adresa;
    }

    public void setAdresa(Adrese adresa) {
        this.adresa = adresa;
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
        if (!(object instanceof MeteoPodaci)) {
            return false;
        }
        MeteoPodaci other = (MeteoPodaci) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foi.nwtis.lovmimica.logika.ws.rest.MeteoPodaci[ id=" + id + " ]";
    }
    
}

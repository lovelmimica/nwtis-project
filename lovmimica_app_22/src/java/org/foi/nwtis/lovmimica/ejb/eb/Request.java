/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.ejb.eb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lovelmimica
 */
@Entity
@Table(name = "REQUEST_")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
    , @NamedQuery(name = "Request.findByContent", query = "SELECT r FROM Request r WHERE r.content = :content")
    , @NamedQuery(name = "Request.findByUsername", query = "SELECT r FROM Request r WHERE r.username = :username")
    , @NamedQuery(name = "Request.findByStart", query = "SELECT r FROM Request r WHERE r.start = :start")
    , @NamedQuery(name = "Request.findByFinish", query = "SELECT r FROM Request r WHERE r.finish = :finish")
    , @NamedQuery(name = "Request.findById", query = "SELECT r FROM Request r WHERE r.id = :id")})
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "CONTENT")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START")
    @Temporal(TemporalType.DATE)
    private Date start;
    @Column(name = "FINISH")
    @Temporal(TemporalType.DATE)
    private Date finish;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;

    public Request() {
    }

    public Request(Integer id) {
        this.id = id;
    }

    public Request(Integer id, String content, String username, Date start) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.start = start;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.foi.nwtis.lovmimica.ejb.eb.Request[ id=" + id + " ]";
    }
    
}

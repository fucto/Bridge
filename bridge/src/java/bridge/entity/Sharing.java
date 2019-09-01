/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
 * @author Jasmi
 */
@Entity
@Table(name = "sharing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sharing.findAll", query = "SELECT s FROM Sharing s")
    , @NamedQuery(name = "Sharing.findBySId", query = "SELECT s FROM Sharing s WHERE s.sId = :sId")
    , @NamedQuery(name = "Sharing.findBySTitle", query = "SELECT s FROM Sharing s WHERE s.sTitle = :sTitle")
    , @NamedQuery(name = "Sharing.findBySDate", query = "SELECT s FROM Sharing s WHERE s.sDate = :sDate")
    , @NamedQuery(name = "Sharing.findBySUp", query = "SELECT s FROM Sharing s WHERE s.sUp = :sUp")})
public class Sharing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "s_id")
    private Integer sId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "s_title")
    private String sTitle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "s_date")
    @Temporal(TemporalType.DATE)
    private Date sDate;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "s_content")
    private String sContent;
    @Column(name = "s_up")
    private Integer sUp;
    @JoinColumn(name = "user_u_id", referencedColumnName = "u_id")
    @ManyToOne(optional = false)
    private User userUId;

    public Sharing() {
    }

    public Sharing(Integer sId) {
        this.sId = sId;
    }

    public Sharing(Integer sId, String sTitle, Date sDate, String sContent) {
        this.sId = sId;
        this.sTitle = sTitle;
        this.sDate = sDate;
        this.sContent = sContent;
    }

    public Integer getSId() {
        return sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }

    public String getSTitle() {
        return sTitle;
    }

    public void setSTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public Date getSDate() {
        return sDate;
    }

    public void setSDate(Date sDate) {
        this.sDate = sDate;
    }

    public String getSContent() {
        return sContent;
    }

    public void setSContent(String sContent) {
        this.sContent = sContent;
    }

    public Integer getSUp() {
        return sUp;
    }

    public void setSUp(Integer sUp) {
        this.sUp = sUp;
    }

    public User getUserUId() {
        return userUId;
    }

    public void setUserUId(User userUId) {
        this.userUId = userUId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sId != null ? sId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sharing)) {
            return false;
        }
        Sharing other = (Sharing) object;
        if ((this.sId == null && other.sId != null) || (this.sId != null && !this.sId.equals(other.sId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bridge.entity.Sharing[ sId=" + sId + " ]";
    }
    
}

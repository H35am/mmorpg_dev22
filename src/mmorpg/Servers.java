/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmorpg;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author heszar
 */
@Entity
@Table(name = "servers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servers.findAll", query = "SELECT s FROM Servers s"),
    @NamedQuery(name = "Servers.findByAddress", query = "SELECT s FROM Servers s WHERE s.address = :address"),
    @NamedQuery(name = "Servers.findByName", query = "SELECT s FROM Servers s WHERE s.name = :name"),
    @NamedQuery(name = "Servers.findByLocation", query = "SELECT s FROM Servers s WHERE s.location = :location"),
    @NamedQuery(name = "Servers.findByMaxUsers", query = "SELECT s FROM Servers s WHERE s.maxUsers = :maxUsers"),
    @NamedQuery(name = "Servers.findByConnectedUsers", query = "SELECT s FROM Servers s WHERE s.connectedUsers = :connectedUsers")})
public class Servers implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @Column(name = "max_users")
    private int maxUsers;
    @Basic(optional = false)
    @Column(name = "connected_users")
    private int connectedUsers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private Collection<Users> usersCollection;

    public Servers() {
    }

    public Servers(String address) {
        this.address = address;
    }

    public Servers(String address, String name, String location, int maxUsers, int connectedUsers) {
        this.address = address;
        this.name = name;
        this.location = location;
        this.maxUsers = maxUsers;
        this.connectedUsers = connectedUsers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldAddress = this.address;
        this.address = address;
        changeSupport.firePropertyChange("address", oldAddress, address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        String oldLocation = this.location;
        this.location = location;
        changeSupport.firePropertyChange("location", oldLocation, location);
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        int oldMaxUsers = this.maxUsers;
        this.maxUsers = maxUsers;
        changeSupport.firePropertyChange("maxUsers", oldMaxUsers, maxUsers);
    }

    public int getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(int connectedUsers) {
        int oldConnectedUsers = this.connectedUsers;
        this.connectedUsers = connectedUsers;
        changeSupport.firePropertyChange("connectedUsers", oldConnectedUsers, connectedUsers);
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (address != null ? address.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servers)) {
            return false;
        }
        Servers other = (Servers) object;
        if ((this.address == null && other.address != null) || (this.address != null && !this.address.equals(other.address))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mmorpg.Servers[ address=" + address + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

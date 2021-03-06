/*
 * Copyright (C) 2017 Brandl Valentin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.othr.tidli.entity;

import com.lambdaworks.crypto.SCryptUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.othr.tidli.util.Role;
import org.othr.tidli.util.Static;

/**
 *
 * @author Brandl Valentin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
})
public class Account extends Id {

    private static final long serialVersionUID = -1876443309866494657L;
    private static final Role ROLE = Role.User;
    public static Account createUser(final String email, final String name,
            final String city, String street, String number, Integer zip, final String password) {
        return new Account(
                email, password, name, city, street, number, zip
        );
    }

    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date creationTime = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    @NotNull
    private boolean activated = true;
    private Integer zipCode;
    private String city;
    private String street;
    private String number;


    /**
     * Superconstructor for all account entities.
     * The password will be hashed using scrypt
     * @param email
     * @param password
     * @param name
     * @param city
     * @param street
     * @param number
     * @param zip
     */
    public Account(final String email, final String password, final String name,
            final String city, String street, String number, Integer zip) {
        super();
        this.email = email;
        this.password = SCryptUtil.scrypt(password, Static.SCRYPT_CPU_COST, Static.SCRYPT_MEM_COST, Static.SCRYPT_PARALLELIZATION);
        this.name = name;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zip;
    }

    public Account() {}

    public boolean isActivated() {
        return this.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
//        this.password = password;
        throw new UnsupportedOperationException("Password should not be set directly");
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getCreationTime() {
        return (Date)this.creationTime.clone();
    }

    public void setCreationTime(final Date creationTime) {
        this.creationTime = (Date)creationTime.clone();
    }

    public Date getLastUpdated() {
        return (Date)this.lastUpdated.clone();
    }

    public void setLastUpdated(final Date lastUpdated) {
        this.lastUpdated = (Date)lastUpdated.clone();
    }

    public boolean changePassword(final String old, final String newPw) {
        if (this.checkPassword(old)) {
            this.hashAndSetPassword(newPw);
            return true;
        } else {
            return false;
        }
    }

    public void hashAndSetPassword(final String pw) {
        this.password = SCryptUtil.scrypt(pw, Static.SCRYPT_CPU_COST, Static.SCRYPT_MEM_COST, Static.SCRYPT_PARALLELIZATION);
    }

    public boolean checkPassword(final String pw) {
        return SCryptUtil.check(pw, this.password);
    }

    public Role getRole() {
        return ROLE;
    }

    public Integer getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(final Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}

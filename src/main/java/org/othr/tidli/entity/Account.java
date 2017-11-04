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
import java.io.Serializable;
import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.othr.tidli.util.Static;

/**
 *
 * @author Brandl Valentin
 */
@MappedSuperclass
public abstract class Account extends Id implements Serializable {

    @Transient
    private static final long serialVersionUID = -1876443309866494657L;

    private String email;
    private String password;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    /**
     * Superconstructor for all account entities.
     * The password will be hashed using scrypt
     * @param email
     * @param password
     * @param name
     */
    public Account(final String email, final String password, final String name) {
        super();
        this.email = email;
        this.password = SCryptUtil.scrypt(password, Static.SCRYPT_CPU_COST, Static.SCRYPT_MEM_COST, Static.SCRYPT_PARALLELIZATION);
    }

    public Account() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getCreationTime() {
        return (Date)creationTime.clone();
    }

    public void setCreationTime(final Date creationTime) {
        this.creationTime = (Date)creationTime.clone();
    }

    public Date getLastUpdated() {
        return (Date)lastUpdated.clone();
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

}

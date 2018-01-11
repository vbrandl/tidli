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

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 *
 * @author Brandl Valentin
 */
@Embeddable
public class Address implements Serializable {
    
    @Transient
    private static final long serialVersionUID = -5335746519732869566L;

    private Integer zipCode;
    private String city;
    private String street;
    private String number;

    public Address(final Integer zipCode, final String city,
            final String street, final String number) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Address() {}

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

    @Override
    public String toString() {
        return String.format("%s %s\n%d %s", this.street,
                this.number, this.zipCode, this.city);
    }
    
}

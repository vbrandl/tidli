/*
 * Copyright (C) 2018 Brandl Valentin
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
package org.othr.tidli.dto;

import java.io.Serializable;
import org.othr.tidli.entity.Shop;

/**
 * @author Brandl Valentin
 */
public class ShopDTO implements Serializable {
    
    private static final long serialVersionUID = 8067666177472185397L;

    private long id;
    private String email;
    private String name;
    private String city;
    private String street;
    private String number;
    private Integer zipCode;

    public ShopDTO() {}

    public ShopDTO(final long id, final String email, final String name, final String city, final String street, final String number, final Integer zipCode) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
    }
    public ShopDTO(final Shop shp) {
        this.id = shp.getId();
        this.email = shp.getEmail();
        this.name = shp.getName();
        this.city = shp.getCity();
        this.street = shp.getStreet();
        this.number = shp.getNumber();
        this.zipCode = shp.getZipCode();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

}

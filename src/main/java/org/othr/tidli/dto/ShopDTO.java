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
 * Immutable DTO for shops.
 * This class is truly immutable. Calling setters won't mutate the current object but
 * create a new object with the new attribute.
 *
 * @author Brandl Valentin
 */
public final class ShopDTO implements Serializable {
    
    private static final long serialVersionUID = 8067666177472185397L;

    private final long id;
    private final String email;
    private final String name;
    private final String city;
    private final String street;
    private final String number;
    private final Integer zipCode;

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

    public ShopDTO setId(final long id) {
        return new ShopDTO(id, this.email, this.name, this.city, this.street, this.number, this.zipCode);
    }

    public String getEmail() {
        return this.email;
    }

    public ShopDTO setEmail(final String email) {
        return new ShopDTO(this.id, email, this.name, this.city, this.street, this.number, this.zipCode);
    }

    public String getName() {
        return this.name;
    }

    public ShopDTO setName(final String name) {
        return new ShopDTO(this.id, this.email, name, this.city, this.street, this.number, this.zipCode);
    }

    public String getCity() {
        return this.city;
    }

    public ShopDTO setCity(final String city) {
        return new ShopDTO(this.id, this.email, this.name, city, this.street, this.number, this.zipCode);
    }

    public String getStreet() {
        return this.street;
    }

    public ShopDTO setStreet(final String street) {
        return new ShopDTO(this.id, this.email, this.name, this.city, street, this.number, this.zipCode);
    }

    public String getNumber() {
        return this.number;
    }

    public ShopDTO setNumber(final String number) {
        return new ShopDTO(this.id, this.email, this.name, this.city, this.street, number, this.zipCode);
    }

    public Integer getZipCode() {
        return this.zipCode;
    }

    public ShopDTO setZipCode(final Integer zipCode) {
        return new ShopDTO(this.id, this.email, this.name, this.city, this.street, this.number, zipCode);
    }

}

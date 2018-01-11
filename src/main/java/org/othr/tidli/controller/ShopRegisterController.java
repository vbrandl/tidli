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
package org.othr.tidli.controller;

import java.util.Objects;
import java.util.Optional;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.othr.tidli.entity.Address;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.service.RegisterService;
import org.othr.tidli.service.ShopServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
@ViewScoped
public class ShopRegisterController extends RegisterController<Shop> {

    private static final long serialVersionUID = 5982363413410764051L;

    private String email, pw1, pw2, name, desc, city, street, number;
    private Integer zip = 0;

    @Inject
    private ShopServiceIF srs;

    @Override
    @SuppressWarnings("unchecked")
    protected RegisterService<Shop> getService() {
        return (RegisterService<Shop>)this.srs;
    }

    @Override
    protected Optional<Shop> createEntity() {
        if (Objects.equals(this.pw1, this.pw2)) {
            final Address addr = new Address(
                    this.zip, this.city, this.street, this.number);
            return Optional.of(new Shop(this.email, this.name, this.pw1, addr, this.desc));
        } else {
            this.sendError("Register", "Passwörter stimmen nicht überein");
            return Optional.empty();
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw1() {
        return this.pw1;
    }

    public void setPw1(String pw1) {
        this.pw1 = pw1;
    }

    public String getPw2() {
        return this.pw2;
    }

    public void setPw2(String pw2) {
        this.pw2 = pw2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public int getZip() {
        return this.zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
    
}

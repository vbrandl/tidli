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
public class ShopRegisterController extends RegisterController<Shop> {

    private String email, pw1, pw2, name, desc, city, street, number;
    private Integer zip = 0;

    @Inject
    private ShopServiceIF srs;

    @Override
    @SuppressWarnings("unchecked")
    protected RegisterService<Shop> getService() {
        return (RegisterService<Shop>)srs;
    }

    @Override
    protected Optional<Shop> createEntity() {
        if (Objects.equals(pw1, pw2)) {
            final Address addr = new Address(
                    zip, city, street, number
            );
            return Optional.of(new Shop(email, name, pw1, addr, desc));
        } else {
            return Optional.empty();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw1() {
        return pw1;
    }

    public void setPw1(String pw1) {
        this.pw1 = pw1;
    }

    public String getPw2() {
        return pw2;
    }

    public void setPw2(String pw2) {
        this.pw2 = pw2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
    
}

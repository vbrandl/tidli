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
import org.othr.tidli.entity.Account;
import org.othr.tidli.service.RegisterService;
import org.othr.tidli.service.UserServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class UserRegisterController extends RegisterController<Account> {

    private static final long serialVersionUID = -9069340813032081058L;

    private String email, name, pw1, pw2, city, street, number;
    private Integer zip;

    @Inject
    private UserServiceIF us;

    @Override
    @SuppressWarnings("unchecked")
    protected RegisterService<Account> getService() {
        return (RegisterService<Account>)this.us;
    }

    @Override
    protected Optional<Account> createEntity() {
        if (Objects.equals(this.pw1, this.pw2)) {
            return Optional.of(
                    Account.createUser(this.email, this.name, this.city, this.street, this.number, this.zip, this.pw1)
            );
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getZip() {
        return this.zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

}

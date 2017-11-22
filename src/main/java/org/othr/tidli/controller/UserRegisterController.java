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
import org.othr.tidli.entity.Address;
import org.othr.tidli.service.RegisterService;
import org.othr.tidli.service.UserServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class UserRegisterController extends RegisterController<Account> {

    private String email, name, pw1, pw2, city, street, number;

    @Inject
    private UserServiceIF us;

    @Override
    protected RegisterService<Account> getService() {
        return (RegisterService<Account>)us;
    }

    @Override
    protected Optional<Account> createEntity() {
        if (Objects.equals(pw1, pw2)) {
            final Address addr = new Address(0, city, street, number);
            return Optional.of(
                    Account.createUser(email, name, addr, pw1)
            );
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}

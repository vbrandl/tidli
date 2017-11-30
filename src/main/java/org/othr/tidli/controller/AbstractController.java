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

import java.util.Optional;
import javax.inject.Inject;
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Administrator;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.service.LoginServiceIF;
import org.othr.tidli.util.Role;

/**
 *
 * @author Brandl Valentin
 */
public abstract class AbstractController {

    @Inject
    private LoginServiceIF ls;

    public Optional<Account> getUser() {
        return ls.getAccount();
    }

    public Optional<Shop> getShop() {
        return ls.getShop();
    }

    public Optional<Administrator> getAdmin() {
        return ls.getAdmin();
    }

    public boolean isRole(final Role r) {
        return getUser().filter(a -> a.getRole() == r).isPresent();
    }

    public boolean isUserRole() {
        return isRole(Role.User);
    }

    public boolean isShopRole() {
        return isRole(Role.Shop);
    }

    public boolean isAdminRole() {
        return isRole(Role.Administrator);
    }

    public boolean isLoggedIn() {
        return ls.getAccount().isPresent();
    }

    public Optional<Role> getRole() {
        return getUser().map(u -> u.getRole());
    }
    
}

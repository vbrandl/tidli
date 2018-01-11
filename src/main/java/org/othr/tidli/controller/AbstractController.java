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

import java.io.Serializable;
import java.util.Optional;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
public abstract class AbstractController implements Serializable {

    private static final long serialVersionUID = 9222962181062753050L;

    @Inject
    private LoginServiceIF ls;

    public Optional<Account> getUser() {
        return this.ls.getAccount();
    }

    public Optional<Shop> getShop() {
        return this.ls.getShop();
    }

    public Optional<Administrator> getAdmin() {
        return this.ls.getAdmin();
    }

    public boolean isRole(final Role r) {
        return this.getUser().filter(a -> a.getRole() == r).isPresent();
    }

    public boolean isUserRole() {
        return this.isRole(Role.User);
    }

    public boolean isShopRole() {
        return this.isRole(Role.Shop);
    }

    public boolean isAdminRole() {
        return this.isRole(Role.Administrator);
    }

    public boolean isLoggedIn() {
        return this.ls.getAccount().isPresent();
    }

    public Optional<Role> getRole() {
        return this.getUser().map(u -> u.getRole());
    }

    public void sendError(final String title, final String message) {
        this.sendMessage(FacesMessage.SEVERITY_ERROR, title, message);
    }

    public void sendInfo(final String title, final String message) {
        this.sendMessage(FacesMessage.SEVERITY_INFO, title, message);
    }

    private void sendMessage(final FacesMessage.Severity severity, final String title, final String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, msg));
    }
    
}

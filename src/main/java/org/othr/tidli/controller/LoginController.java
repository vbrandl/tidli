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

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.othr.tidli.service.LoginServiceIF;
import org.othr.tidli.util.LoginStatus;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
@ViewScoped
public class LoginController extends AbstractController {

    private static final long serialVersionUID = -635336017048699103L;
    @Inject
    private LoginServiceIF ls;
    
    private String email, pw;

    public String login() {
        final LoginStatus login = this.ls.login(this.email, this.pw);
        login.getMessage().ifPresent(m -> this.sendError("Login", m));
        return login.getTarget();
    }

    public void logout() {
        this.ls.logout();
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return this.pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}

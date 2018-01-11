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
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.entity.Account;
import org.othr.tidli.service.LoginServiceIF;
import org.othr.tidli.service.UserServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
@SessionScoped
public class UserDetailController extends AbstractController {
    private static final long serialVersionUID = -2292619186583344544L;
    private static boolean isNullOrEmpty(final String str) {
        return null == str || str.isEmpty();
    }

    @Inject
    private LoginServiceIF ls;
    @Inject 
    private UserServiceIF us;
    private Optional<Account> user;
    private String pw1, pw2, pwOld;


    @PostConstruct
    private void prepareData() {
        this.user = this.ls.getAccount();
    }


    public void updateUser() {
        this.user = this.user.map(u -> {
            if (!isNullOrEmpty(this.pw1) || !isNullOrEmpty(this.pw2) || !isNullOrEmpty(this.pwOld)) {
                if (this.pw1.equals(this.pw2) && u.checkPassword(this.pw1)) {
                    u.changePassword(this.pwOld, this.pw1);
                } else {
                    return u;
                }
            }
            return this.us.updateUser(u);
        });
    }

    public Account getUnwrapedUser() {
        return this.user.get();
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

    public String getPwOld() {
        return this.pwOld;
    }

    public void setPwOld(String pwOld) {
        this.pwOld = pwOld;
    }
    
}

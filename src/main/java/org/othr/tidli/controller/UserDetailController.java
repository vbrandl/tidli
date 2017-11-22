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

import javax.annotation.PostConstruct;
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
public class UserDetailController extends AbstractController {
    @Inject
    private LoginServiceIF ls;
    @Inject 
    private UserServiceIF us;
    private Account user;
    private String pw1, pw2, pwOld;

    @PostConstruct
    private void prepareData() {
        user = ls.getAccount().get();
    }

    private static boolean isNullOrEmpty(final String str) {
        return null == str || str.isEmpty();
    }

    public void updateUser() {
        if (!isNullOrEmpty(pw1) || !isNullOrEmpty(pw2) || !isNullOrEmpty(pwOld)) {
            if (pw1.equals(pw2) && user.checkPassword(pw1)) {
                this.user.changePassword(pwOld, pw1);
            } else {
                return;
            }
        }
        this.user = us.updateUser(user);
    }

    public Account getUnwrapedUser() {
        return user;
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

    public String getPwOld() {
        return pwOld;
    }

    public void setPwOld(String pwOld) {
        this.pwOld = pwOld;
    }
    
}

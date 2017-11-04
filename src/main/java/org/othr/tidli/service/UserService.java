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
package org.othr.tidli.service;

import java.util.Objects;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import org.othr.tidli.entity.User;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
@RequestScoped
@WebService
public class UserService extends AbstractService {
    private User newUser = new User();
    private String pw1, pw2;

    @Transactional
    public User registerUser(final User user) {
        getEm().persist(user);
        return user;
    }

    @Transactional
    public void register() {
        if (Objects.equals(pw1, pw2)) {
            newUser.hashAndSetPassword(pw1);
            getEm().persist(newUser);
            newUser = new User();
        }
    }

	public User findUser(final long id) {
		return getEm().find(User.class, id);
	}

    public User getUser() {
        return newUser;
    }

    public void setUser(final User newUser) {
        this.newUser = newUser;
    }

    public String getPw1() {
        return pw1;
    }

    public void setPw1(final String pw) {
        this.pw1 = pw;
    }

    public String getPw2() {
        return pw2;
    }

    public void setPw2(final String pw) {
        this.pw2 = pw;
    }

}

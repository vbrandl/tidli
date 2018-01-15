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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.entity.Account;
import org.othr.tidli.service.UserServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class AdminUserController extends AbstractController {

    private static final long serialVersionUID = 3565920421845368710L;
    
    @Inject
    private UserServiceIF us;
    private Set<Account> users;

    @PostConstruct
    private void prepareData() {
        if (this.isAdminRole()) {
            this.users = new HashSet<>(this.us.getAllUsers());
        }
    }

    public void toogleActivationState(final Account acc) {
        if (this.isAdminRole()) {
            if (this.users.remove(acc)) {
                final Account updated = this.us.toogleActivationState(acc);
                this.users.add(updated);
            }
        }
    }

    public void deleteShop(final Account acc) {
        if (this.isAdminRole()) {
            if (this.users.remove(acc)) {
                this.us.deleteUser(acc);
            }
        }
    }

    public Set<Account> getUsers() {
        if (this.isAdminRole()) {
            return Collections.unmodifiableSet(this.users);
        } else {
            return Collections.emptySet();
        }
    }
}

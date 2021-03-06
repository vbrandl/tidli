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

import java.util.Optional;
import javax.enterprise.context.SessionScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Administrator;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.util.LoginStatus;
import org.othr.tidli.util.Role;

/**
 *
 * @author Brandl Valentin
 */
@SessionScoped
public class LoginService extends AbstractService<Account> implements LoginServiceIF {

    private static final long serialVersionUID = 8818972444125627074L;

    private Optional<Account> user = Optional.empty();

    private Optional<Account> findByEmail(final String email) {
        final TypedQuery<Account> query = this.getEm().createNamedQuery("Account.findByEmail", Account.class);
        query.setParameter("email", email);
        return singleResultToOptional(query);
    }

    @Override
    public LoginStatus login(final String email, final String pw) {
        final Optional<Account> accOpt = this.findByEmail(email);
        final LoginStatus result = accOpt
                .map(a -> {
                    if (a.checkPassword(pw)) {
                        if (a.isActivated()) {
                            return LoginStatus.Ok;
                        } else {
                            return LoginStatus.NotActivated;
                        }
                    } else {
                        return LoginStatus.Fail;
                    }
                }).orElse(LoginStatus.Fail);
        if (result == LoginStatus.Ok) {
            this.user = accOpt;
        }
        return result;
    }

    @Override
    public void logout() {
        this.user = Optional.empty();
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public boolean isLoggedIn() {
        return this.user.isPresent();
    }

    @Override
    public Optional<Account> getAccount() {
        return this.user;
        //return this.user.map(u -> u);
    }

    @Override
    public Optional<Role> getRole() {
        return this.user.map(Account::getRole);
    }

    @Override
    public Optional<Shop> getShop() {
        return this.user.filter(a -> a.getRole() == Role.Shop).map(a -> (Shop)a);
    }

    @Override
    public Optional<Administrator> getAdmin() {
        return this.user.filter(a -> a.getRole() == Role.Administrator).map(a -> (Administrator)a);
    }

    @Transactional
    @Override
    public void updateSession() {
        this.user = this.user.map(u -> {
            if (u.getRole() == Role.Shop) {
                return this.getEm().find(Shop.class, u.getId());
            } else if (u.getRole() == Role.Administrator) {
                return this.getEm().find(Administrator.class, u.getId());
            } else {
                return this.getEm().find(Account.class, u.getId());
            }
        });
    }
    
}

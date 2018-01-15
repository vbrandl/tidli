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

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import org.othr.tidli.entity.Account;
import org.othr.tidli.util.Role;

/**
 *
 * @author Brandl Valentin
 */
@RequestScoped
public class UserService extends RegisterService<Account> implements UserServiceIF {

    private static final long serialVersionUID = 1161955593295568896L;
    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    protected boolean validateEntity(final Account entity) {
        return true;
    }

    @Transactional
    @Override
    public Account updateUser(Account user) {
        user.setLastUpdated(new Date());
        final Account merged = this.getEm().merge(user);
        return merged;
    }

    @Override
    public Collection<Account> getAllUsers() {
        return this.getEm()
                .createNamedQuery("Account.findAll", Account.class)
                .getResultList()
                .parallelStream()
                .filter(a -> a.getRole() == Role.User)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Account toogleActivationState(final Account acc) {
        final Account merged = this.getEm().merge(acc);
        merged.setActivated(!merged.isActivated());
        return merged;
    }

    @Transactional
    @Override
    public void deleteUser(final Account acc) {
        this.getEm().remove(acc);
    }

}

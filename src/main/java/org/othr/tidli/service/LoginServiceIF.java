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
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Administrator;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.util.LoginStatus;
import org.othr.tidli.util.Role;

/**
 *
 * @author Brandl Valentin
 */
public interface LoginServiceIF {

    /**
     * Try to login using the given email and password.
     * On success the session will be started
     * @param email
     * @param pw
     * @return The login status
     */
    LoginStatus login(final String email, final String pw);
    /**
     * Termiate the current session.
     */
    void logout();
    /**
     * Check if the user is logged in.
     * @return  If the user is logged in
     */
    boolean isLoggedIn();
    /**
     * Returns the logged in account
     * @return The logged in account or {@code empty}
     */
    Optional<Account> getAccount();
    /**
     * Returns the logged in shop, if the account role is {@link Role#Shop }
     * @return The logged in shop or {@code none }
     */
    Optional<Shop> getShop();
    /**
     * Returns the logged in administrator, if the account role is {@link Role#Administrator }
     * @return The logged in administrator or {@code none }
     */
    Optional<Administrator> getAdmin();
    /**
     * Returns the accounts role
     * @return The accounts role or {@code empty}
     */
    Optional<Role> getRole();
    
}

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
import org.othr.tidli.entity.Offer;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
public interface AdminServiceIF {

    boolean activateShop(final Shop s, final Optional<Administrator> adm);
    boolean deleteAccount(final Account acc, final Optional<Administrator> adm);
    boolean deleteOffer(final Offer off, final Optional<Administrator> adm);
    
}

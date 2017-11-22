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
import javax.transaction.Transactional;
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Administrator;
import org.othr.tidli.entity.Id;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.util.Role;

/**
 *
 * @author Brandl Valentin
 */
public class AdminService extends AbstractService<Administrator> implements AdminServiceIF {

    private static final long serialVersionUID = -4883918402174245937L;
    
    @Override
    protected Class<Administrator> getEntityClass() {
        return Administrator.class;
    }

    @Transactional
    @Override
    public boolean activateShop(Shop s, Optional<Administrator> adm) {
        return adm.filter(
                a -> null != s && s.isActivated()
        ).map(a -> {
            final Shop merged = getEm().merge(s);
            merged.setActivated(true);
            getEm().persist(merged);
            return true;
        }).orElse(false);
    }

    @Transactional
    @Override
    public boolean deleteAccount(Account acc, Optional<Administrator> adm) {
        return deleteEntity(acc, adm, Role.Administrator);
    }

    @Override
    public boolean deleteOffer(Offer off, Optional<Administrator> adm) {
        return deleteEntity(off, adm, Role.Administrator);
    }
    
}

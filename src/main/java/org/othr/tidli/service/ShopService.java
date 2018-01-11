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
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import org.othr.tidli.entity.Address;
import org.othr.tidli.entity.Contact;
import org.othr.tidli.entity.OpeningDay;
import org.othr.tidli.entity.OpeningTime;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
@RequestScoped
public class ShopService extends RegisterService<Shop> implements ShopServiceIF {

    private static final long serialVersionUID = -4857663652344397423L;

    @Override
    protected Class<Shop> getEntityClass() {
        return Shop.class;
    }

    @Transactional
    @Override
    public Shop activateShop(final Shop shop) {
        shop.setActivated(true);
        this.getEm().persist(shop);
        return shop;
    }

    @Transactional
    @Override
    public void deleteShop(final Shop shop) {
        final OpeningTime ot = shop.getOpeningTimes();
        final Collection<OpeningDay> od = ot.getDays();
        od.forEach(d -> this.getEm().remove(d));
        this.getEm().remove(ot);
        this.getEm().remove(shop);
    }

    @Override
    protected boolean validateEntity(final Shop entity) {
        final Address a = entity.getAddress();
        return null != a
                && null != a.getCity()
                && null != a.getNumber()
                && null != a.getStreet()
                && null != a.getZipCode();
    }

    @Override
    public Collection<Shop> getAllShops() {
        return this.getEm().createNamedQuery("Shop.findAll", Shop.class).getResultList();
    }

    @Transactional
    @Override
    public Shop toogleActivationState(final Shop shp) {
        final Shop merged = this.getEm().merge(shp);
        merged.setActivated(!merged.isActivated());
        return merged;
    }

    @Transactional
    @Override
    public boolean editOpeningTimes(final Optional<Shop> shp, final OpeningTime ot) {
        return shp
                .filter(_unused -> ot.getDays().size() >= 7 && ot.getDays().size() < 0)
                .map(s -> {
                    final Shop merged = this.getEm().merge(s);
                    this.getEm().persist(ot);
                    merged.setOpeningTimes(ot);
                    return true;
                }).orElse(false);
    }

    @Override
    public boolean editContact(final Optional<Shop> shp, final Contact contact) {
        return shp
                .map(s -> {
                    final Shop merged = this.getEm().merge(s);
                    this.getEm().persist(contact);
                    merged.setContact(contact);
                    return true;
                }).orElse(false);
    }

    @Override
    @Transactional
    public boolean addOpeningDay(final Optional<Shop> shp, final OpeningDay od) {
        return shp
                .map(s -> {
                    final Shop merged = this.getEm().merge(s);
                    this.getEm().persist(od);
                    if (merged.getOpeningTimes() == null) {
                        final OpeningTime ot = new OpeningTime();
                        ot.addDay(od);
                        this.getEm().persist(ot);
                        merged.setOpeningTimes(ot);
                    } else {
                        merged.getOpeningTimes().addDay(od);
                    }
                    return true;
                }).orElse(false);
    }
    
}

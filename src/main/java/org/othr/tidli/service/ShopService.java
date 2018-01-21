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
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import org.othr.tidli.dto.ShopDTO;
import org.othr.tidli.entity.Article;
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
    private static boolean isNullOrEmpty(final String str) {
        return null == str || str.isEmpty();
    }

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
        return null != entity
                && null != entity.getCity()
                && null != entity.getNumber()
                && null != entity.getStreet()
                && null != entity.getZipCode();
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

    @Transactional
    @Override
    public boolean removeOpeningDay(Optional<Shop> shp, OpeningDay od) {
        return shp
                .map(Shop::getOpeningTimes)
                .map(this.getEm()::merge)
                .map(ot -> {
                    ot.removeDay(od);
                    final OpeningDay merged = this.getEm().merge(od);
                    this.getEm().remove(merged);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<Shop> fromDTO(final ShopDTO shp) {
        return null != shp
                ? this.findEntity(shp.getId())
                : Optional.empty();
    }

    @Transactional
    @Override
    public Shop updateShop(final Shop shp, final String pwOld, final String pwNew) {
        final Optional<Shop> merged = this.findEntity(shp.getId())
                .map(this.getEm()::merge);
        merged.ifPresent(s -> {
            s.setLastUpdated(shp.getLastUpdated());
            s.setName(shp.getName());
            s.setEmail(shp.getEmail());
            s.setCity(shp.getCity());
            s.setStreet(shp.getStreet());
            s.setZipCode(shp.getZipCode());
            s.setNumber(shp.getNumber());
            s.setPubEmail(shp.getPubEmail());
            s.setTelNo(shp.getTelNo());
            s.setDescription(shp.getDescription());
            s.setLastUpdated(new Date());
            if (!isNullOrEmpty(pwOld) && !isNullOrEmpty(pwNew)) {
                s.changePassword(pwOld, pwNew);
            }
        });
        //final Shop merged = this.getEm().merge(shp);
        return merged.orElse(null);
    }

    @Transactional
    @Override
    public Optional<Shop> register(final Shop entity) {
        if (this.validateEntity(entity)) {
            try {
                final OpeningTime ot = new OpeningTime();
                this.getEm().persist(ot);
                entity.setOpeningTimes(ot);
                return super.register(entity);
            } catch (final PersistenceException pe) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean isOwnerOf(final Article art, final Optional<Shop> shp) {
        return shp
                .map(s -> this.getEm().find(Shop.class, s.getId()))
                .map(s -> s.hasArticle(art))
                .orElse(false);
    }
    
}

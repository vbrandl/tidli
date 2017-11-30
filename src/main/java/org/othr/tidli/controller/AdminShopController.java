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
import org.othr.tidli.entity.Shop;
import org.othr.tidli.service.ShopServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class AdminShopController extends AbstractController {
    
    @Inject
    private ShopServiceIF ss;
    private Set<Shop> shops;

    @PostConstruct
    private void prepareData() {
        this.shops = new HashSet<>(ss.getAllShops());
    }

    public void toogleActivationState(final Shop shp) {
        if (shops.remove(shp)) {
            final Shop updated = ss.toogleActivationState(shp);
            shops.add(updated);
        }
    }

    public void deleteShop(final Shop shp) {
        if (shops.remove(shp)) {
            ss.deleteShop(shp);
        }
    }

    public Set<Shop> getShops() {
        return Collections.unmodifiableSet(shops);
    }
}

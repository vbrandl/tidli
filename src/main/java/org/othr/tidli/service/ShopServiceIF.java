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
import org.othr.tidli.dto.ShopDTO;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.OpeningDay;
import org.othr.tidli.entity.OpeningTime;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
public interface ShopServiceIF {

    Shop activateShop(final Shop shop);
    void deleteShop(final Shop shop);
    Optional<Shop> register(final Shop entity);
    Collection<Shop> getAllShops();
    Shop toogleActivationState(final Shop shp);
    boolean editOpeningTimes(final Optional<Shop> shp, final OpeningTime ot);
    boolean addOpeningDay(final Optional<Shop> shp, final OpeningDay od);
    boolean removeOpeningDay(final Optional<Shop> shp, final OpeningDay od);
    Optional<Shop> fromDTO(final ShopDTO shp);
    Shop updateShop(final Shop shp, final String pwOld, final String pwNew);
    Optional<Shop> findEntity(final long id);
    boolean isOwnerOf(final Article art, final Optional<Shop> shp);
    
}

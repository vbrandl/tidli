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

import javax.inject.Inject;
import javax.jws.WebService;
import org.othr.tidli.dto.ShopDTO;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Offer;

/**
 *
 * @author Brandl Valentin
 */
@WebService
public class PubOfferService extends AbstractService<Offer> implements PubOfferServiceIF {

    private static final long serialVersionUID = -480568517771229974L;

    @Inject
    private OfferServiceIF os;
    @Inject
    private ShopServiceIF ss;
    
    @Override
    protected Class<Offer> getEntityClass() {
        return Offer.class;
    }

    @Override
    public boolean createOffer(final ShopDTO s, final Article art,
            final int price, final int amount) {
        return this.ss.fromDTO(s)
                .map(shp -> this.os.createOffer(art, price, amount, shp) != null)
                .orElse(false);
    }
    
}

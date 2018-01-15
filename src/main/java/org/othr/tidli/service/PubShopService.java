/*
 * Copyright (C) 2018 Brandl Valentin
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
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import org.othr.tidli.dto.ShopDTO;
import org.othr.tidli.entity.Shop;


@WebService
@RequestScoped
public class PubShopService extends AbstractService<Shop> implements PubShopServiceIF {

    private static final long serialVersionUID = 6286821680487468328L;

    @Inject
    private ShopServiceIF ss;

    @Override
    public Collection<ShopDTO> listShops() {
        return this.ss.getAllShops().parallelStream().map(ShopDTO::new).collect(Collectors.toList());
    }

    @Override
    protected Class<Shop> getEntityClass() {
        return Shop.class;
    }
    
}

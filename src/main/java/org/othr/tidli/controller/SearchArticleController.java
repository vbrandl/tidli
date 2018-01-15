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
package org.othr.tidli.controller;

import java.util.Collection;
import javax.inject.Inject;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.service.OfferServiceIF;

/**
 *
 * @author Brandl Valentin
 */
public class SearchArticleController extends AbstractController {
    
    private static final long serialVersionUID = 8287699942027330617L;

    private String query;
    private String city;
    private Integer zipCode;

    @Inject
    private OfferServiceIF os;

    public Collection<Offer> searchArticles() {
        return this.os.findForQuery(this.query);
    }

    public Collection<Offer> searchByOwnLocation() {
        return this.os.findForLocation(this.getUser());
    }

    public Collection<Offer> searchByCustomLocation() {
        return this.os.findForCustomLocation(this.city, this.zipCode);
    }

    public Integer getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(final Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(final String query) {
        this.query = query;
    }
    
}

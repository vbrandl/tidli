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
import javax.inject.Inject;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
public final class PubOfferService extends AbstractService<Offer> implements PubOfferServiceIF {

    private static final long serialVersionUID = -480568517771229974L;

    @Inject
    private OfferServiceIF os;
    @Inject
    private ArticleServiceIF as;
    
    @Override
    protected Class<Offer> getEntityClass() {
        return Offer.class;
    }

    @Override
    public boolean createOffer(final Shop s, final Article art, final Offer off,
            final int price, final int amount) {
        return null != this.os.createOffer(art, price, amount, s);
    }

    @Override
    public boolean createArticle(final Shop s, final Article art) {
        return null != this.as.createArticle(art, s);
    }

    @Override
    public Collection<Article> listArticles(final Shop s) {
        final Shop persistentShop = getEm().find(Shop.class, s.getId());
        return persistentShop.getArticles();
    }
    
}

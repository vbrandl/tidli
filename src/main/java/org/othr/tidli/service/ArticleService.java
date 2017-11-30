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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
@RequestScoped
public class ArticleService extends AbstractService<Article> implements ArticleServiceIF {

    private static final long serialVersionUID = 7206970196829319624L;
    @Inject
    private OfferServiceIF os;
    
    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }

    @Transactional
    @Override
    public Article createArticle(final Article article, final Shop shp) {
        final Shop s = getEm().merge(shp);
        getEm().persist(article);
        s.addArticle(article);
        return article;
    }

    @Override
    public byte[] getImageForId(final long id) {
        return findEntity(id).map(art -> art.getImage()).orElse(new byte[] {});
    }

    @Transactional
    @Override
    public void deleteArticle(final Article art) {
        final Article merged = getEm().merge(art);
        merged.getRatings().parallelStream().forEach(getEm()::remove);
        os.findForArticle(art).parallelStream().map(getEm()::merge)
                .forEach(getEm()::remove);
        getEm().remove(merged);
    }

}

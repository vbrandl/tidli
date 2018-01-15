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
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
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
        final Shop s = this.getEm().merge(shp);
        this.getEm().persist(article);
        s.addArticle(article);
        return article;
    }

    @Override
    public byte[] getImageForId(final long id) {
        return this.findEntity(id).map(art -> art.getImage()).orElse(new byte[] {});
    }

    @Override
    public Optional<Shop> getShopForArticle(final Article art) {
        final TypedQuery<Shop> query = this.getEm()
                .createNamedQuery("Shop.findAll", Shop.class);
        final List<Shop> shps = query.getResultList();
        return shps.parallelStream().filter(s -> s.getArticles().contains(art)).findFirst();
    }

    @Transactional
    @Override
    public void deleteArticle(final Article art) {
        final Article merged = this.getEm().merge(art);
        // named query fails somehow. doing it manually
        //final TypedQuery<Shop> query = this.getEm()
                //.createNamedQuery("Shop.findByArticle", Shop.class);
        //query.setParameter("art", art);
        //final Optional<Shop> owner = AbstractService.singleResultToOptional(query);
        final TypedQuery<Shop> query = this.getEm()
                .createNamedQuery("Shop.findAll", Shop.class);
        final List<Shop> shps = query.getResultList();
        final Optional<Shop> owner = shps.parallelStream().filter(s -> s.getArticles().contains(art)).findFirst();
        owner.map(s -> this.getEm().merge(s)).ifPresent(s -> s.removeArticle(merged));
        merged.getRatings().parallelStream().forEach(this.getEm()::remove);
        this.os.findForArticle(art).parallelStream().map(this.getEm()::merge)
                .forEach(this.getEm()::remove);
        this.getEm().remove(merged);
    }

    @Override
    public Collection<Article> findAll() {
        return this.getEm().createNamedQuery("Article.findAll", Article.class).getResultList();
    }

}

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
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
public interface ArticleServiceIF {

    Article createArticle(final Article art, final Shop shp);
    byte[] getImageForId(final long id);
    void deleteArticle(final Article art);
    Optional<Article> findEntity(final long id);
    Collection<Article> findAll();
    public Optional<Shop> getShopForArticle(final Article art);
    
}

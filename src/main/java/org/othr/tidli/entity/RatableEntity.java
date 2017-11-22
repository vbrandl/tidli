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
package org.othr.tidli.entity;

import java.util.Collection;

/**
 *
 * @author Brandl Valentin
 */
public interface RatableEntity {

    Collection<Rating> getRatings();
    void setRatings(final Collection<Rating> ratings);
    boolean addRating(final Rating r);
    default boolean isRatedByUser(final Account a) {
        return this.getRatings().parallelStream().anyMatch(r -> r.getAccount().equals(a));
    }
    
}

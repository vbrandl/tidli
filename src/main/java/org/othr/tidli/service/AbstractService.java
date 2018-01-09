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

import java.io.Serializable;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Id;
import org.othr.tidli.util.Role;

/**
 *
 * @author Brandl Valentin
 * @param <T>
 */
public abstract class AbstractService<T extends Id> implements Serializable {

    private static final long serialVersionUID = -7707210843707915366L;
    protected static<T> Optional<T> singleResultToOptional(final TypedQuery<T> query) {
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException _unused) {
            return Optional.empty();
        }
    }

    @PersistenceContext
    private EntityManager em;
    protected abstract Class<T> getEntityClass();

    protected EntityManager getEm() {
        return em;
    }

    /**
     * Finds an entity by its id
     * @param id The primary key
     * @return The entity or {@code empty}
     */
    public Optional<T> findEntity(final long id) {
        return Optional.ofNullable(
                getEm().find(getEntityClass(), id)
        );
    }

    /**
     * Merges an entity if present and returns the resulting value
     * @param entity The optional to merge
     * @return The merged entity or {@code empty}
     */
    protected Optional<T> mergeIfPresent(final Optional<T> entity) {
        if (entity.isPresent()) {
            final T e = getEm().merge(entity.get());
            return Optional.of(e);
        } else {
            return Optional.empty();
        }
    }

    protected <E extends Id>boolean deleteEntity(final E entity, Optional<? extends Account> acc,
            final Role reqRole) {
        return acc.filter(a -> null != entity && a.getRole() == reqRole)
                .map(_unused -> {
                    final E merged = getEm().merge(entity);
                    getEm().remove(merged);
                    return true;
                }).orElse(false);
    }

}

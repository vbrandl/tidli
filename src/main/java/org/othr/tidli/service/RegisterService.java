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

import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;
import org.othr.tidli.entity.Id;

/**
 *
 * @author Brandl Valentin
 * @param <T>
 */
@Dependent
public abstract class RegisterService<T extends Id> extends AbstractService<T> {

    private static final long serialVersionUID = 4671179992834520117L;
    protected abstract boolean validateEntity(final T entity);

    /**
     * Registers a new entity of type {@code T} if it passes validation by 
     * {@link #validateEntity(org.othr.tidli.entity.Id) }.
     * @param entity The entity to register
     * @return The entity wrapped into an {@code Optional<T>} or an empy
     * {@code Optional} if validation failed.
     */
    @Transactional
    public Optional<T> register(final T entity) {
        if (validateEntity(entity)) {
            getEm().persist(entity);
            return Optional.of(entity);
        } else {
            return Optional.empty();
        }
    }
}

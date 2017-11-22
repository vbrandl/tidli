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
package org.othr.tidli.controller;

import java.util.Optional;
import org.othr.tidli.entity.Id;
import org.othr.tidli.service.RegisterService;

/**
 *
 * @author Brandl Valentin
 * @param <T>
 */
public abstract class RegisterController<T extends Id> {
    protected abstract RegisterService<T> getService();
    protected abstract Optional<T> createEntity();
    public void register() {
        final Optional<T> entOpt = createEntity();
        if (entOpt.isPresent()) {
            final T entity = entOpt.get();
            getService().register(entity);
        }
    }
}

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

import javax.persistence.Entity;
import org.othr.tidli.util.Role;

/**
 *
 * @author Brandl Valentin
 */
@Entity
public class Administrator extends Account {
    
    private static final long serialVersionUID = -9156274763174215690L;
    private static final Role ROLE = Role.Administrator;

    @Override
    public Role getRole() {
        return ROLE;
    }
    
}

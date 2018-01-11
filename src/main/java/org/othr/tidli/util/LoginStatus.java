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
package org.othr.tidli.util;

import java.util.Optional;

/**
 *
 * @author Brandl Valentin
 */
public enum LoginStatus {
    Ok("index?faces-redirect=true", null),
    NotActivated("", "Ihr Account ist nicht aktiv"),
    Fail("", "Falscher Benutzername oder Passwort");

    private final String target;
    private final String message;
    private LoginStatus(final String target, final String message) {
        this.target = target;
        this.message = message;
    }

    public String getTarget() {
        return this.target;
    }

    public Optional<String> getMessage() {
        return Optional.ofNullable(this.message);
    }
}

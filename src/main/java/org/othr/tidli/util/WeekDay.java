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
package org.othr.tidli.util;

import java.util.Optional;

/**
 *
 * @author Brandl Valentin
 */
public enum WeekDay {
    Monday("Montag"),
    Tuesday("Dienstag"),
    Wednesday("Mittwoch"),
    Thursday("Donnerstag"),
    Friday("Freitag"),
    Saturday("Samstag"),
    Sunday("Sonntag");

    private final String name;
    private WeekDay(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Optional<WeekDay> fromString(final String str) {
        if (null != str) {
            for (final WeekDay wd : WeekDay.values()) {
                if (wd.toString().toLowerCase().equals(str.trim().toLowerCase())) {
                    return Optional.of(wd);
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<WeekDay> fromInteger(final Integer str) {
        if (null != str) {
            for (final WeekDay wd : WeekDay.values()) {
                if (wd.ordinal() == str) {
                    return Optional.of(wd);
                }
            }
        }
        return Optional.empty();
    }

    public int getInt() {
        return this.ordinal();
    }
}

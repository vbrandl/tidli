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

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Brandl Valentin
 */
public class AccountTest {

    /**
     * Test of checkPassword method, of class Account.
     */
    @Test
    public void testCheckPassword() {
        final String pw = "foo";
        final Account instance = new Account("foo", pw, "bar", null, null, null, null);
        Assert.assertTrue(instance.checkPassword(pw));
        Assert.assertFalse(instance.checkPassword("baz"));
    }

    /**
     * Test of changePassword method, of class Account.
     */
    @Test
    public void testChangePassword() {
        final String old = "bar";
        final String newPw = "baz";
        final Account instance = new Account("foo", old, "bar", null, null, null, null);
        Assert.assertFalse(instance.changePassword("foo", newPw));
        Assert.assertTrue(instance.checkPassword(old));
        Assert.assertTrue(instance.changePassword(old, newPw));
        Assert.assertTrue(instance.checkPassword(newPw));
    }

}

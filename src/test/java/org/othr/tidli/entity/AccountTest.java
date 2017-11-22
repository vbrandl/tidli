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

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.othr.tidli.util.Role;

/**
 *
 * @author Brandl Valentin
 */
public class AccountTest {

    public AccountTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of checkPassword method, of class Account.
     */
    @org.junit.Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        final String pw = "foo";
        final Account instance = new Account("foo", pw, "bar", null);
        assertTrue(instance.checkPassword(pw));
        assertFalse(instance.checkPassword("baz"));
    }

    /**
     * Test of changePassword method, of class Account.
     */
    @org.junit.Test
    public void testChangePassword() {
        System.out.println("changePassword");
        final String old = "bar";
        final String newPw = "baz";
        final Account instance = new Account("foo", old, "bar", null);
        assertFalse(instance.changePassword("foo", newPw));
        assertTrue(instance.checkPassword(old));
        assertTrue(instance.changePassword(old, newPw));
        assertTrue(instance.checkPassword(newPw));
    }

}

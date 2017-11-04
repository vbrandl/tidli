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

import com.lambdaworks.crypto.SCryptUtil;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.transaction.Transactional;
import org.othr.tidli.entity.User;
import org.othr.tidli.util.Static;

/**
 *
 * @author Brandl Valentin
 */
@RequestScoped
@WebService
public class UserService extends AbstractService {
    
    @Transactional
    public User registerUser(final User user, final String password) {
        user.setPassword(SCryptUtil.scrypt(password, Static.SCRYPT_CPU_COST, Static.SCRYPT_MEM_COST, Static.SCRYPT_PARALLELIZATION));
        getEm().persist(user);
        return user;
    }

}

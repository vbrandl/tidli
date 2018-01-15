/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.othr.tidli.service;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import org.othr.tidli.dto.AccountDTO;
import org.othr.tidli.entity.Administrator;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
@WebService
@RequestScoped
public class PubUserService implements PubUserServiceIF {

    @Inject
    private UserServiceIF us;
    @Inject
    private ShopServiceIF ss;
    @Inject
    private AdminServiceIF as;
    
    @Override
    public AccountDTO getUser(final long id) {
        final Optional<Shop> shp = this.ss.findEntity(id);
        if (shp.isPresent()) {
            return shp.map(AccountDTO::new).get();
        }
        final Optional<Administrator> adm = this.as.findEntity(id);
        if (adm.isPresent()) {
            return adm.map(AccountDTO::new).get();
        }
        return this.us.findEntity(id).map(AccountDTO::new).orElse(null);
    }
    
}

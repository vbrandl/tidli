/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.othr.tidli.controller;

import com.othr.sw.gsupportticketsystem.services.Ticket;
import com.othr.sw.gsupportticketsystem.services.UserAccessPointService;
import com.othr.sw.gsupportticketsystem.services.UserAccessPointServiceService;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class ListTicketsController extends AbstractController {
    
    private static final long serialVersionUID = 1558005703569519403L;

    private final UserAccessPointService uaps = new UserAccessPointServiceService().getUserAccessPointServicePort();

    public List<Ticket> getTickets() {
        // marshalling error from webservice
        // provider won't fix
        // (tickets seem to get created but the response does not work
        return this.getUser()
                .map(u -> this.uaps.getTickets((int)u.getId()))
                .orElse(Collections.emptyList());
    }
    
}

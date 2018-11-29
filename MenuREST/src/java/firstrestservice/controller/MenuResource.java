/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstrestservice.controller;

import firstrestservice.model.Dish;
import firstrestservice.service.MenuService;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author iovani.juarezgarcia
 */
@Path("/menu")
@Stateless
public class MenuResource {
    
    @Inject
    private MenuService menuService;

    @GET
    @Produces(value={MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @PermitAll
    public List<Dish> getMenu(
            @DefaultValue("") @QueryParam("dishName") String dishName,
            @DefaultValue("") @QueryParam("dishCategory") String dishCategory,
            @DefaultValue("") @QueryParam("dishDescription") String dishDescription){
        return menuService.getMenu(dishName,dishCategory,dishDescription);
    }

    @GET
    @Produces(value={MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{dishId}")
    @PermitAll
    public Response getDishById(@PathParam("dishId") int dishId){
        
        Dish dishFound = menuService.findDishById(dishId);
        
        if (dishFound == null)
            return Response.status(Status.NOT_FOUND).build();
        
        return Response.ok().entity(dishFound).build();
    }
    
    @POST
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response addDish(List<Dish> dishList) {

        for (Dish dish : dishList) {
            if (menuService.checkExistingBeforePost(dish)) {
                return Response.status(Status.CONFLICT).build();
            } else {
                try {
                    menuService.addDish(dish);
                } catch (Exception e) {
                    System.out.println("Exception when posting: " + e.getMessage());
                    return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                }
            }
        }
        return Response.ok().build();
    }

    @PUT
    @Produces(value={MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{dishId}")
    @PermitAll
    public Response updateDish(@PathParam("dishId") int dishId, Dish updatedDish) {
        try {
            Dish dish = menuService.findDishById(dishId);

            dish.setDishName(updatedDish.getDishName());
            dish.setDishCategory(updatedDish.getDishCategory());
            dish.setDishDescription(updatedDish.getDishDescription());

            menuService.updateDish(dish);
            return Response.ok().entity(dish).build();

        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{dishId}")
    @PermitAll
    public Response deleteDish(@PathParam("dishId") int dishId){
        try {
            Dish dishToDel = menuService.findDishById(dishId);
            
            if(dishToDel == null){
                return Response.status(404).build();
            }
            menuService.deleteDish(dishToDel);
            return Response.ok().build();
            
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

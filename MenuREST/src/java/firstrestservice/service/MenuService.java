/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstrestservice.service;

import firstrestservice.model.Dish;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author iovani.juarezgarcia
 */
@Local
public interface MenuService {
    public List<Dish> getMenu(String dishName,String dishCategory, String dishDescription);
    public Dish findDishById(int dishId);
    public void addDish(Dish d);
    public void updateDish(Dish d);
    public void deleteDish(Dish d);
    public boolean checkExistingBeforePost(Dish dish);
}
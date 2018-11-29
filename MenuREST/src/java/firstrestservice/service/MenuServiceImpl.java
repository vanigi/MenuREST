/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstrestservice.service;

import firstrestservice.dao.DishDAO;
import firstrestservice.model.Dish;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author iovani.juarezgarcia
 */
@Stateless
public class MenuServiceImpl implements MenuService {
    
    @Resource
    private SessionContext context;
    
    @Inject
    private DishDAO dishDao;

    @Override
    public List<Dish> getMenu(String dishName,String dishCategory, String dishDescription){
        return dishDao.getMenu(dishName,dishCategory,dishDescription);
    }

    @Override
    public Dish findDishById(int dishId){
        return dishDao.findDishById(dishId);
    }
    
    @Override
    public void addDish(Dish d){
        dishDao.addDish(d);
    }
    
    @Override
    public void updateDish(Dish d){
        try{
            dishDao.updateDish(d);
        }catch(Throwable t){
            context.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }
    
    @Override
    public void deleteDish(Dish d){
        dishDao.deleteDish(d);
    }
    
    @Override
    public boolean checkExistingBeforePost(Dish dish){
        return dishDao.checkExistingBeforePost(dish);
    }
}

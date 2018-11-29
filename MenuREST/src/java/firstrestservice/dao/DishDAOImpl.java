/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstrestservice.dao;

import firstrestservice.model.Dish;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author iovani.juarezgarcia
 */
@Stateless
public class DishDAOImpl implements DishDAO {

    @PersistenceContext(unitName = "MenuRESTPU")
    EntityManager entityManager;
    
    @Override
    public List<Dish> getMenu(String dishName,String dishCategory, String dishDescription){
        String query = "select menu from Dish menu "
                + "where lower(menu.dishName) like lower('%"+dishName+"%') "
                + "and lower(menu.dishCategory) like lower('%"+dishCategory+"%') "
                + "and lower(menu.dishDescription) like lower('%"+dishDescription+"%') "
                + "order by menu.dishId";
                
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Dish findDishById(int dishId){
        return entityManager.getReference(Dish.class, dishId);  
    }

    @Override
    public void addDish(Dish d){
        entityManager.persist(d);
    }

    @Override
    public void updateDish(Dish d){
        entityManager.merge(d);
    }

    @Override
    public void deleteDish(Dish d){
        entityManager.remove(d);
    }   

    @Override
    public boolean checkExistingBeforePost(Dish dish) {
                
        String query = "select d from Dish d where d.dishName = '"+dish.getDishName()+"'";
        
        try{
            entityManager.createQuery(query).getSingleResult();
            return true;
        }catch(NoResultException nre){
            System.out.println("NO DISH FOUND WITH SAME NAME BEFORE POSTING "+dish.getDishName()+" "+nre.getMessage());
            return false;
        }
        
   
    }
}

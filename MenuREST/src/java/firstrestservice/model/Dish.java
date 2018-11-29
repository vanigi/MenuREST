/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstrestservice.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iovani.juarezgarcia
 */
@Entity
@Table(name= "DT_DISH", schema="RESTUSER")
@XmlRootElement
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DT_DISH_ID")
    private int dishId;

    @Column(name = "DT_DISH_NAME")
    private String dishName;

    @Column(name = "DT_DISH_CATEGO")
    private String dishCategory;
    
    @Column(name = "DT_DISH_DESC")
    private String dishDescription;

    public Dish(){}
    
    public Dish(int dishId){
        this.dishId = dishId;
    }

    public Dish(String name){
        this.dishName = name;
    }
    
    public Dish(String name, String category, String description) {
        this.dishName = name;
        this.dishCategory = category;
        this.dishDescription = description;
    }

    public int getDishId() {
        return this.dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return this.dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishCategory() {
        return this.dishCategory;
    }

    public void setDishCategory(String dishCategory) {
        this.dishCategory = dishCategory;
    }

    public String getDishDescription() {
        return this.dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }
    
}
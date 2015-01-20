/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Models;

import java.util.Observable;

/**
 *
 * @author st
 */
public class Context extends Observable {
    // Properties
    private int id;
    private String name;
    
    public Context() {
        // Initialize fields
        this.id = -1;
        this.name = "";
    }
    
    public Context(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers(this);
    }
    
}

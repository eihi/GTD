/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.model;

import java.util.Observable;

/**
 *
 * @author st
 */
public class Status extends Observable {
    // Properties
    private int id;
    private String name;
    
    /**
     * Constructor
     */
    public Status() {
        // Initialize fields
        this.id = 0;
        this.name = "";
    }
    
    public Status(String name) {
        // Initialize fields
        super();
        this.name = name;
    }
    
    /**
     * Constructor
     * @param id
     * @param name 
     */
    public Status(int id, String name) {
        // Initialize fields
        super();
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

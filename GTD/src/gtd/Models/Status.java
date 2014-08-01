/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Models;

/**
 *
 * @author st
 */
public class Status {
    // Properties
    private final int id;
    private String name;
    
    /**
     * Constructor
     */
    public Status() {
        // Initialize fields
        this.id = 0;
        this.name = "";
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
    }
    
}

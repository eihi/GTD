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
public class Thought extends Observable {
    // Properties
    private int id;
    private String notes;
    
    /**
     * Constructor
     */
    public Thought() {
        // Initialize fields
        this.id = 0;
        this.notes = "";
    }
    
    public Thought(String notes) {
        super();
        this.notes = notes;
    }
    
    /**
     * Constructor
     * @param id
     * @param notes 
     */
    public Thought(int id, String notes) {
        // Initialize fields
        super();
        this.id = id;
        this.notes = notes;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
        setChanged();
        notifyObservers(this);
    }
}

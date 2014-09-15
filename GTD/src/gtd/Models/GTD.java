/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Models;

import java.util.ArrayList;

/**
 *
 * @author st
 */
public class GTD {
    // Properties
    private final ArrayList<Action> actions;
    private final ArrayList<Project> projects;
    private final ArrayList<Thought> thoughts;
    private final ArrayList<Context> contexts;
    private final ArrayList<Status> statuses;
    
    public GTD() {
        // Initialize models
        this.actions = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.thoughts = new ArrayList<>();
        this.contexts = new ArrayList<>();
        this.statuses = new ArrayList<>();
    }
    
    public ArrayList<Action> getActions() {
        return this.actions;
    }
    
    public ArrayList<Project> getProjects() {
        return this.projects;
    }
    
    public ArrayList<Thought> getThoughts() {
        return this.thoughts;
    }
    
    public ArrayList<Context> getContexts() {
        return this.contexts;
    }
    
    public ArrayList<Status> getStatuses() {
        return this.statuses;
    }
    
}

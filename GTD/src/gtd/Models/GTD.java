/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Models;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author st
 */
public class GTD extends Observable{
    // Properties
    private ArrayList<Action> actions;
    private ArrayList<Project> projects;
    private ArrayList<Thought> thoughts;
    private ArrayList<Context> contexts;
    private ArrayList<Status> statuses;
    
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
    
    public void setActions(ArrayList<Action> actions){
        this.actions = actions;
        setChanged();
        notifyObservers(this.actions);
    } 
    
    public ArrayList<Project> getProjects() {
        return this.projects;
    }
    
    public void setProjects(ArrayList<Project> projects){
        this.projects = projects;
        setChanged();
        notifyObservers(this.projects);
    } 
    
    public ArrayList<Thought> getThoughts() {
        return this.thoughts;
    }
    public void setThoughts(ArrayList<Thought> thoughts){
        this.thoughts = thoughts;
        setChanged();
        notifyObservers(this.thoughts);
    } 
    
    public ArrayList<Context> getContexts() {
        return this.contexts;
    }
    
    public void setContexts(ArrayList<Context> contexts){
        this.contexts = contexts;
        setChanged();
        notifyObservers(this.contexts);
    } 
    
    public ArrayList<Status> getStatuses() {
        return this.statuses;
    } 
    
    public void setStatuses(ArrayList<Status> statuses){
        this.statuses = statuses;
        setChanged();
        notifyObservers(this.statuses);
    }     
}

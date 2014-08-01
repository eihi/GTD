/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Controllers;

import gtd.DAL.*;
import gtd.Models.*;
import gtd.Views.*;
import java.util.ArrayList;

/**
 *
 * @author st
 */
public class MainController {
    // Properties
    private final ArrayList<Action> actions;
    private final ArrayList<Project> projects;
    private final ArrayList<Thought> thoughts;
    private final ArrayList<Context> contexts;
    private final ArrayList<Status> statuses;
    private final MainView mainview;
    
    /**
     * Constructor
     * @param mainview
     */
    public MainController(MainView mainview) {
        // Initialize models
        this.actions = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.thoughts = new ArrayList<>();
        this.contexts = new ArrayList<>();
        this.statuses = new ArrayList<>();
        
        // Initialize view
        this.mainview = mainview;
        
        //this.mainview.a
    }    
}

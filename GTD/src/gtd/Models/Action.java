/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gtd.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author st
 */
public class Action {
    // Properties
    private final int id;
    private String description;
    private String notes;
    private int status_id;
    private int context_id;
    private int project_id;
    private SimpleDateFormat date_format;
    private Date action_date;
    private Date statuschange_date;
    private boolean done;
    
    /**
     * Constructor
     */
    public Action() {
        // Initialize fields
        this.id = 0;
        this.description = "";
        this.notes = "";
        this.status_id = 0;
        this.context_id = 0;
        this.project_id = 0;
        this.action_date = new Date();
        this.statuschange_date = new Date();
        this.done = false;
    }
    
    /**
     * Constructor
     * @param id
     * @param description
     * @param notes
     * @param status_id
     * @param context_id
     * @param project_id
     * @param action_date
     * @param statuschange_date
     * @param done 
     */
    public Action(int id, String description, String notes, int status_id, int context_id, int project_id, Date action_date, Date statuschange_date, boolean done) {
        super();
        this.id = id;
        this.description = description != null ? description : "";
        this.notes = notes != null ? notes : "";
        this.status_id = status_id;
        this.context_id = context_id;
        this.project_id = project_id;
        this.action_date = action_date;
        this.statuschange_date = statuschange_date;
        this.done = done;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
    }

    /**
     * @return the status_id
     */
    public int getStatus_id() {
        return status_id;
    }

    /**
     * @param status_id the status_id to set
     */
    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    /**
     * @return the context_id
     */
    public int getContext_id() {
        return context_id;
    }

    /**
     * @param context_id the context_id to set
     */
    public void setContext_id(int context_id) {
        this.context_id = context_id;
    }

    /**
     * @return the project_id
     */
    public int getProject_id() {
        return project_id;
    }

    /**
     * @param project_id the project_id to set
     */
    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    /**
     * @return the date_format
     */
    public SimpleDateFormat getDate_format() {
        return date_format;
    }

    /**
     * @param date_format the date_format to set
     */
    public void setDate_format(SimpleDateFormat date_format) {
        this.date_format = date_format;
    }

    /**
     * @return the action_date
     */
    public Date getAction_date() {
        return action_date;
    }

    /**
     * @param action_date the action_date to set
     */
    public void setAction_date(Date action_date) {
        this.action_date = action_date;
    }

    /**
     * @return the statuschange_date
     */
    public Date getStatuschange_date() {
        return statuschange_date;
    }

    /**
     * @param statuschange_date the statuschange_date to set
     */
    public void setStatuschange_date(Date statuschange_date) {
        this.statuschange_date = statuschange_date;
    }

    /**
     * @return the done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done the done to set
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}

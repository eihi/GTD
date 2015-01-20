/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gtd.Views;

import gtd.Config;
import gtd.Controllers.MainController;
import gtd.Models.Action;
import gtd.Models.Context;
import gtd.Models.Project;
import gtd.Models.Status;
import gtd.Models.Thought;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author st
 */
public class MainView extends JFrame implements Observer {

    private static final Dimension WINDOWSIZE = Config.WINDOWSIZE;
    private static final String TITLE = Config.TITLE;
    private static final Color BGCOLOR = Config.BGCOLOR;
    public HeaderView Header;
    public MenuView Menu;
    public ContentView Content;
    private JTable resultTable;
    private MainController controller;

    /**
     * Constructor
     */
    public MainView() {

        super(TITLE);
        initMainView();
    }

    private void initMainView() {

        // Initialize mainview
        this.setTitle(TITLE);
        this.setLayout(new BorderLayout());
        this.setSize(WINDOWSIZE);
        this.setResizable(true);
        this.setBackground(BGCOLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add header
        Header = new HeaderView(this);
        this.add(Header, BorderLayout.NORTH);

        // Create menu and add to view
        Menu = new MenuView(this);
        this.add(Menu, BorderLayout.WEST);

        // Create content and add to view
        Content = new ContentView(this);
        Content.setLayout(new GridLayout());
        Content.setBackground(BGCOLOR);
        this.add(Content, BorderLayout.CENTER);
        this.setVisible(true);

    }

    public void addActionListener(ActionListener controller) {
        Header.btnNew.addActionListener(controller);
        Menu.btnActions.addActionListener(controller);
        Menu.btnContexts.addActionListener(controller);
        Menu.btnProjects.addActionListener(controller);
        Menu.btnThoughts.addActionListener(controller);
    }

    public void addController(MainController controller) {
        this.controller = controller;
    }

    public void addTable(ResultSetTableModel tableModel) {
        resultTable = new JTable(tableModel);
        resultTable.setAutoCreateRowSorter(true);
        resultTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent ev) {
                if (ev.getValueIsAdjusting()) 
                    openDetailView((int)resultTable.getValueAt(resultTable.getSelectedRow(), 0));
            }
        });

        Content.setContent(resultTable);
        Content.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        //who called us and what did they send?
        System.out.println("View      : Observable is " + o.getClass() + ", object passed is " + o.getClass());

        //model Push 
        //parse obj
        //myTextField.setText("" + ((Integer)obj).intValue());	//obj is an Object, need to cast to an Integer
    }
    
    public void openDetailView(int selection) {DetailView detailView = new DetailView(controller);
        switch (controller.getCurrentTable()) {
            case "actions":
                detailView.load(controller.getAction(selection));
                break;
            case "thoughts":
                detailView.load(controller.getThought(selection));
                break;
            case "projects":
                detailView.load(controller.getProject(selection));
                break;
            case "context":
                detailView.load(controller.getContext(selection));
                break;
            case "statuses":
                detailView.load(controller.getStatus(selection));
                break;
            default:
                break;
        }
    }
}

/*
 * Name: Benjamin McConville 
 * Date: 09-09-2014
 */
package dayplanner;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

/**
 * DayPlannerGui class
 *
 * @author Benjamin McConville
 *
 * A class that implements a GUI interface
 */
public class DayPlannerGui extends JFrame implements ActionListener {

    public static final int windowWidth = 730;
    public static final int windowHeight = 500;
    public static final int SMALL_WIDTH = 300; //for confirm window
    public static final int SMALL_HEIGHT = 200;//for confirm window
    public static final int LINES = 4;
    public static final int CHAR_PER_LINE = 30;

    private static DayPlanner dayplanner;
    private String activityName = "other";
    private String message = "";
    private JTextField title;
    private JTextField startingTime;
    private JTextField endingTime;
    private JTextField titleS;
    private JTextField startingTimeS;
    private JTextField endingTimeS;
    private JTextField location;
    private JTextField comment;
    private JTextArea messageDisplay;
    private JTextArea searchDisplay;
    private JPanel startPanel;
    private JPanel containerAdd;
    private JPanel containerSearch;
    private JPanel textPanel;
    private JPanel textPanelS;

    /**
     * The main of the program which gets file to read into the program then
     * uses the GUI interface to add and search the for activities
     *
     * @param args arguments through the command line to accept input and output
     * files
     */
    public static void main(String[] args) {

        dayplanner = new DayPlanner();
        Scanner StreamObject;

        try {
            if (args.length == 2) {
                StreamObject = new Scanner(new FileInputStream(args[0]));
                dayplanner.addActivityFile(StreamObject);
                StreamObject.close();

            }
        } catch (FileNotFoundException ex) {//Check and see if it will throw exception if only 1 file
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        DayPlannerGui gui = new DayPlannerGui();
        gui.setVisible(true);

        try {
            if (args.length == 1) {
                PrintWriter outputStream = new PrintWriter(new FileOutputStream(args[0]));

                dayplanner.addActivityToFile(outputStream);
            } else if (args.length == 2) {
                PrintWriter outputStream = new PrintWriter(new FileOutputStream(args[1]));

                dayplanner.addActivityToFile(outputStream);
            }
        } catch (FileNotFoundException ex) {//Check and see if it will throw exception if no files
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Action listener for when the command menu selects add
     */
    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            startPanel.setVisible(false);
            textPanel.setVisible(true);
            textPanelS.setVisible(false);
            containerAdd.setVisible(true);
            containerSearch.setVisible(false);
        }
    }

    /**
     * Action listener for when the command menu selects search
     */
    private class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            startPanel.setVisible(false);
            textPanel.setVisible(false);
            textPanelS.setVisible(true);
            containerAdd.setVisible(false);
            containerSearch.setVisible(true);
        }
    }
    
    private class quitExit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ConfirmWindow checkers = new ConfirmWindow( );
            checkers.setVisible(true);
        }
    }

    /**
     * Action listener for the comboBox on both the add and search panel
     */
    private class ComboBox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            activityName = (String) cb.getSelectedItem();
        }
    }

    /**
     * Action Listener for the reset button, which sets all text fields to
     * nothing
     */
    private class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            title.setText("");
            startingTime.setText("");
            endingTime.setText("");
            titleS.setText("");
            startingTimeS.setText("");
            endingTimeS.setText("");
            location.setText("");
            comment.setText("");
            messageDisplay.setText("");
            searchDisplay.setText("");
        }
    }

    /**
     * Action Listener for the enter button on the add panel
     */
    private class EnterListenerAdd implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            message = dayplanner.addActivityGui(activityName, title.getText(), startingTime.getText(), endingTime.getText(), location.getText(), comment.getText());
            messageDisplay.setText(message);
        }
    }

    /**
     * Action listener for the enter button on the search panel
     */
    private class EnterListenerSearch implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            message = dayplanner.searchActivitiesGui(activityName, title.getText(), startingTime.getText(), endingTime.getText());
            searchDisplay.setText(message);
        }
    }

    /**
     * The constructor class for the GUI
     */
    public DayPlannerGui() {
        super("Day Planner                                                                                                                     ");
        setSize(windowWidth, windowHeight);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CheckOnExit( ));
        setLocationRelativeTo(null);
        Border outline = BorderFactory.createLineBorder(Color.black);


        /*Start menu*/
        startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
        JLabel label1 = new JLabel("          Welcome to my DayPlanner.");
        JLabel label2 = new JLabel("          Choose a command from the \"Commands\" menu above for adding");
        JLabel label3 = new JLabel("          an activity, searching activities, or quitting the program.");
        startPanel.add(label1, BorderLayout.NORTH);
        startPanel.add(label2, BorderLayout.CENTER);
        startPanel.add(label3, BorderLayout.SOUTH);
        startPanel.setVisible(true);
        add(startPanel, BorderLayout.NORTH);


        /*Add menu*/
        containerAdd = new JPanel();
        containerAdd.setLayout(new BorderLayout());

        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

        JPanel adding = new JPanel();
        adding.setLayout(new GridLayout(1, 2));
        JLabel labelA = new JLabel("Adding an activity:");
        adding.add(labelA);
        addPanel.add(adding, BorderLayout.WEST);

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
        JLabel labelB = new JLabel("Type:    ");
        typePanel.add(labelB);
        String[] activity = {"home", "school", "other"};
        JComboBox act = new JComboBox(activity);
        act.setSelectedIndex(2);
        act.addActionListener(new ComboBox());
        typePanel.add(act);
        JLabel fill1 = new JLabel("                          "
                + "                                      ");
        typePanel.add(fill1);
        JLabel fill2 = new JLabel("                      "
                + "                                          ");
        typePanel.add(fill2);
        addPanel.add(typePanel, BorderLayout.CENTER);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        JLabel labelC = new JLabel("Title: ");
        titlePanel.add(labelC);
        title = new JTextField(CHAR_PER_LINE);
        titlePanel.add(title);

        addPanel.add(titlePanel, BorderLayout.CENTER);

        JPanel sTimePanel = new JPanel();
        sTimePanel.setLayout(new BoxLayout(sTimePanel, BoxLayout.PAGE_AXIS));
        JLabel labelD = new JLabel("Starting time(year,month,day,hour,minute):");
        sTimePanel.add(labelD);
        startingTime = new JTextField(CHAR_PER_LINE);
        sTimePanel.add(startingTime);
        addPanel.add(sTimePanel, BorderLayout.CENTER);

        JPanel eTimePanel = new JPanel();
        eTimePanel.setLayout(new BoxLayout(eTimePanel, BoxLayout.PAGE_AXIS));
        JLabel labelE = new JLabel("Ending time(year,month,day,hour,minute):");
        eTimePanel.add(labelE);
        endingTime = new JTextField(CHAR_PER_LINE);
        eTimePanel.add(endingTime);
        addPanel.add(eTimePanel, BorderLayout.WEST);

        JPanel locPanel = new JPanel();
        locPanel.setLayout(new BoxLayout(locPanel, BoxLayout.PAGE_AXIS));
        JLabel labelF = new JLabel("Location:");
        locPanel.add(labelF);
        location = new JTextField(CHAR_PER_LINE);
        locPanel.add(location);
        addPanel.add(locPanel, BorderLayout.WEST);

        JPanel comPanel = new JPanel();
        comPanel.setLayout(new BoxLayout(comPanel, BoxLayout.Y_AXIS));
        JLabel labelG = new JLabel("Comment:");
        comPanel.add(labelG);
        comment = new JTextField(CHAR_PER_LINE);
        comPanel.add(comment);
        addPanel.add(comPanel, BorderLayout.WEST);

        addPanel.setVisible(true);
        containerAdd.add(addPanel, BorderLayout.WEST);

        /*Buttons for add*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(outline);
        buttonPanel.setLayout(new GridLayout(6, 1));

        JLabel filler1 = new JLabel(" ");
        buttonPanel.add(filler1);
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetListener());
        buttonPanel.add(resetButton);

        JLabel filler2 = new JLabel(" ");
        buttonPanel.add(filler2);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterListenerAdd());
        buttonPanel.add(enterButton);

        JLabel filler3 = new JLabel(" ");
        buttonPanel.add(filler3);

        JLabel filler4 = new JLabel(" ");
        buttonPanel.add(filler4);

        buttonPanel.setVisible(true);
        containerAdd.add(buttonPanel, BorderLayout.EAST);

        /*Message*/
        textPanel = new JPanel();
        textPanel.setBorder(outline);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));

        JLabel labelM = new JLabel("Message:");
        textPanel.add(labelM);
        messageDisplay = new JTextArea(LINES + 6, CHAR_PER_LINE);
        messageDisplay.setEditable(false);

        JScrollPane scrolledText = new JScrollPane(messageDisplay);
        scrolledText.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolledText.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        textPanel.add(scrolledText);
        textPanel.setVisible(false);
        containerAdd.add(textPanel, BorderLayout.SOUTH);

        containerAdd.setVisible(false);
        add(containerAdd, BorderLayout.WEST);

        /*search panel*/
        containerSearch = new JPanel();
        containerSearch.setLayout(new BoxLayout(containerSearch, BoxLayout.X_AXIS));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

        JPanel searching = new JPanel();
        searching.setLayout(new GridLayout(1, 2));
        JLabel labelAS = new JLabel("Searching activities:");
        searching.add(labelAS);
        searchPanel.add(searching, BorderLayout.WEST);

        JPanel typePanelS = new JPanel();
        typePanelS.setLayout(new BoxLayout(typePanelS, BoxLayout.X_AXIS));
        JLabel labelBS = new JLabel("Type:    ");
        typePanelS.add(labelBS);
        String[] activityS = {"home", "school", "other", ""};
        JComboBox acts = new JComboBox(activityS);
        acts.setSelectedIndex(2);
        acts.addActionListener(new ComboBox());
        typePanelS.add(acts);
        JLabel fill1S = new JLabel("                          "
                + "                                      ");
        typePanelS.add(fill1S);
        JLabel fill2S = new JLabel("                      "
                + "                                          ");
        typePanelS.add(fill2S);
        searchPanel.add(typePanelS, BorderLayout.CENTER);

        JPanel titlePanelS = new JPanel();
        titlePanelS.setLayout(new BoxLayout(titlePanelS, BoxLayout.X_AXIS));
        JLabel labelCS = new JLabel("Title: ");
        titlePanelS.add(labelCS);
        titleS = new JTextField(CHAR_PER_LINE);
        titlePanelS.add(titleS);
        searchPanel.add(titlePanelS, BorderLayout.CENTER);

        JPanel sTimePanelS = new JPanel();
        sTimePanelS.setLayout(new BoxLayout(sTimePanelS, BoxLayout.Y_AXIS));
        JLabel labelDS = new JLabel("Starting time(year,month,day,hour,minute):");
        sTimePanelS.add(labelDS);
        startingTimeS = new JTextField(CHAR_PER_LINE);
        sTimePanelS.add(startingTimeS);
        searchPanel.add(sTimePanelS, BorderLayout.CENTER);

        JPanel eTimePanelS = new JPanel();
        eTimePanelS.setLayout(new BoxLayout(eTimePanelS, BoxLayout.Y_AXIS));
        JLabel labelES = new JLabel("Ending time(year,month,day,hour,minute):");
        eTimePanelS.add(labelES);
        endingTimeS = new JTextField(CHAR_PER_LINE);
        eTimePanelS.add(endingTimeS);
        searchPanel.add(eTimePanelS, BorderLayout.WEST);

        searchPanel.setVisible(true);
        containerSearch.add(searchPanel, BorderLayout.WEST);

        /*Buttons for search*/
        JPanel buttonPanelS = new JPanel();
        buttonPanelS.setBorder(outline);
        buttonPanelS.setLayout(new GridLayout(6, 1));

        JLabel filler1S = new JLabel(" ");
        buttonPanelS.add(filler1S);
        JButton resetButtonS = new JButton("Reset");
        resetButtonS.addActionListener(new ResetListener());
        buttonPanelS.add(resetButtonS);

        JLabel filler2S = new JLabel(" ");
        buttonPanelS.add(filler2S);

        JButton enterButtonS = new JButton("Enter");
        enterButtonS.addActionListener(new EnterListenerSearch());
        buttonPanelS.add(enterButtonS);

        JLabel filler3S = new JLabel(" ");
        buttonPanelS.add(filler3S);

        JLabel filler4S = new JLabel(" ");
        buttonPanelS.add(filler4S);

        buttonPanelS.setVisible(true);
        containerSearch.add(buttonPanelS, BorderLayout.EAST);

        /*Message search*/
        textPanelS = new JPanel();
        textPanelS.setBorder(outline);
        textPanelS.setLayout(new BoxLayout(textPanelS, BoxLayout.PAGE_AXIS));

        JLabel labelMS = new JLabel("Search Results:");
        textPanelS.add(labelMS);
        searchDisplay = new JTextArea(LINES + 12, CHAR_PER_LINE);
        searchDisplay.setEditable(false);

        JScrollPane scrolledTextS = new JScrollPane(searchDisplay);
        scrolledTextS.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolledTextS.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        textPanelS.add(scrolledTextS);
        textPanelS.setVisible(false);
        add(textPanelS, BorderLayout.SOUTH);

        containerSearch.setVisible(false);
        add(containerSearch);

        /*Command Menu*/
        JMenu commandMenu = new JMenu("Commands");

        JMenuItem addChoice = new JMenuItem("Add");
        addChoice.addActionListener(new AddListener());
        commandMenu.add(addChoice);

        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(new SearchListener());
        commandMenu.add(searchChoice);

        JMenuItem quitChoice = new JMenuItem("Quit");
        quitChoice.addActionListener(new quitExit());
        commandMenu.add(quitChoice);

        /*the command Menu*/
        JMenuBar bar = new JMenuBar();
        bar.add(commandMenu);
        setJMenuBar(bar);

    }

    private class CheckOnExit extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            ConfirmWindow checkers = new ConfirmWindow();
            checkers.setVisible(true);
        }
    }

    private class ConfirmWindow extends JFrame implements ActionListener {

        public ConfirmWindow() {
            setSize(SMALL_WIDTH, SMALL_HEIGHT);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            getContentPane().setBackground(Color.LIGHT_GRAY);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JLabel confirmLabel = new JLabel(
                    "Are you sure you want to exit?");
            add(confirmLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.darkGray);
            buttonPanel.setLayout(new FlowLayout());

            JButton exitButton = new JButton("Yes");
            exitButton.addActionListener(this);
            buttonPanel.add(exitButton);

            JButton cancelButton = new JButton("No");
            cancelButton.addActionListener(this);
            buttonPanel.add(cancelButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();

            if (actionCommand.equals("Yes")) {
                System.exit(0);
            } else if (actionCommand.equals("No")) {
                dispose();//Destroys only the ConfirmWindow.
            } else {
                System.out.println("Unexpected Error in Confirm Window.");
            }
        }
    }

}

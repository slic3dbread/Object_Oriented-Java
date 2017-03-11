package dayplanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * DayPlanner class
 *
 * @author Benjamin McConville
 *
 * A class that adds and searches activities.
 *
 */
public class DayPlanner {

    public static final String[] types = new String[]{"home", "school", "other", "h", "s", "o"};

    /**
     * Array list containing all the activities actual sizes
     */
    ArrayList<Activity> activity = new ArrayList<>();

    /**
     * empty constructor for the DayPlanner
     */
    public DayPlanner() {

    }

    /**
     * Create a time object for the valid input
     * @param line the time string being passed
     * @return a Time class or null value
     */
    private Time getTime(String line) {
        String[] tokens = line.split("[ ,\n]+");
        if (tokens.length != 5) {
            return null;
        }
        for (int i = 0; i < 5; i++) {
            if (!tokens[i].matches("[-+]?[0-9]+")) {
                return null;
            }
        }
        int year = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int day = Integer.parseInt(tokens[2]);
        int hour = Integer.parseInt(tokens[3]);
        int minute = Integer.parseInt(tokens[4]);
        if (Time.timeOK(year, month, day, hour, minute)) {
            return new Time(year, month, day, hour, minute);
        } else {
            return null;
        }
    }

    /**
     * Add a valid home activity
     * @param home the Home class being added to the activity ArrayList 
     */
    protected void addHomeActivity(HomeActivity home) {
        activity.add(home);

    }

    /**
     * Add a valid home activity
     * @param school the School class being added to the activity ArrayList 
     */
    protected void addSchoolActivity(SchoolActivity school) {
        activity.add(school);
    }

    /**
     * Add a valid home activity
     * @param other the Other class being added to the activity ArrayList 
     */
    private void addOtherActivity(OtherActivity other) {
        activity.add(other);
    }

    /**
     * Parse the input file and add them to the array list
     *
     * @param file the input file that is being parsed
     *
     */
    public void addActivityFile(Scanner file) {
        String st, title, startTime, endTime, comment, location;
        StringTokenizer string;

        while (file.hasNext()) {
            st = file.nextLine();
            string = new StringTokenizer(st, "\"");
            string.nextToken();
            title = string.nextToken();

            switch (title) {
                case "school":
                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    if (string.hasMoreTokens()) {
                        title = string.nextToken();
                    } else {
                        title = "";
                    }

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    startTime = string.nextToken();

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    endTime = string.nextToken();

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    if (string.hasMoreTokens()) {
                        comment = string.nextToken();
                    } else {
                        comment = "";
                    }

                    addSchoolActivity(new SchoolActivity(title, getTime(startTime), getTime(endTime), comment));
                    break;
                case "home":
                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    if (string.hasMoreTokens()) {
                        title = string.nextToken();
                    } else {
                        title = "";
                    }

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    startTime = string.nextToken();

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    endTime = string.nextToken();

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    if (string.hasMoreTokens()) {
                        comment = string.nextToken();
                    } else {
                        comment = "";
                    }

                    addHomeActivity(new HomeActivity(title, getTime(startTime), getTime(endTime), comment));
                    break;
                case "other":
                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    if (string.hasMoreTokens()) {
                        title = string.nextToken();
                    } else {
                        title = "";
                    }

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    startTime = string.nextToken();

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    endTime = string.nextToken();

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    if (string.hasMoreTokens()) {
                        location = string.nextToken();
                    } else {
                        location = "";
                    }

                    st = file.nextLine();
                    string = new StringTokenizer(st, "\"");
                    string.nextToken();
                    if (string.hasMoreTokens()) {
                        comment = string.nextToken();
                    } else {
                        comment = "";
                    }

                    addOtherActivity(new OtherActivity(title, getTime(startTime), getTime(endTime), location, comment));
                    break;
                default:
                    System.out.println("There was an error");
            }

        }
    }

    /**
     * Checks the array list objects and writes to the output file
     *
     * @param outFile
     *
     */
    public void addActivityToFile(PrintWriter outFile) {
        String type;
        SchoolActivity school = null;
        HomeActivity home = null;
        OtherActivity other = null;

        for (int i = 0; activity.size() > i; i++) {
            if (activity.get(i) instanceof SchoolActivity) {
                outFile.println("type = \"school\"");
            } else if (activity.get(i) instanceof HomeActivity) {
                outFile.println("type = \"home\"");
            } else if (activity.get(i) instanceof OtherActivity) {
                outFile.println("type = \"other\"");
            }

            outFile.println("title = \"" + activity.get(i).getTitle() + "\"");
            outFile.println("start = \"" + activity.get(i).getStartingTime().getYear() + ", " + activity.get(i).getStartingTime().getMonth() + ", "
                    + activity.get(i).getStartingTime().getDay() + ", " + activity.get(i).getStartingTime().getHour() + ", " + activity.get(i).getStartingTime().getMinute() + "\"");
            outFile.println("end = \"" + activity.get(i).getEndingTime().getYear() + ", " + activity.get(i).getEndingTime().getMonth() + ", "
                    + activity.get(i).getEndingTime().getDay() + ", " + activity.get(i).getEndingTime().getHour() + ", " + activity.get(i).getEndingTime().getMinute() + "\"");
            if (activity.get(i) instanceof OtherActivity) {
                other = (OtherActivity) activity.get(i);
                outFile.println("location = \"" + other.getLocation() + "\"");
            }
            outFile.println("comment = \"" + activity.get(i).getComment() + "\"");
            outFile.println();

        }
        outFile.close();
    }

    /**
     * Create an activity from the input and add it to the appropriate list for
     * GUI
     *
     * @param type the type coming in
     * @param title the title of the activity
     * @param startTime the start time of the activity
     * @param endTime the end time of the activity
     * @param location the location of the activity
     * @param comment the comment of the activity
     * @return a string with an error message or Success
     */
    public String addActivityGui(String type, String title, String startTime, String endTime, String location, String comment) {

        if (getTime(startTime) == null || getTime(endTime) == null) {
            return "The time was inputted in the wrong form";
        }

        if (type.equalsIgnoreCase("home")) {
            addHomeActivity(new HomeActivity(title, getTime(startTime), getTime(endTime), comment));
        } else if (type.equalsIgnoreCase("school")) {
            addSchoolActivity(new SchoolActivity(title, getTime(startTime), getTime(endTime), comment));
        } else {
            addOtherActivity(new OtherActivity(title, getTime(startTime), getTime(endTime), location, comment));
        }

        return "Success!";
    }

    /**
     * Create an activity from the input and add it to the appropriate list for
     * command line
     *
     * @param input the choice between the menu input
     */
    public void addActivity(Scanner input) {
        String type = "";
        do {
            System.out.print("Enter an activity type (home, school, or other)> ");
            type = input.nextLine();
        } while (!matchedKeyword(type, types));

        System.out.print("Enter a title> ");
        String title = input.nextLine();

        Time startingTime = null;
        do {
            System.out.print("Enter a starting time (year, month, day, hour, minute)> ");
            startingTime = getTime(input.nextLine());
        } while (startingTime == null);

        Time endingTime = null;
        do {
            System.out.print("Enter an ending time (year, month, day, hour, minute)> ");
            endingTime = getTime(input.nextLine());
        } while (endingTime == null);

        String location = "";
        if (type.equalsIgnoreCase("other") || type.equalsIgnoreCase("o")) {
            System.out.print("Enter a location> ");
            location = input.nextLine();
        }

        System.out.print("Enter a line of comment> ");
        String comment = input.nextLine();

        if (type.equalsIgnoreCase("home") || type.equalsIgnoreCase("h")) {
            addHomeActivity(new HomeActivity(title, startingTime, endingTime, comment));
        } else if (type.equalsIgnoreCase("school") || type.equalsIgnoreCase("s")) {
            addSchoolActivity(new SchoolActivity(title, startingTime, endingTime, comment));
        } else {
            addOtherActivity(new OtherActivity(title, startingTime, endingTime, location, comment));
        }
    }

    /**
     * Check if a keyword is on a list of tokens
     * @param keyword a string of the keyword
     * @param tokens an array of all the keywords
     * @return 
     */
    private boolean matchedKeyword(String keyword, String[] tokens) {
        for (String token : tokens) {
            if (keyword.equalsIgnoreCase(token)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Check if all keywords are in a string  
     * @param keywords an array of all the keywords
     * @param title the title
     * @return 
     */
    private boolean matchedKeywords(String[] keywords, String title) {
        String[] tokens = title.split("[ ,\n]+");
        for (String keyword : keywords) {
            if (!matchedKeyword(keyword, tokens)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Search for all other activities that satisfy a search request
     * @param startingTime the start time search
     * @param endingTime the end time search
     * @param keywords the keywords to search
     */
    private void searchHomeActivities(Time startingTime, Time endingTime, String[] keywords) {
        HomeActivity test = new HomeActivity();
        for (int i = 0; activity.size() > i; i++) {
            if ((activity.get(i).getClass().equals(test.getClass())) && (startingTime == null || activity.get(i).getStartingTime().compareTo(startingTime) >= 0)
                    && (endingTime == null || activity.get(i).getEndingTime().compareTo(endingTime) <= 0)
                    && (keywords == null || matchedKeywords(keywords, activity.get(i).getTitle()))) {
                System.out.println(activity.get(i));
            }
        }
    }

    /**
     * Search for all other activities that satisfy a search request
     * @param startingTime the start time search
     * @param endingTime the end time search
     * @param keywords the keywords to search
     */
    private void searchSchoolActivities(Time startingTime, Time endingTime, String[] keywords) {
        SchoolActivity test = new SchoolActivity();
        for (int i = 0; activity.size() > i; i++) {
            if ((activity.get(i).getClass().equals(test.getClass())) && (startingTime == null || activity.get(i).getStartingTime().compareTo(startingTime) >= 0)
                    && (endingTime == null || activity.get(i).getEndingTime().compareTo(endingTime) <= 0)
                    && (keywords == null || matchedKeywords(keywords, activity.get(i).getTitle()))) {
                System.out.println(activity.get(i));
            }
        }
    }

    /**
     * Search for all other activities that satisfy a search request
     * @param startingTime the start time search
     * @param endingTime the end time search
     * @param keywords the keywords to search
     */
    private void searchOtherActivities(Time startingTime, Time endingTime, String[] keywords) {
        OtherActivity test = new OtherActivity();
        for (int i = 0; activity.size() > i; i++) {
            if ((activity.get(i).getClass().equals(test.getClass())) && (startingTime == null || activity.get(i).getStartingTime().compareTo(startingTime) >= 0)
                    && (endingTime == null || activity.get(i).getEndingTime().compareTo(endingTime) <= 0)
                    && (keywords == null || matchedKeywords(keywords, activity.get(i).getTitle()))) {
                System.out.println(activity.get(i));
            }
        }
    }
    
    /**
     * Search for all other activities that satisfy a search request
     * For GUI interface
     * @param startingTime the start time search
     * @param endingTime the end time search
     * @param keywords the keywords to search
     * @return a string output of the activity
     */
    private String searchHomeActivitiesG(Time startingTime, Time endingTime, String[] keywords) {
        HomeActivity test = new HomeActivity();
        for (int i = 0; activity.size() > i; i++) {
            if ((activity.get(i).getClass().equals(test.getClass())) && (startingTime == null || activity.get(i).getStartingTime().compareTo(startingTime) >= 0)
                    && (endingTime == null || activity.get(i).getEndingTime().compareTo(endingTime) <= 0)
                    && (keywords == null || matchedKeywords(keywords, activity.get(i).getTitle()))) {
                return activity.get(i).toString();
            }
        }
        return "There is no activity with these constraints";
    }

    /**
     * Search for all other activities that satisfy a search request
     * For GUI interface
     * @param startingTime the start time search
     * @param endingTime the end time search
     * @param keywords the keywords to search
     * @return a string output of the activity
     */
    private String searchSchoolActivitiesG(Time startingTime, Time endingTime, String[] keywords) {
        SchoolActivity test = new SchoolActivity();
        for (int i = 0; activity.size() > i; i++) {
            if ((activity.get(i).getClass().equals(test.getClass())) && (startingTime == null || activity.get(i).getStartingTime().compareTo(startingTime) >= 0)
                    && (endingTime == null || activity.get(i).getEndingTime().compareTo(endingTime) <= 0)
                    && (keywords == null || matchedKeywords(keywords, activity.get(i).getTitle()))) {
                return activity.get(i).toString();
            }
        }
        return "There is no activity with these constraints";
    }

    /**
     * Search for all other activities that satisfy a search request
     * For GUI interface
     * @param startingTime the start time search
     * @param endingTime the end time search
     * @param keywords the keywords to search
     * @return a string output of the activity
     */
    private String searchOtherActivitiesG(Time startingTime, Time endingTime, String[] keywords) {
        OtherActivity test = new OtherActivity();
        for (int i = 0; activity.size() > i; i++) {
            if ((activity.get(i).getClass().equals(test.getClass())) && (startingTime == null || activity.get(i).getStartingTime().compareTo(startingTime) >= 0)
                    && (endingTime == null || activity.get(i).getEndingTime().compareTo(endingTime) <= 0)
                    && (keywords == null || matchedKeywords(keywords, activity.get(i).getTitle()))) {
                return activity.get(i).toString();
            }
        }
        return "There is no activity with these constraints";
    }

    /**
     * Gather a search request and find the matched activities in the related
     * list(s)
     *
     * @param type the type of activity
     * @param title the title of the activity
     * @param startTime the start time of the activity
     * @param endTime the end time of the activity
     * @return a string which is either an error message or an activity
     */
    public String searchActivitiesGui(String type, String title, String startTime, String endTime) {

        if (startTime.equals("") || endTime.equals("")) {
            System.out.println("");
        } else if (getTime(startTime) == null || getTime(endTime) == null) {
            return "Time was inputted wrong(either clear it or input it in the right form";
        }

        String[] keywords = null;

        if (!title.isEmpty()) {
            keywords = title.split("[ ,\n]+");
        }

        // search for matched activities
        if (type.isEmpty() || type.equalsIgnoreCase("home")) {
            return "Matched activities: \n" + searchHomeActivitiesG(getTime(startTime), getTime(endTime), keywords);
        }

        if (type.isEmpty() || type.equalsIgnoreCase("school")) {
            return "Matched activities: \n" + searchSchoolActivitiesG(getTime(startTime), getTime(endTime), keywords);
        }

        if (type.isEmpty() || type.equalsIgnoreCase("other")) {
            return "Matched activities: \n" + searchOtherActivitiesG(getTime(startTime), getTime(endTime), keywords);
        }
        
        return "There was an unknown error";
    }

    /**
     * Gather a search request and find the matched activities in the related
     * list(s)
     *
     * @param input the users input form the menu
     */
    public void searchActivities(Scanner input) {
        String type = "";
        do {
            System.out.print("Enter an activity type (home, school, or other)> ");
            type = input.nextLine();
        } while (!type.isEmpty() && !matchedKeyword(type, types));

        Time startingTime = null;
        do {
            System.out.print("Enter a starting time (year, month, day, hour, minute)> ");
            String line = input.nextLine();
            if (line.isEmpty()) {
                break;
            } else {
                startingTime = getTime(line);
            }
        } while (startingTime == null);

        Time endingTime = null;
        do {
            System.out.print("Enter an ending time (year, month, day, hour, minute)> ");
            String line = input.nextLine();
            if (line.isEmpty()) {
                break;
            } else {
                endingTime = getTime(line);
            }
        } while (endingTime == null);

        System.out.print("Enter title keywords: ");
        String[] keywords = null;
        String line = input.nextLine();
        if (!line.isEmpty()) {
            keywords = line.split("[ ,\n]+");
        }

        // search for matched activities
        System.out.println("Matched activities: ");
        if (type.isEmpty() || type.equalsIgnoreCase("home") || type.equalsIgnoreCase("h")) {
            searchHomeActivities(startingTime, endingTime, keywords);
        }

        if (type.isEmpty() || type.equalsIgnoreCase("school") || type.equalsIgnoreCase("s")) {
            searchSchoolActivities(startingTime, endingTime, keywords);
        }

        if (type.isEmpty() || type.equalsIgnoreCase("other") || type.equalsIgnoreCase("o")) {
            searchOtherActivities(startingTime, endingTime, keywords);
        }
    }

}

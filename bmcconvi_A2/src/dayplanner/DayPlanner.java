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

    public DayPlanner() {

    }

    /*
     * Create a time object for the valid input
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

    /*
     * Add a valid home activity
     */
    protected void addHomeActivity(HomeActivity home) {
        activity.add(home);

    }

    /*
     * Add a valid school activity
     */
    protected void addSchoolActivity(SchoolActivity school) {
        activity.add(school);
    }

    /*
     * Add a valid other activity
     */
    private void addOtherActivity(OtherActivity other) {
        activity.add(other);
    }
    
    /**
     * Parse the input file and add them to the array list
     *
     * @param file
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
     * Create an activity from the input and add it to the appropriate list
     *
     * @param input
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

    /* 
     * Check if a keyword is on a list of tokens
     */
    private boolean matchedKeyword(String keyword, String[] tokens) {
        for (String token : tokens) {
            if (keyword.equalsIgnoreCase(token)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Check if all keywords are in a string 
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

    /*
     * Search for all home activities that satisfy a search request
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

    /*
     * Search for all school activities that satisfy a search request
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

    /*
     * Search for all other activities that satisfy a search request
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
     * Gather a search request and find the matched activities in the related
     * list(s)
     *
     * @param input
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

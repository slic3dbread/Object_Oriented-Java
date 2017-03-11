/*
 * Name: Benjamin McConville
 * Date: 10-15-2014
 */
package a1;

import java.util.Scanner;

/**
 * School Activity
 *
 * @author Benjamin McConville
 */
public class SchoolActivity {

    private String title;
    private String comment;
    private Time startTime;
    private Time endTime;

    /**
     * Sets the initial values of the activity
     */
    public SchoolActivity() {
        title = "";
        comment = "";
        startTime = new Time();
        endTime = new Time();
    }

    /**
     * This Method outputs all the values of the Activity.
     *
     * @return A string containing the activity
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append("Title: ");
        result.append(title);
        result.append(newLine);
        result.append("Start to End time(Year-Month-Day-Hour-Minute): ");
        result.append(startTime.getYear()).append("-").append(startTime.getMonth()).append("-").append(startTime.getDay()).append("-").append(startTime.getHour()).append("-").append(startTime.getMinute());
        result.append(newLine);
        result.append(endTime.getYear()).append("-").append(endTime.getMonth()).append("-").append(endTime.getDay()).append("-").append(endTime.getHour()).append("-").append(endTime.getMinute());
        result.append(newLine);
        result.append("Comment: ");
        result.append(comment);
        result.append(newLine);

        return result.toString();
    }

    /**
     * Checks if this activity has a word inside it that is being searched
     *
     * @param searchString The word being searched.
     * @return If it is found or not it will return false or true.
     */
    public boolean equalsValue(String searchString) {
        String temp;
        temp = title;
        String[] tokenize = temp.split(" ");
        int determine = 0;

        for (String Token : tokenize) {
            if (Token.equalsIgnoreCase(searchString)) {
                determine = 1;
            }
        }
        if (determine == 1) {
            return true;
        } else {
            return searchString.isEmpty();
        }
    }

    /**
     * Returns the title
     *
     * @return the title is being returned
     */
    public String getTitle() {

        return title;
    }

    /**
     * Returns the StartTime
     *
     * @return the StartTime is being returned
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Returns the EndTime
     *
     * @return the endTime is being returned
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Returns the Comment
     *
     * @return the Comment is being returned
     */
    public String getComment() {
        return comment;
    }

    /**
     * Gets the Start Time of the Activity
     */
    public void setStartTime() {
        Scanner in = new Scanner(System.in);
        int year, month, day, hour, minute;
        System.out.println("Enter the year:(four digits)");
        year = in.nextInt();
        System.out.println("Enter the month:(two digits)");
        month = in.nextInt();
        System.out.println("Enter the day of the month: (two digits)");
        day = in.nextInt();
        System.out.println("Enter the hour:(two digits)");
        hour = in.nextInt();
        System.out.println("Enter the minute: (two digits)");
        minute = in.nextInt();

        startTime = new Time(year, month, day, hour, minute);

    }

    /**
     * Gets the End Time of the Activity
     */
    public void setEndTime() {
        Scanner in = new Scanner(System.in);
        int year, month, day, hour, minute;
        System.out.println("Enter the year:(four digits)");
        year = in.nextInt();
        System.out.println("Enter the month:(two digits)");
        month = in.nextInt();
        System.out.println("Enter the day of the month: (two digits)");
        day = in.nextInt();
        System.out.println("Enter the hour:(two digits)");
        hour = in.nextInt();
        System.out.println("Enter the minute: (two digits)");
        minute = in.nextInt();

        endTime = new Time(year, month, day, hour, minute);

    }

    /**
     * Changes the Activities title
     *
     * @param title The new Title of the Activity
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Changes the Comment of the Activity
     *
     * @param comment The new Comment of the Activity
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}

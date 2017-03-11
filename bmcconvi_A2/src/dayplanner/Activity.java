/*
 * Name: Benjamin McConville
 * Date: 09-09-2014
 */
package dayplanner;

/**Super Activity Class of OtherActivity, SchoolActivity and HomeActivity
 *
 * @author Benjamin McConville
 */
public class Activity {

    protected String title;       // short description of the activity
    protected Time startingTime;  // starting time of the activity
    protected Time endingTime;    // ending time of the activity
    protected String comment;     // additional detail for the activity

    public Activity(){
        
    }
    
    /**
     * Create a home activity with all the required fields
     *
     * @param title
     * @param startingTime
     * @param endingTime
     * @param comment
     */
    public Activity(String title, Time startingTime, Time endingTime, String comment) {
        if (valid(startingTime, endingTime)) {
            this.title = title;
            this.startingTime = startingTime;
            this.endingTime = endingTime;
            this.comment = comment;
        } else {
            System.out.println("Invalid times for HomeActivity");
            System.exit(0);
        }
    }


    /**
     * Check the validity for a potential activity
     *
     * @param startingTime
     * @param endingTime
     * @return
     */
    public static boolean valid(Time startingTime, Time endingTime) {
        return (startingTime != null) && (endingTime != null);
    }

    /**
     * Set a new value for title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set a new value for starting time
     *
     * @param startingTime
     */
    public void setStartingTime(Time startingTime) {
        if (startingTime == null) {
            System.out.println("Invalid starting time");
            System.exit(0);
        } else {
            this.startingTime = startingTime;
        }
    }

    /**
     * Set a new value for ending time
     *
     * @param endingTime
     */
    public void setEndingTime(Time endingTime) {
        if (endingTime == null) {
            System.out.println("Invalid ending time");
            System.exit(0);
        }
        this.endingTime = endingTime;
    }

    /**
     * Set a new value for comment
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Get the value of title
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the value of starting time
     *
     * @return
     */
    public Time getStartingTime() {
        return startingTime;
    }

    /**
     * Get the value of ending time
     *
     * @return
     */
    public Time getEndingTime() {
        return endingTime;
    }

    /**
     * Get the value of comment
     *
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * Check for the equality of two home activities
     *
     * @param other
     * @return
     */
    public boolean equals(Activity other) {
        if (other == null) {
            return false;
        } else {
            return title.equals(other.title)
                    && startingTime.equals(other.startingTime)
                    && endingTime.equals(other.endingTime)
                    && comment.equals(other.comment);
        }
    }

    /**
     * Show the content of a home activity in a string
     *
     * @return
     */
    @Override
    public String toString() {
        return title + ": " + startingTime + " to " + endingTime + ", " + comment;
    }

}

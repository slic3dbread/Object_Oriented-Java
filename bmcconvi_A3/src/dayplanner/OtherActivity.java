package dayplanner;

/**
 * OtherActivity class
 *
 * @author Benjamin McConville
 *
 * A class for representing and comparing other activities.
 *
 */
public class OtherActivity extends Activity {

    private String location;    // location of the activity

    public OtherActivity() {

    }

    public OtherActivity(String title, Time startingTime, Time endingTime, String comment, String location) {
        super(title, startingTime, endingTime, comment);
        this.location = location;
    }


    /**
     * Set a new value for location
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the value of location
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    public boolean equals(OtherActivity other) {
        if (other == null) {
            return false;
        } else {
            return super.equals(other) && location.equals(other.location);
        }
    }

    /**
     * Show the content of an other activity in a string
     *
     * @return
     */
    @Override
    public String toString() {
        Time Start = getStartingTime();
        Time End = getEndingTime();
        return getTitle() + ": " + Start + " to " + End + ", " + location + ", " + getComment();
    }

    public static void main(String[] args) {
        Time startingTime = new Time(2009, 10, 22, 12, 30);
        Time endingTime = new Time(2009, 10, 22, 13, 20);
        OtherActivity activity = new OtherActivity("Lunch", startingTime, endingTime, "Time Horton", "");
        System.out.println("Other Activity: " + activity);
    }
}

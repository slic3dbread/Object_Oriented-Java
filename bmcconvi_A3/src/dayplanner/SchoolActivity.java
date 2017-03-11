package dayplanner;

/**
 * SchoolActivity class
 *
 * @author Benjamin McConville
 *
 * A class for representing and comparing school activities.
 *
 */
public class SchoolActivity extends Activity {

    public SchoolActivity() {

    }

    public SchoolActivity(String title, Time startingTime, Time endingTime, String comment) {
        super(title, startingTime, endingTime, comment);
    }


    public static void main(String[] args) {
        Time startingTime = new Time(2009, 10, 22, 12, 30);
        Time endingTime = new Time(2009, 10, 22, 13, 20);
        SchoolActivity activity = new SchoolActivity("Java class", startingTime, endingTime, "");
        System.out.println("School Activity: " + activity);
    }

}

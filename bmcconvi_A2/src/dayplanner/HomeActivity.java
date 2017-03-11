package dayplanner;

/**
 * HomeActivity class
 *
 * @author Benjamin McConville
 *
 * A class for representing and comparing home activities.
 *
 */
public class HomeActivity extends Activity {

    public HomeActivity() {

    }

    public HomeActivity(String title, Time startingTime, Time endingTime, String comment) {
        super(title, startingTime, endingTime, comment);
    }

    public static void main(String[] args) {
        Time startingTime = new Time(2009, 10, 22, 12, 30);
        Time endingTime = new Time(2009, 10, 22, 13, 20);
        HomeActivity activity = new HomeActivity("Call friends", startingTime, endingTime, "");
        System.out.println("Home Activity: " + activity);
    }

}

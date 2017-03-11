package dayplanner;

/**
 * Time class
 *
 * @author Benjamin McConville
 *
 * A class for representing and comparing different times.
 *
 */
public class Time {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    /**
     * Create a time object with all the requirement elements
     *
     * @param year the year 
     * @param month the month
     * @param day the day
     * @param hour the hour
     * @param minute the minute
     */
    public Time(int year, int month, int day, int hour, int minute) {
        if (timeOK(year, month, day, hour, minute)) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
        } else {
            System.out.println("Fatal Error for Time");
            System.exit(0);
        }
    }

    /**
     * Create a time object with no arguments
     */
    public Time() {
        year = 0;
        month = 0;
        day = 0;
        hour = 0;
        minute = 0;
    }

    /**
     * Set a new value for year
     *
     * @param year the year to be set
     */
    public void setYear(int year) {
        if (year >= 0) {
            this.year = year;
        } else {
            System.out.println("Invalid value for year: " + year);
            System.exit(0);
        }
    }

    /**
     * Set a new value for month
     *
     * @param month the month to be set
     */
    public void setMonth(int month) {
        if (month >= 0) {
            this.month = month;
        } else {
            System.out.println("Invalid value for month: " + month);
            System.exit(0);
        }
    }

    /**
     * Set a new value for day
     *
     * @param day the day to be set
     */
    public void setDay(int day) {
        if (day >= 0) {
            this.day = day;
        } else {
            System.out.println("Invalid value for day: " + day);
            System.exit(0);
        }
    }

    /**
     * Set a new value for hour
     *
     * @param hour the hour to be set
     */
    public void setHour(int hour) {
        if (hour >= 0) {
            this.hour = hour;
        } else {
            System.out.println("Invalid value for hour: " + hour);
            System.exit(0);
        }
    }

    /**
     * Set a new value for minute
     *
     * @param minute the minute to be set
     */
    public void setMinute(int minute) {
        if (minute >= 0) {
            this.minute = minute;
        } else {
            System.out.println("Invalid value for minute: " + minute);
            System.exit(0);
        }
    }

    /**
     * Check for equality with a given time object
     *
     * @param other the object of another class to be checked
     * @return true if they are equal and false if they are not
     */
    public boolean equals(Time other) {
        if (other == null) {
            return false;
        } else {
            return year == other.year
                    && month == other.month
                    && day == other.day
                    && hour == other.hour
                    && minute == other.minute;
        }
    }

    /**
     * Compare two time objects for ordering
     *
     * @param other an other time class
     * @return  compared int
     */
    public int compareTo(Time other) {
        if (year < other.year) {
            return -1;
        } else if (year > other.year) {
            return 1;
        } else if (month < other.month) {
            return -1;
        } else if (month > other.month) {
            return 1;
        } else if (day < other.day) {
            return -1;
        } else if (day > other.day) {
            return 1;
        } else if (hour < other.hour) {
            return -1;
        } else if (hour > other.hour) {
            return 1;
        } else if (minute < other.minute) {
            return -1;
        } else if (minute > other.minute) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Get the value for year
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Get the value for month
     *
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Get the value for day
     *
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Get the value for hour
     *
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Get the value for minute
     *
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Show the content of a time object in a string
     *
     * @return the class as a string
     */
    @Override
    public String toString() {
        return hour + ":" + minute + ", " + month + "/" + day + "/" + year;
    }

    /**
     * Validate all the fields for a time object
     *
     * @param year year
     * @param month month
     * @param day day
     * @param minute minute
     * @param hour hour
     * @return true if the time is all positive
     */
    static public boolean timeOK(int year, int month, int day, int hour, int minute) {
        return (year >= 0) && (month >= 0) && (day >= 0)
                && (hour >= 0) && (minute >= 0);
    }

    public static void main(String[] args) {
        Time startingTime = null;
        if (Time.timeOK(2014, 10, 22, 12, 30)) {
            startingTime = new Time(2014, 10, 22, 12, 30);
        }

        Time endingTime = null;
        if (Time.timeOK(2014, 10, 22, 13, 20)) {
            endingTime = new Time(2014, 10, 22, 13, 20);
        }

        if (startingTime != null && endingTime != null) {
            int result = startingTime.compareTo(endingTime);
            if (result < 0) {
                System.out.println(startingTime + " precedes " + endingTime);
            } else if (result > 0) {
                System.out.println(startingTime + " follows " + endingTime);
            } else {
                System.out.println(startingTime + " equals " + endingTime);
            }
        }
    }
}

/*
 * Name: Benjamin McConville
 * Date: 10-15-2014
 */
package a1;

/**
 * Time
 *
 * @author Benjamin McConville
 */
public class Time {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    /**
     * The default constructor of the Time
     */
    public Time() {

    }

    /**
     * Changes the time
     *
     * @param year The new year
     * @param month The new Month
     * @param day The new Day
     * @param hour The new Hour
     * @param minute The new Minute
     */
    public Time(int year, int month, int day, int hour, int minute) {

        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Compares the times to see if they are the same
     *
     * @param comparedTime The searched Time that is being compared
     * @return Outputs True if the comparedTime is not set or if it is the same
     */
    public boolean compareTo(Time comparedTime) {

        if ((comparedTime.getYear() == 0) && (comparedTime.getMonth() == 0) && (comparedTime.getDay() == 0) && (comparedTime.getHour() == 0) && (comparedTime.getMinute() == 0)) {
            return true;
        } else {
            return (year == comparedTime.getYear()) && (month == comparedTime.getMonth()) && (day == comparedTime.getDay()) && (hour == comparedTime.getHour()) && (minute == comparedTime.getMinute());
        }
    }

    /**
     * If all the times are equal to zero the time is not set
     *
     * @return true if they are equals to zero
     */
    public boolean equalsZero() {
        return year == 0 && month == 0 && day == 0 && hour == 0 && minute == 0;
    }

    /**
     * Sets all the times to zero
     */
    public void setToZero() {
        year = 0;
        month = 0;
        day = 0;
        hour = 0;
        minute = 0;

    }

    /**
     * Outputs the Year
     *
     * @return the Year is being Returned
     */
    public int getYear() {
        return year;
    }

    /**
     * Outputs the Month
     *
     * @return the Month is being Returned
     */
    public int getMonth() {
        return month;
    }

    /**
     * Outputs the Day
     *
     * @return the Day is being Returned
     */
    public int getDay() {
        return day;
    }

    /**
     * Outputs the Hour
     *
     * @return the Hour is being Returned
     */
    public int getHour() {
        return hour;
    }

    /**
     * Outputs the Minute
     *
     * @return the Minute is being Returned
     */
    public int getMinute() {
        return minute;
    }

}

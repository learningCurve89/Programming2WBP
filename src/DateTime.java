import java.util.Scanner;

/**
 * This class is used to set the date and time that the user will need the meeting rooms at
 */
public class DateTime {
    private int day;
    private int month;
    private int year;

    private double startTime;
    private double endTime;

    /**
     * Constructs the date and time
     */
    public DateTime() {
        day = 0;
        month = 0;
        year = 0;
        startTime = 0;
        endTime = 0;
    }

    /**
     * Constructs the date and time with parameters
     *
     * @param day       the desired day in format dd
     * @param month     the desired month in format MM
     * @param year      the desired year in format YYYY
     * @param startTime the desired start time in 24hours format
     * @param endTime   the desired end time calculated after user input of duration
     */
    public DateTime(int day, int month, int year, double startTime, double endTime) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the date and time to a readable format
     *
     * @return day, month, year, startTime, endTime
     */
    public String toString() {
        return day + "-" + month + "-" + year + "," + startTime + "," + endTime;
    }

    /**
     * prompts the user to enter date and time elements and creates a desired date and time object
     *
     * @param loginScanner to read the user input
     * @return desiredDateTime
     */
    public DateTime selectDateTime(Scanner loginScanner) {
        System.out.println("Enter desired day: ");
        int day = loginScanner.nextInt();
        System.out.println("Enter desired month: ");
        int month = loginScanner.nextInt();
        System.out.println("Enter desired year: ");
        int year = loginScanner.nextInt();
        System.out.println("Enter desired time");
        double startTime = loginScanner.nextDouble();
        System.out.println("Enter duration: ");
        double duration = loginScanner.nextDouble();
        double endTime = startTime + duration;
        DateTime desiredDateTime = new DateTime(day, month, year, startTime, endTime);
        return desiredDateTime;
    }

    /**
     * gets the day
     *
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * sets the day
     *
     * @param day the desired day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * gets the month
     *
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * sets the month
     *
     * @param month the desired month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * gets the year
     *
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * sets the year
     *
     * @param year the desired year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * gets the start time
     *
     * @return startTime
     */
    public double getStartTime() {
        return startTime;
    }

    /**
     * sets the startTime
     *
     * @param startTime the desired start time
     */
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    /**
     * gets the end time
     *
     * @return endTime
     */
    public double getEndTime() {
        return endTime;
    }

    /**
     * sets the end time
     *
     * @param endTime the desired end time
     */
    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }
}

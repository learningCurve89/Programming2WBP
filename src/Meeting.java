import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a meeting
 */
public class Meeting {
    public static ArrayList<Meeting> meetingArrayList = new ArrayList<>();
    private static Scanner meetingScanner = new Scanner(System.in);
    private String date;
    private double start;
    private double duration;
    private String contact;
    public String meetingPointName;

    /**
     * a meeting object
     */
    public Meeting() {
        date = "";
        start = 0;
        duration = 0;
        contact = "";
        meetingPointName = null;
    }

    /**
     * a meeting object with parameters
     *
     * @param date             the date of the meeting
     * @param start            the start time of the meeting
     * @param duration         the duration of the meeting
     * @param contact          the contact of the user who booked the meeting
     * @param meetingPointName the meeting point where the meeting is going to take place
     */
    public Meeting(String date, double start, double duration, String contact, String meetingPointName) {
        this.date = date;
        this.start = start;
        this.duration = duration;
        this.contact = contact;
        this.meetingPointName = meetingPointName;
    }

    /**
     * a method that returns a printable version of the object
     *
     * @return date, start, duration, contact, meetingPointName
     */
    public String toString() {
        return date + "," + start + "," + duration + "," + contact + "," + meetingPointName;
    }

    /**
     * returns a user friendly version of the object
     *
     * @return Date: date, Start: start, Duration: duration, Item: meetingPointName
     */
    public String printMeetingToUser() {
        return "Date: " + date + ", " + "Start: " + start + ", " + "Duration: " + duration + ", " + "Item: " + meetingPointName;
    }

    /**
     * adds a meeting to the meetingArrayList
     *
     * @param desiredDateTime the dateTime object which represents the user desired date and time
     * @param userInput       the user input to select the desired meeting point
     */
    public static void addMeeting(DateTime desiredDateTime, int userInput) {
        int day = desiredDateTime.getDay();
        int month = desiredDateTime.getMonth();
        int year = desiredDateTime.getYear();
        String date = day + "-" + month + "-" + year;
        double start = desiredDateTime.getStartTime();
        double endTime = desiredDateTime.getEndTime();
        double duration = endTime - start;
        System.out.println("Please enter your email address: ");
        String contact = meetingScanner.next();
        String meetingPointName = MeetingPoint.meetingPointArrayList.get(userInput - 1).getName();
        Meeting meeting = new Meeting(date, start, duration, contact, meetingPointName);
        meetingArrayList.add(meeting);
        printMeeting();
        System.out.println(meeting.printMeetingToUser());
    }

    /**
     * removes a meeting from the meetingArrayList
     *
     * @param meetingToDelete the meeting selected by the user
     */
    public static void deleteMeeting(Meeting meetingToDelete) {
        meetingArrayList.remove(meetingToDelete);
        try {
            FileImportOutput.meetingOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints meeting in the meetings database file
     */
    public static void printMeeting() {
        try {
            FileImportOutput.meetingOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * parses a meeting date and creates a meeting date time object
     *
     * @return meetingDateTime
     */
    public DateTime parseDate() {
        String date = getDate();
        double start = getStart();
        double duration = getDuration();
        String[] separation = date.split("-");
        int day = Integer.parseInt(separation[0]);
        int month = Integer.parseInt(separation[1]);
        int year = Integer.parseInt(separation[2]);
        double endTime = start + duration;
        DateTime meetingDateTime = new DateTime(day, month, year, start, endTime);
        return meetingDateTime;
    }

    /**
     * gets the date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * sets the date
     *
     * @param date the date of the meeting
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * gets the start time
     *
     * @return start
     */
    public double getStart() {
        return start;
    }

    /**
     * sets the start time
     *
     * @param start the start time of the meeting
     */
    public void setStart(double start) {
        this.start = start;
    }


    /**
     * gets the duration of the meeting
     *
     * @return duration
     */
    public double getDuration() {
        return duration;
    }

    /**
     * sets the duration of the meeting
     *
     * @param duration duration of the meeting
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * gets the contact of the user that booked the meeting
     *
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * sets the contact
     *
     * @param contact the contact of the user
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * gets the meeting point name to link the meeting to the meeting Points text file
     *
     * @return meetingPointName
     */
    public String getMeetingPointName() {
        return meetingPointName;
    }

    /**
     * sets the meeting poin name
     *
     * @param meetingPointName the name of the meeting point that was booked
     */
    public void setMeetingPointName(String meetingPointName) {
        this.meetingPointName = meetingPointName;
    }
}

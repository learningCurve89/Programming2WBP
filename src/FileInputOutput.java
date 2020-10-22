import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class inputs and outputs the txt files
 */
class FileImportOutput {
    private static Scanner meetingPoints;
    private static Scanner meetings;
    private static Scanner users;
    private static ArrayList<MeetingPoint> meetingPointArrayList = MeetingPoint.meetingPointArrayList;
    private static ArrayList<Meeting> meetingsArrayList = Meeting.meetingArrayList;
    private static ArrayList<User> userArrayList = User.userArrayList;


    /**
     * reads the file from MeetingPoints.txt
     *
     * @throws FileNotFoundException
     */
    public static void readFile() throws FileNotFoundException {
        File inputData = new File("MeetingPoints.txt");
        meetingPoints = new Scanner(inputData);
    }

    /**
     * reads the file from MeetingsDB.txt
     *
     * @throws FileNotFoundException
     */
    public static void readMeetingFile() throws FileNotFoundException {
        File inputData = new File("MeetingsDB.txt");
        meetings = new Scanner(inputData);
    }

    /**
     * reads file from UserDB.txt
     *
     * @throws FileNotFoundException
     */
    public static void readUserFile() throws FileNotFoundException {
        File inputData = new File("UsersDB.txt");
        users = new Scanner(inputData);
    }

    /**
     * splits the MeetingPoints file using split() with comma as regex
     * calls the parseMeetingPointString() method
     */
    public static void splitFile() {
        String[] commaSeparatedString = null;
        while (meetingPoints.hasNextLine()) {
            String meetingPointString = meetingPoints.nextLine();
            String ignoreCaseMeetingPointString = meetingPointString.toUpperCase();
            commaSeparatedString = ignoreCaseMeetingPointString.split(",");
            meetingPointArrayList.add(parseMeetingPointString(commaSeparatedString));
        }
    }

    /**
     * splits the MeetingsDB file using split() with comma as regex
     * calls the parseMeetingString() method
     */
    public static void splitMeetingFile() {
        String[] commaSeparatedString = null;
        while (meetings.hasNextLine()) {
            String meetingPointString = meetings.nextLine();
            String ignoreCaseMeetingPointString = meetingPointString.toUpperCase();
            commaSeparatedString = ignoreCaseMeetingPointString.split(",");
            meetingsArrayList.add(parseMeetingString(commaSeparatedString));
        }
    }

    /**
     * splits the UsersDB file using split() with comma as regex
     * calls the parseUserString() method
     */
    public static void splitUserFile() {
        String[] commaSeparatedString = null;
        while (users.hasNextLine()) {
            String usersString = users.nextLine();
            String ignoreCaseUsersString = usersString.toUpperCase();
            commaSeparatedString = ignoreCaseUsersString.split(",");
            userArrayList.add(parseUserString(commaSeparatedString));
        }
    }

    /**
     * parses the User string array and extracts the user name and password to constructs a user object
     *
     * @param commaSeparatedString the string array composed after using split() with regex ","
     * @return user
     */
    private static User parseUserString(String[] commaSeparatedString) {
        splitUserFile();
        User user;
        String userName = commaSeparatedString[0];
        String password = commaSeparatedString[2];
        user = new User(userName, password);
        return user;
    }

    /**
     * parses the meeting string array and extracts the meeting date, start, duration, contact and meeting point name to construct a meeting object
     *
     * @param commaSeparatedString the string array composed after using split() with regex ","
     * @return meeting
     */
    public static Meeting parseMeetingString(String[] commaSeparatedString) {
        splitMeetingFile();
        Meeting meeting;
        String date = commaSeparatedString[0];
        double start = Double.parseDouble(commaSeparatedString[1]);
        double duration = Double.parseDouble(commaSeparatedString[2]);
        String contact = commaSeparatedString[3].toLowerCase();
        String meetingPointName = commaSeparatedString[4];
        meeting = new Meeting(date, start, duration, contact, meetingPointName);
        return meeting;
    }

    /**
     * parses the meeting point string array to extract variables and construct the meeting point object
     *
     * @param commaSeparatedString the string array composed after using split() with regex ","
     * @return meetingPointInput
     */
    public static MeetingPoint parseMeetingPointString(String[] commaSeparatedString) {
        splitFile();
        MeetingPoint meetingPointInput = new MeetingPoint();
        String type = commaSeparatedString[0].toUpperCase();
        String name = commaSeparatedString[1].toUpperCase();
        boolean isAvailable = Boolean.parseBoolean(commaSeparatedString[2].toUpperCase());
        int capacity = Integer.parseInt(commaSeparatedString[3]);
        int screenSize = Integer.parseInt(commaSeparatedString[4]);
        if (type.toUpperCase().equals("ROOM")) {
            int numberOfChairs = Integer.parseInt(commaSeparatedString[5]);
            String tableSize = commaSeparatedString[6].toUpperCase();
            boolean hasWireConnection = Boolean.parseBoolean(commaSeparatedString[7]);
            String screenType = commaSeparatedString[8].toUpperCase();
            meetingPointInput = new Room(type, name, isAvailable, capacity, screenSize, numberOfChairs, tableSize, hasWireConnection, screenType);
        } else if (type.toUpperCase().equals("HUDDLE")) {
            boolean hasWifi = Boolean.parseBoolean(commaSeparatedString[5]);
            boolean hasWhiteBoard = Boolean.parseBoolean(commaSeparatedString[6]);
            meetingPointInput = new Huddle(type, name, isAvailable, capacity, screenSize, hasWifi, hasWhiteBoard);
            switch (commaSeparatedString[5]) {
                case "YES":
                    ((Huddle) meetingPointInput).setHasWifi(true);
                    break;
                case "NO":
                    ((Huddle) meetingPointInput).setHasWifi(false);
                    break;
            }
            switch (commaSeparatedString[6]) {
                case "YES":
                    ((Huddle) meetingPointInput).setHasWhiteboard(true);
                    break;
                case "NO":
                    ((Huddle) meetingPointInput).setHasWhiteboard(false);
                    break;
            }
        }
        if (commaSeparatedString[2].toUpperCase().equals("AVAILABLE")) {
            meetingPointInput.setAvailable(true);
        } else if (commaSeparatedString[2].toUpperCase().equals("NOT AVAILABLE")) {
            meetingPointInput.setAvailable(false);
        } else throw new NullPointerException("Error: This field is null!");

        return meetingPointInput;
    }

    /**
     * Outputs the changes made while the program is running in te VehicleData.txt file
     *
     * @throws IOException
     */
    public static void fileOutput() throws IOException {
        FileWriter outputMeetingPointsFile = new FileWriter("MeetingPoints.txt");
        for (MeetingPoint str : meetingPointArrayList) {
            outputMeetingPointsFile.write(str.toString() + "\n");
        }
        outputMeetingPointsFile.close();
    }

    /**
     * Outputs the changes into the MeetingsDB.txt file
     *
     * @throws IOException
     */
    public static void meetingOutput() throws IOException {
        FileWriter outputMeetingPointsFile = new FileWriter("MeetingsDB.txt");
        for (Meeting meeting : meetingsArrayList) {
            outputMeetingPointsFile.write(meeting.toString() + "\n");
        }
        outputMeetingPointsFile.close();
    }

    /**
     * closes scanner meetingPoints
     */
    public static void closeScanner() {
        meetingPoints.close();
    }

    /**
     * closes scanner meetings
     */
    public static void closeMeetingsScanner() {
        meetings.close();
    }

    /**
     * closes scanner users
     */
    public static void closeUsersScanner() {
        users.close();
    }

}



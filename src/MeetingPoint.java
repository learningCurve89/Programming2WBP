import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the super class, it represents all meeting points
 */
public class MeetingPoint {
    public static ArrayList<MeetingPoint> meetingPointArrayList = new ArrayList<>();
    public static ArrayList<MeetingPoint> availableMeetingPoints = new ArrayList<>();
    public static ArrayList<MeetingPoint> nonAvailableMeetingPoints = new ArrayList<>();
    private String type;
    private String name;
    private boolean isAvailable;
    private int capacity;
    private int screenSize;

    /**
     * a meeting point object
     */
    public MeetingPoint() {
        type = "";
        name = "";
        isAvailable = false;
        capacity = 0;
        screenSize = 0;
    }

    /**
     * a meeting point object with parameters
     *
     * @param type     the type either room or huddle
     * @param name     the name of the meeting point
     * @param capacity how many people it can fit
     */
    public MeetingPoint(String type, String name, int capacity) {
        this.type = type;
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * a meeting point with different parameters
     *
     * @param type        the type either room or huddle
     * @param name        the name of the meeting point
     * @param isAvailable if it is available
     * @param capacity    how many people it can fit
     * @param screenSize  what size is the screen
     */
    public MeetingPoint(String type, String name, boolean isAvailable, int capacity, int screenSize) {
        this.type = type;
        this.name = name;
        this.isAvailable = isAvailable;
        this.capacity = capacity;
        this.screenSize = screenSize;
    }

    /**
     * adds a meeting point to the meeting point array list, this is only available for Admin users
     */
    public static void addMeetingPoint() {
        Scanner adminInputNewMeetingPoint = new Scanner(System.in);
        System.out.println("Enter meeting point: ");
        String newMeetingPoint = adminInputNewMeetingPoint.nextLine();
        String ignoreCase = newMeetingPoint.toUpperCase();
        String[] commaSeparatedNewMeetingPoint = ignoreCase.split(", ");
        meetingPointArrayList.add(FileImportOutput.parseMeetingPointString(commaSeparatedNewMeetingPoint));
        try {
            FileImportOutput.fileOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes a meeting point, this is only visible to admin users
     *
     * @param in the scanner to read user input
     */
    public static void deleteMeetingPoint(Scanner in) {
        Menu allMeetingPoints = inspectMeetingPoint();
        int userInput = allMeetingPoints.executeMenu("Select a meeting point");
        System.out.println(meetingPointArrayList.get(userInput - 1).printDetailedInformation());
        Menu deleteMeetingPoint = new Menu("Would you like to delete this meeting room?", in);
        deleteMeetingPoint.addOption("Yes, delete this item");
        deleteMeetingPoint.addOption("No, let's keep it for a bit longer");
        int userAction = deleteMeetingPoint.executeMenu("Choose action: ");
        switch (userAction) {
            case 1:
                meetingPointArrayList.remove(userInput - 1);
                try {
                    FileImportOutput.fileOutput();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                break;
        }
    }

    /**
     * returns the meeting point into printable format
     *
     * @return type, name, availability, capacity, screensize
     */
    public String toString() {
        String available = "";
        if (isAvailable) {
            available = "AVAILABLE";
        } else {
            available = "NOT AVAILABLE";
        }
        return type + "," + name + "," + available + "," + capacity + "," + screenSize;
    }

    /**
     * returns brief information to printable format
     *
     * @return type, name
     */
    public String printBriefInformation() {
        return type + ", " + name;
    }

    /**
     * prints the best matched meeting point
     *
     * @return type, name, capacity
     */
    public String printBestMatch() {
        return "Type: " + type + ", Name: " + name + ", Capacity: " + capacity;
    }

    /**
     * returns a string in printable format with detailed information
     *
     * @return Detailed info, type,name,availability,capacity,screensize
     */
    public String printDetailedInformation() {
        return
                "Detailed Information: " + "\n" +
                        "Type: " + type + "\n" +
                        "Name: " + name + "\n" +
                        "Availability: " + isAvailable + "\n" +
                        "Capacity: " + capacity + "\n" +
                        "Screen Size: " + screenSize + "\n";
    }

    /**
     * creates a menu where all the meeting points are added as options the user can select and inspect
     *
     * @return allMEetingPointsMenu
     */
    public static Menu inspectMeetingPoint() {
        Scanner in = new Scanner(System.in);
        Menu allMeetingPointsMenu = new Menu("Choose a meeting point: ", in);
        for (int i = 0; i < meetingPointArrayList.size(); i++) {
            String index = meetingPointArrayList.get(i).printBriefInformation();
            allMeetingPointsMenu.addOption(index);
        }
        return allMeetingPointsMenu;
    }

    /**
     * If the meeting point is not booked on that day at that time, it creates a meeting and books the meeting point
     */
    public static void book(DateTime desiredDateTime, int itemSelection) {
        Scanner bookScanner = new Scanner(System.in);
        Menu bookItem = new Menu("Would you like to book this item?", bookScanner);
        bookItem.addOption("Yes, book this item");
        bookItem.addOption("No, I'm fine");
        int userAction = bookItem.executeMenu("Choose action: ");
        switch (userAction) {
            case 1:
                Meeting.addMeeting(desiredDateTime, itemSelection);
                try {
                    FileImportOutput.fileOutput();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                break;
        }
    }

    /**
     * if the meeting point is booked user can release it by entering user credentials
     */
    public static void release(DateTime desiredDateTime, int itemSelection) {
        Scanner releaseScanner = new Scanner(System.in);
        Menu unbookItem = new Menu("Would you like to release this item?", releaseScanner);
        unbookItem.addOption("Yes, release this item");
        unbookItem.addOption("No, I'll keep it");
        unbookItem.addOption("I haven't booked this, but I need it: request user information");
        int releaseInput = unbookItem.executeMenu("Choose action: ");
        switch (releaseInput) {
            case 1:
                System.out.println("Enter the email address you booked this item with: ");
                String emailAddress = releaseScanner.nextLine();
                for (Meeting meeting : Meeting.meetingArrayList) {
                    DateTime meetingDateTime = meeting.parseDate();
                    if (meetingPointArrayList.get(itemSelection - 1).getName().equals(meeting.getMeetingPointName()) && meetingDateTime.toString().equals(desiredDateTime.toString())) {
                        if (emailAddress.equals(meeting.getContact())) {
                            if (User.verifyUser(releaseScanner)) {
                                Meeting.deleteMeeting(meeting);
                                meetingPointArrayList.get(itemSelection - 1).setAvailable(true);
                                try {
                                    FileImportOutput.fileOutput();
                                    FileImportOutput.meetingOutput();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else {
                                System.out.println("Invalid username or password. ");
                            }
                        } else {
                            System.out.println("Invalid email address. ");
                        }
                    }
                }
                break;
            case 2:
                break;
            case 3:
                meetingPointArrayList.get(itemSelection - 1).request(itemSelection);
                break;

        }
    }

    /**
     * if the user selects a meeting point that's already booked by another user, user can request the user contact to request the meeting point needed
     */
    public void request(int itemSelection) {
        MeetingPoint chosenItem = meetingPointArrayList.get(itemSelection - 1);
        String contact = "";
        for (Meeting meeting : Meeting.meetingArrayList) {
            if (chosenItem.getName().equals(meeting.getMeetingPointName())) {
                contact = meeting.getContact();
            }
        }

        System.out.println("This item was booked by: " + contact);
        System.out.println("Please get in contact with the user to request meeting point.");
    }

    /**
     * prints all available meeting points
     */
    public static void printAvailableMeetingPoints() {
        for (MeetingPoint meetingPoint : availableMeetingPoints) {
            if (meetingPoint.isAvailable) {
                System.out.println(meetingPoint.printBriefInformation() + " - currently AVAILABLE");
            }
        }
    }

    /**
     * Checks if meeting points are available, adds non available meeting point to nonAvailableMeetingPoints array list
     *
     * @param desiredDateTime the time adn date that the user wants to check
     * @param chosenItem      the meeting point that will be set to available or not available
     */
    public void checkAvailability(DateTime desiredDateTime, MeetingPoint chosenItem) {
        nonAvailableMeetingPoints.clear();
        for (Meeting meeting : Meeting.meetingArrayList) {
            DateTime meetingDateTime = meeting.parseDate();
            chosenItem.setAvailable(true);
            if (chosenItem.getName().equals(meeting.getMeetingPointName())) {
                if (desiredDateTime.getDay() == meetingDateTime.getDay() && desiredDateTime.getMonth() == meetingDateTime.getMonth() && desiredDateTime.getYear() == meetingDateTime.getYear()) {
                    if (desiredDateTime.getStartTime() < meetingDateTime.getStartTime() && desiredDateTime.getEndTime() <= meetingDateTime.getStartTime()) {
                        chosenItem.setAvailable(true);
                        break;
                    }
                    if (desiredDateTime.getEndTime() <= meetingDateTime.getStartTime()) {
                        chosenItem.setAvailable(true);
                        break;
                    }
                    if (desiredDateTime.getStartTime() > meetingDateTime.getEndTime()) {
                        chosenItem.setAvailable(true);
                        break;
                    } else {
                        chosenItem.setAvailable(false);
                        nonAvailableMeetingPoints.add(chosenItem);
                        break;
                    }
                } else {
                    chosenItem.setAvailable(true);
                }
            }
        }
    }


    /**
     * search for individual meeting point
     */
    public static void searchMeetingPoint() {
        Scanner searchScanner = new Scanner(System.in);
        MeetingPoint meetingPointToSearch;
        int score = 0;
        int highScore = 0;
        MeetingPoint bestMatchedMeetingPoint = null;
        int numberOfChairs = 0;
        String screenType = "";
        boolean hasWhiteboard = false;
        System.out.println("Enter Type: ");
        String type = searchScanner.nextLine();
        System.out.println("Enter Name: ");
        String name = searchScanner.nextLine();
        System.out.println("Enter capacity: ");
        int capacity = searchScanner.nextInt();
        if (type.toUpperCase().equals("ROOM")) {
            System.out.println("Enter Number of Chairs: ");
            numberOfChairs = searchScanner.nextInt();
            System.out.println("Enter Screen Type: ");
            screenType = searchScanner.next();
            meetingPointToSearch = new Room(type, name, capacity, numberOfChairs, screenType);
        } else if (type.toUpperCase().equals("HUDDLE")) {
            System.out.println("Do you need a whiteboard? (YES/NO) ");
            String whiteboardString = searchScanner.next();
            if (whiteboardString.toUpperCase().equals("YES")) {
                hasWhiteboard = true;
            } else
                hasWhiteboard = false;
            meetingPointToSearch = new Huddle(type, name, capacity, hasWhiteboard);
        }
        for (MeetingPoint meetingPoint : meetingPointArrayList) {
            if (meetingPoint.getType().toUpperCase().equals(type.toUpperCase())) {
                score++;
            }
            if (meetingPoint.getName().toUpperCase().equals(name.toUpperCase())) {
                score++;
            }
            if (meetingPoint.getCapacity() == capacity) {
                score++;
            }
            if (meetingPoint instanceof Room) {
                Room room = (Room) meetingPoint;
                if ((room.getNumberOfChairs() == Integer.parseInt(String.valueOf(numberOfChairs)))) {
                    score++;
                }
                if ((room.getScreenType().toUpperCase().equals(screenType.toUpperCase()))) {
                    score++;
                }
            }
            if (meetingPoint instanceof Huddle) {
                Huddle huddle = (Huddle) meetingPoint;
                if ((huddle.isHasWhiteboard() == hasWhiteboard)) {
                    score++;
                }
            }
            if (score > highScore) {
                highScore = score;
                bestMatchedMeetingPoint = meetingPoint;
            }
            score = 0;
        }
        System.out.println("The closest item I could find is: " + bestMatchedMeetingPoint.printBestMatch());
    }


    /**
     * gets the screen size
     *
     * @return screenSize
     */
    public int getScreenSize() {
        return screenSize;
    }

    /**
     * sets the screenSize
     *
     * @param screenSize the size of the screen in the meeting point
     */
    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    /**
     * gets the type of meeting point
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * sets the type of meeting point
     *
     * @param type room or huddle
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * gets the name of the meeting point
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the meeting point
     *
     * @param name of the meeting point
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets availability
     *
     * @return isAvailable
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * sets availability
     *
     * @param available boolean is available or not
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * gets the number of people the meeting point can fit
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * sets the capacity
     *
     * @param capacity number of people that can fit in the meeting point
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

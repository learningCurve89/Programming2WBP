/**
 * This class extends the meeting point class
 */
public class Room extends MeetingPoint {
    private int numberOfChairs;
    private String tableSize;
    private boolean hasWireConnection;
    private String screenType;

    /**
     * constructs an empty room
     */
    public Room() {
        super();
        numberOfChairs = 0;
        tableSize = "";
        hasWireConnection = false;
        screenType = "";
    }

    /**
     * constructs a room with parameters
     *
     * @param type              the type of meeting point inherited from meeting point class
     * @param name              the name of meeting point inherited from meeting point class
     * @param isAvailable       availability, inherited from meeting point class
     * @param capacity          the capacity of meeting point inherited from meeting point class
     * @param screenSize        the screenSize of meeting point inherited from meeting point class
     * @param numberOfChairs    the number of chairs in the room
     * @param tableSize         the size of the table
     * @param hasWireConnection if it has wire connection to the screen
     * @param screenType        if it has touch screen or not touch screen
     */
    public Room(String type, String name, boolean isAvailable, int capacity, int screenSize, int numberOfChairs, String tableSize, boolean hasWireConnection, String screenType) {
        super(type, name, isAvailable, capacity, screenSize);
        this.numberOfChairs = numberOfChairs;
        this.tableSize = tableSize;
        this.hasWireConnection = hasWireConnection;
        this.screenType = screenType;
    }

    /**
     * constructs a room with different parameters
     *
     * @param type           the type of meeting point inherited from meeting point class
     * @param name           the name of meeting point inherited from meeting point class
     * @param capacity       capacity the capacity of meeting point inherited from meeting point class
     * @param numberOfChairs the number of chairs in the room
     * @param screenType     if it has touch screen or not touch screen
     */
    public Room(String type, String name, int capacity, int numberOfChairs, String screenType) {
        super(type, name, capacity);
        this.numberOfChairs = numberOfChairs;
        this.screenType = screenType;
    }

    /**
     * returns a printable version of the room object
     *
     * @return super.toString(), number of chairs, table size, has wire connection, screen type
     */
    public String toString() {
        String hasWireConnectionString;
        if (hasWireConnection) {
            hasWireConnectionString = "YES";
        } else hasWireConnectionString = "NO";
        return super.toString() + "," + numberOfChairs + "," + tableSize + "," + hasWireConnectionString + "," + screenType;
    }

    /**
     * returns brief information to a printable version of the room object
     *
     * @return super.printBriefInformation(), number of chairs
     */
    public String printBriefInformation() {
        return super.printBriefInformation() + "," + numberOfChairs;
    }

    /**
     * gets number of chairs
     *
     * @return numberOfChairs
     */
    public int getNumberOfChairs() {
        return numberOfChairs;
    }

    /**
     * sets the number of chairs
     *
     * @param numberOfChairs the number of chairs in a room
     */
    public void setNumberOfChairs(int numberOfChairs) {
        this.numberOfChairs = numberOfChairs;
    }

    /**
     * gets the size of the table
     *
     * @return tableSize
     */
    public String getTableSize() {
        return tableSize;
    }

    /**
     * sets the size of the table
     *
     * @param tableSize the size of the table
     */
    public void setTableSize(String tableSize) {
        this.tableSize = tableSize;
    }

    /**
     * gets if it has wire connection to the screens
     *
     * @return hasWireConnection
     */
    public boolean isHasWireConnection() {
        return hasWireConnection;
    }

    /**
     * sets if it has wire connection to the screens
     *
     * @param hasWireConnection
     */
    public void setHasWireConnection(boolean hasWireConnection) {
        this.hasWireConnection = hasWireConnection;
    }

    /**
     * gets the screen type
     *
     * @return screenType
     */
    public String getScreenType() {
        return screenType;
    }

    /**
     * sets the screen type
     *
     * @param screenType the type of screen is touch or not touch
     */
    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }
}

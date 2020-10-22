/**
 * This class extends the MeetingPoint class and represents a huddle
 */
public class Huddle extends MeetingPoint {
    private boolean hasWifi;
    private boolean hasWhiteboard;

    /**
     * constructs a huddle object
     */
    public Huddle() {
        super();
        hasWifi = false;
        hasWhiteboard = false;
    }

    /**
     * constructs a huddle object with parameters
     *
     * @param type          the type of meeting point
     * @param name          the name of the meeting point
     * @param isAvailable   the availability
     * @param capacity      the capacity
     * @param screenSize    the screensize
     * @param hasWifi       if it has wifi
     * @param hasWhiteboard if there is a white board
     */
    public Huddle(String type, String name, boolean isAvailable, int capacity, int screenSize, boolean hasWifi, boolean hasWhiteboard) {
        super(type, name, isAvailable, capacity, screenSize);
        this.hasWifi = hasWifi;
        this.hasWhiteboard = hasWhiteboard;
    }

    /**
     * constructs a huddle object with type,name,capacity,hasWhiteboard parameters
     *
     * @param type          the type of meeting point
     * @param name          the name of the meeting point
     * @param capacity      the capacity
     * @param hasWhiteboard if there is a white board
     */
    public Huddle(String type, String name, int capacity, boolean hasWhiteboard) {
        super(type, name, capacity);
        this.hasWhiteboard = hasWhiteboard;
    }

    /**
     * returns the huddle in printable format
     *
     * @return super.toString(), hasWifiString, hasWhiteBoardString
     */
    public String toString() {
        String hasWifiString;
        String hasWhiteBoardString;
        if (hasWifi) {
            hasWifiString = "YES";
        } else hasWifiString = "NO";

        if (hasWhiteboard) {
            hasWhiteBoardString = "YES";
        } else hasWhiteBoardString = "NO";

        return super.toString() + "," + hasWifiString + "," + hasWhiteBoardString;
    }

    /**
     * returns brief information about the object
     *
     * @return super.printBriefInformation(), hasWhiteBoardString
     */
    public String printBriefInformation() {
        String hasWhiteBoardString;
        if (hasWhiteboard) {
            hasWhiteBoardString = "YES";
        } else hasWhiteBoardString = "NO";
        return super.printBriefInformation() + "," + hasWhiteBoardString;
    }

    /**
     * gets if huddle has wifi or not
     *
     * @return hasWifi
     */
    public boolean isHasWifi() {
        return hasWifi;
    }

    /**
     * sets hasWifi
     *
     * @param hasWifi if it has wifi
     */
    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    /**
     * gets if huddle have whiteboard
     *
     * @return hasWhiteBoard
     */
    public boolean isHasWhiteboard() {
        return hasWhiteboard;
    }

    /**
     * sets hasWhiteBoard
     *
     * @param hasWhiteboard if it has a whiteboard
     */
    public void setHasWhiteboard(boolean hasWhiteboard) {
        this.hasWhiteboard = hasWhiteboard;
    }
}

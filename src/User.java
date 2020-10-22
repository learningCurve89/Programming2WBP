import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class represents the user
 */
public class User {
    public static ArrayList<User> userArrayList = new ArrayList<>();
    private String userName;
    private String emailAddress;
    private String password;

    /**
     * constructs a user with parameters
     *
     * @param userName the name of the user
     * @param password the user password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * verifies the user if the user password and name entered are the same as the ones recorded in the UserDB.txt file
     *
     * @param loginScanner reads the user input
     * @return a boolean if the user is verified it's true, or not verified it's false
     */
    public static boolean verifyUser(Scanner loginScanner) {
        boolean isVerified = false;
        System.out.println("Enter user name to verify: ");
        String userName = loginScanner.nextLine().toUpperCase();
        System.out.println("Enter Password: ");
        String password = loginScanner.nextLine().toUpperCase();
        User user = new User(userName, password);
        for (User userFromList : userArrayList) {
            if (user.getUserName().equals(userFromList.getUserName()) && user.getPassword().equals(userFromList.getPassword())) {
                isVerified = true;
                break;
            } else {
                isVerified = false;
            }
        }
        return isVerified;
    }


    /**
     * gets the user name
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets the user name
     *
     * @param userName the name of the user
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * gets the email address
     *
     * @return emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * sets the emailAddress
     *
     * @param emailAddress the email address of the user
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * gets the password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the password
     *
     * @param password the user password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class manages the menus and user interaction from the login onwards
 * it is called in the main method to start the menus
 */
public class Login {

    /**
     * it starts the menu to select the user type
     */
    public static void loginMenu() {
        ArrayList<MeetingPoint> meetingPointArrayList = MeetingPoint.meetingPointArrayList;
        ArrayList<MeetingPoint> availableMeetingPoints = MeetingPoint.availableMeetingPoints;
        ArrayList<MeetingPoint> nonAvailableMeetingPoints = MeetingPoint.nonAvailableMeetingPoints;
        Scanner loginScanner = new Scanner(System.in);
        Menu userMenu = new Menu("Login: ", loginScanner);
        userMenu.addOption("Admin");
        userMenu.addOption("User");
        boolean doWhile;
        do {
            doWhile = false;
            int userCase = userMenu.executeMenu("Enter user type: ");
            switch (userCase) {
                case 1:
                    System.out.println("Please enter admin code: ");
                    String adminCode = loginScanner.nextLine();
                    //the admin code is the admin password, for the purpose of this project hardcoded as ADMIN
                    if (adminCode.equals("ADMIN")) {
                        //The admin can add a meeting point or delete a meeting point
                        Menu adminMenu = new Menu("Admin Menu: ", loginScanner);
                        adminMenu.addOption("Add Meeting Point");
                        adminMenu.addOption("Delete Meeting Point");
                        adminMenu.addOption("Quit");
                        boolean admin;
                        do {
                            admin = false;
                            int adminInput = adminMenu.executeMenu("Choose an action");
                            switch (adminInput) {
                                case 1:
                                    MeetingPoint.addMeetingPoint();
                                    break;
                                case 2:
                                    MeetingPoint.deleteMeetingPoint(loginScanner);
                                    break;
                                case 3:
                                    admin = true;
                                    break;
                            }
                        } while (!admin);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    //The user can inspect the meeting points, print the available meeting points, search a meeting point or quit
                    Scanner userScanner = new Scanner(System.in);
                    Menu regularUserMenu = new Menu("User Menu: ", userScanner);
                    regularUserMenu.addOption("Inspect item to book or release");
                    regularUserMenu.addOption("Print available meeting point");
                    regularUserMenu.addOption("Search meeting point");
                    regularUserMenu.addOption("Quit");
                    boolean user;
                    do {
                        user = false;
                        int userInput = regularUserMenu.executeMenu("Choose your action: ");
                        switch (userInput) {
                            case 1:
                                Menu allMeetingPoints = MeetingPoint.inspectMeetingPoint();
                                DateTime desiredDateTime = new DateTime();
                                desiredDateTime = desiredDateTime.selectDateTime(userScanner);
                                int itemSelection = allMeetingPoints.executeMenu("Select item: ");
                                System.out.println("Detailed info: ");
                                System.out.println("*********************************************************************");
                                System.out.println(meetingPointArrayList.get(itemSelection - 1).printDetailedInformation());
                                System.out.println("********************************************************************");
                                MeetingPoint chosenMeetingPoint = meetingPointArrayList.get(itemSelection - 1);
                                chosenMeetingPoint.checkAvailability(desiredDateTime, chosenMeetingPoint);
                                if (chosenMeetingPoint.isAvailable()) {
                                    MeetingPoint.book(desiredDateTime, itemSelection);
                                } else if (!chosenMeetingPoint.isAvailable()) {
                                    MeetingPoint.release(desiredDateTime, itemSelection);
                                }
                                break;
                            case 2:
                                desiredDateTime = new DateTime();
                                desiredDateTime = desiredDateTime.selectDateTime(userScanner);
                                for (MeetingPoint availableMeetingPoint : meetingPointArrayList) {
                                    availableMeetingPoint.checkAvailability(desiredDateTime, availableMeetingPoint);
                                    availableMeetingPoints.add(availableMeetingPoint);
                                    for (MeetingPoint nonAvailableMeetingPoint : nonAvailableMeetingPoints) {
                                        if (availableMeetingPoint.getName().equals(nonAvailableMeetingPoint.getName())) {
                                            availableMeetingPoints.remove(availableMeetingPoint);
                                        }
                                    }
                                }
                                MeetingPoint.printAvailableMeetingPoints();
                                availableMeetingPoints.clear();
                                break;
                            case 3:
                                MeetingPoint.searchMeetingPoint();
                                break;
                            case 4:
                                try {
                                    FileImportOutput.fileOutput();
                                    FileImportOutput.closeScanner();
                                    FileImportOutput.closeMeetingsScanner();
                                    FileImportOutput.closeUsersScanner();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.exit(0);
                                break;

                        }
                    } while (!user);
                    userScanner.close();
                    break;
            }
        }
        while (!doWhile);
    }

}

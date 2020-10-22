import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is based on the menu class that was originally provided by Tom Blanchard on
 * /**https://blackboard.swan.ac.uk/webapps/blackboard/content/listContent.jsp?course_id=_59086_1&content_id=_3209736_1
 * /**Some parts have been modify to fit the current project.
 */
public class Menu {
    private String title;
    private ArrayList<String> options;
    private Scanner in;

    /**
     * Constructs an empty menu
     *
     * @param title The title of the menu.
     * @param in    The scanner object for reading the menu input from
     */
    public Menu(String title, Scanner in) {
        this.title = title;
        this.options = new ArrayList<String>();
        this.in = in;
    }

    /**
     * Adds an option to the end of this menu.
     *
     * @param option the option to add.
     */
    public void addOption(String option) {
        options.add(option);
    }

    /**
     * Display the menu, with options numbered starting with 1,
     * and prompts the user for input.
     *
     * @return the option number that the user chose.
     */
    public int executeMenu() {
        return executeMenu("Choice: ");
    }

    /**
     * Display the menu, with options numbered starting with 1,
     *
     * @param prompt the prompt the user is shown.
     * @return the option number that the user chose.
     */
    public int executeMenu(String prompt) {
        printTitle();
        printOptions();

        boolean validChoiceEntered = false;
        String input = null;
        int inputAsInt = 0;
        do {
            System.out.print(prompt);
            in = new Scanner(System.in);
            input = in.nextLine();

            try {
                inputAsInt = Integer.parseInt(input);
                if (inputAsInt >= 1 && inputAsInt <= options.size()) {
                    validChoiceEntered = true;
                } else {
                    printInputError(input);
                }
            } catch (NumberFormatException e) {
                printInputError(input);
            }
        } while (!validChoiceEntered);

        return inputAsInt;
    }

    /**
     * Prints the title of the menu
     */
    private void printTitle() {
        System.out.println(title);
        for (int i = 0; i < title.length(); i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prints the options of the menu.
     */
    private void printOptions() {
        for (int i = 0; i < options.size(); i++) {
            int choice = i + 1;
            System.out.println(choice + ") " + options.get(i));
        }
    }

    /**
     * Print a error message for invalid input.
     *
     * @param input The input that is invalid.
     */
    private void printInputError(String input) {
        System.out.println();
        System.out.println("Error: " + input + " is an invalid choice.");
        System.out.println("Please enter number between 1 and " + options.size() + ".");
        System.out.println();
    }

}

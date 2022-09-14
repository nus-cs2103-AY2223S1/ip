import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class UI {

    private Scanner sc;

    private static String initGreeting = "Hello! I'm Duke \nWhat can I do for you?";

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public String greet() {
        return initGreeting;
    }

    /**
     * Returns a farewell message before Duke closes.
     *
     * @return String containing the farewell message
     */
    public String bye() {
        sc.close();
        return "Bye. Hope to see you again soon!";
    }

    public String getDukeErrorMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Returns the user's input using the Scanner sc.
     *
     * @return String sentence of user input.
     */
    public String getInput() {
        return sc.nextLine();
    }
}

package duke;

import java.util.Scanner;

/**
 * Represents an interface that interacts with the user by printing the necessary messages
 */
public class Ui {

    String showWelcome() {
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    String showError(Exception e){
        return e.getMessage();
    }

}

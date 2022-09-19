package duke;

import java.util.Scanner;

/**
 * UI for interaction with user.
 *
 * @author Aaron Pang
 * @version CS2103T AY22/23 Sem 1
 */
public class Ui {

    private Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Shows the goodbye message.
     */
    public String showBye() {
        input.close();
        String bye = "Bye. Hope to see you again soon!";
        return bye;
    }
}


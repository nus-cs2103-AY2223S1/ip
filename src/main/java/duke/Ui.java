package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 *
 * @author eugeneleong
 * @version 1.0
 */

public class Ui {

    private static final String line = "-----------------------------";
    private Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Our lovely Duke gives a sweet intro
     */
    public void sayHi() {
        System.out.println(line + "\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you\n"
                + line + "\n");
    }

    /**
     * Our lovely Duke gives its parting words
     */
    public void sayBye() {
        System.out.println(line + "\n"
                + "Bye. Hope to see you again soon!\n"
                + line + "\n");
    }

    /**
     * Our lovely Duke takes in the commands of the user
     * @return the command, as it is
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Just in case someone keys in the wrong filename...
     * @exception FileNotFoundException if the file cannot be found
     */
    public void showLoadingError() throws FileNotFoundException {
        throw new FileNotFoundException("File cannot be found!");
    }

}

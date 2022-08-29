package duke;

/**
 * A class that deals with interacting with the user.
 *
 * @author A0235287X
 */
public class Ui {

    /**
     * Method that displays a string as a starting message.
     */
    public void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Method that displays error when file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("The file is empty! Let's create a new file :)");
    }



}

package duke;

/**
 * The object that handles the UI aspects of the application
 */
public class Ui {
    /** Main logo of the app */
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints a text in the specified format
     * @param text
     */
    public void printText(String text) {
        System.out.println("________________________________________\n"
                + text
                + "\n________________________________________\n");
    }

    /**
     * Prints initial UI of the app
     */
    public void initialize() {
        System.out.println("Hello from\n" + logo);

        printText("Hello I'm Duke\nWhat can I do for you");
    }

    /**
     * Prints final UI of the app
     */
    public void exit() {
        printText("Bye. hope to see you again soon!");
    }

}

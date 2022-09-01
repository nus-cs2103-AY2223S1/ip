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
    public String initialize() {
        String string = "";
        string += "Hello from\n" + logo + "\n";

        string += "Hello I'm Duke\nWhat can I do for you";
        return string;
    }

    /**
     * Prints final UI of the app
     */
    public String exit() {
        return ("Bye. hope to see you again soon!");
    }

}

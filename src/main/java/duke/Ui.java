package duke;

/**
 * Represents the User interface,
 * used to interact with the user
 */

public class Ui {
    /**
     * Represents what users see when program
     * is started, a greeting by duke
     */
    public void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you ?");
    }

    /**
     * Represents what users see when file
     * faces issues when being loaded
     */
    public void showLoadingError() {
        System.out.println("Could not load any existing task files");
        System.out.println("New task file will be created :) ");
    }

}

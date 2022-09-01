package duke;

/**
 * Class to handle Ui interactions
 */
public class Ui {
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Method to run at the start of running program
     */
    public void greeting() {
        String greet = "Hello! I'm Duke \n"
                + "What can I do for you? \n";

        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
    }

    public void print(String input) {
        System.out.println(input);
    }

    /**
     * Method to run at the end of running program
     */
    public void farewell() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}

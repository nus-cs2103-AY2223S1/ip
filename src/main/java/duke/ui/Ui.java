package duke.ui;

public class Ui {

    public Ui() {

    }

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String welcomeMessage = "Hello from\n" + logo
            + "What can I do for you?";

    public void bootUpDuke() {
        System.out.println(welcomeMessage);
    }

    public void shutDownDuke() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}

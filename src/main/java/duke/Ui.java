package duke;

public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String marked = "[X]";
    String unmarked = "[ ]";
    public void printText(String text) {
        System.out.println("________________________________________\n" +
                text +
                "\n________________________________________\n");
    }

    public void initialize() {
        System.out.println("Hello from\n" + logo);

        printText("Hello I'm Duke\nWhat can I do for you");
    }

    public void exit() {
        printText("Bye. hope to see you again soon!");
    }

}

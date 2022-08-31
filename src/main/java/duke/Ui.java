package duke;

public class Ui {

    enum Keywords {
        mark,
        unmark,
        delete,
        todo,
        deadline,
        event
    }

    public Ui() {}

    public static String straightLine = "  ----------------------------------------------------------------------------------";

    public String logo  =         "___   ___    ___  ____                     ___   ___ \n"
                               + "|  |  /  /  |  |  \\  \\      ------      /  /   |  | \n"
                               + "|   /   /   |  |   \\  \\    / / \\ \\    /  /    |  | \n"
                               + "|   \\  \\   |  |     \\  \\  /  /   \\ \\  /  /     |  | \n"
                               + "|    \\  \\  |  |      \\  \\/  /     \\ \\/  /      |  | \n"
                               + "|___| \\__\\ |__|       \\____/       \\____/       |__| \n";

    public void printGreeting() {
        System.out.println("\nHello from\n" + logo);

        System.out.println("Hello! I'm KiwiQE :) \nWhat can I do for you? \n");
    }

    public void printGoodbyeMessage() {
        System.out.println(straightLine + "\n  sayonara, goodbye\n" + straightLine);
    }


    public void printTaskListEmpty() {
        System.out.println(straightLine + "\n" + "  Nothing to do currently ehe\n" + straightLine);
    }

    public void printIndexOutOfBoundsException(Keywords keyword) {
        switch (keyword) {
            case delete:
                System.out.println(straightLine + "\n  Can't delete something that isn't there...\n"
                        + straightLine + "\n");
                break;
            case unmark:
                System.out.println(straightLine + "\n  HEY THERE'S NO SUCH TASK! >:(\n"
                        + straightLine + "\n");
                break;
        }
    }

    public void printInsufficientInfoException(Keywords keyword) {
        switch (keyword) {
            case delete:
                System.out.println(straightLine + "\n  Insufficient information to delete! Please input more ;-;\n"
                        + straightLine + "\n");
                break;
            case mark:
                System.out.println(straightLine + "\n  Insufficient information to mark! Please input more ;-;\n"
                        + straightLine + "\n");
                break;
            case unmark:
                System.out.println(straightLine + "\n  Insufficient information to unmark! Please input more ;-;\n"
                        + straightLine + "\n");
                break;
            case todo:
                System.out.println(straightLine + "\n  Insufficient information to make a todo! Please input more ;-;\n"
                        + straightLine + "\n");
                break;
            case deadline:
                System.out.println(straightLine + "\n  Insufficient information to make a deadline! Please input more ;-;\n"
                        + straightLine + "\n");
                break;
            case event:
                System.out.println(straightLine + "\n  Insufficient information to make a event! Please input more ;-;\n"
                        + straightLine + "\n");
                break;
        }
    }

    public void printUnrecognisedWord() {
        System.out.println(straightLine + "\n  What do you mean by Justin Bieber plays~\n" + straightLine + "\n");
    }

    public static void printFileNotFound() {
        System.out.println("File has yet to be created, creating for you now!");
    }

    public static void printCannotOpenFile() {
        System.out.println("You can't create a file!");
    }

    public static void printUnableToAppend() {
        System.out.println("You can't append!");
    }

    public void printNoMatchingTask() {
        System.out.println(straightLine + "\n No tasks exist yet, can't find a match :( \n" + straightLine);
    }

}

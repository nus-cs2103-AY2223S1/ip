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

    /**
     * A constructor to initialize the Ui Object.
     */
    public Ui() {}

    public static String straightLine = "  ----------------------------------------------------------------------------------";
    public String logo  =         "___   ___  ___   ____                    ___    ___ \n"
                               +  "|  |  /  / |  |   \\  \\      ------      /  /   |  | \n"
                               +  "|   /   /  |  |    \\  \\    /  / \\ \\    /  /    |  | \n"
                               +  "|   \\  \\   |  |     \\  \\  /  /   \\ \\  /  /     |  | \n"
                               +  "|    \\  \\  |  |      \\  \\/  /     \\ \\/  /      |  | \n"
                               +  "|___| \\__\\ |__|       \\____/       \\___/       |__| \n";

    /**
     * Output the greeting message when starting up the project.
     */
    public void printGreeting() {
        System.out.println("\nHello from\n" + logo);

        System.out.println("Hello! I'm KiwiQE :) \nWhat can I do for you? \n");
    }

    /**
     * Output the goodbye message when terminating the project.
     */
    public void printGoodbyeMessage() {
        System.out.println(straightLine + "\n  sayonara, goodbye\n" + straightLine);
    }

    /**
     * Output the task list.
     */
    public void printTaskListEmpty() {
        System.out.println(straightLine + "\n" + "  Nothing to do currently ehe\n" + straightLine);
    }

    /**
     * Output the corresponding customised Index Out Of Bounds Exception message.
     *
     * @param keyword A keyword object to identify which message to output.
     */
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

    /**
     * Output the corresponding customised String Index Out Of Bounds Exception message.
     *
     * @param keyword A keyword object to identify which message to output.
     */
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

    /**
     * Output the message when input is not recognised.
     */
    public void printUnrecognisedWord() {
        System.out.println(straightLine + "\n  What do you mean by Justin Bieber plays~\n" + straightLine + "\n");
    }

    /**
     * Output the File Not Found Error.
     */
    public static void printFileNotFound() {
        System.out.println("File has yet to be created, creating for you now!");
    }

    /**
     * Output the error unable to create the file.
     */
    public static void printCannotOpenFile() {
        System.out.println("You can't create a file!");
    }

    /**
     * Output unable to append information to the file.
     */
    public static void printUnableToAppend() {
        System.out.println("You can't append!");
    }


}

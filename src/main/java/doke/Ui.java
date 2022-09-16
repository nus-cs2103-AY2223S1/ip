package doke;

/**
 * Represent an Ui to interact with user.
 */
public class Ui {

    private static final String MESSAGE_HELLO = "_________________________ \n" + "    Hi, my name is Doke"
            + "\n" + "    What can I do for you? \n" + "    Enter a String!! \n"
            + "_________________________" + "\n";

    private static final String MESSAGE_BYE = "_________________________ \n" + "    Bye bye! \n"
            + "_________________________ \n";


    protected void printAddMessage(Task task, int tasks) {
        printOut("_________________________ \n" + "added: " + task.toString() + "\n"
                + "Nice, now you have " + tasks + " tasks!! \n"
                + "_________________________" + "\n");
    }

    protected void printMiscErrorMessage() {
        printOut("_________________________ \n" + "Something went wrong, try again"
                + "\n _________________________ \n");
    }

    protected void printMarkMessage(boolean isMarked, Task task) {
        if (isMarked != true) {
            printOut("_________________________ \n" + "I've marked this task: \n"
                    + task.toString() + "\n _________________________ \n");
        } else {
            printOut("_________________________ \n" + "Task is already marked as such: \n"
                    + task.toString() + "\n _________________________ \n");
        }
    }

    protected void printNewFileCreatedMessage() {
        printOut("a new Doke.txt file has been created");
    }

    protected void printErrorMessage() {
        printOut("An error occurred. Try again at another time.");
    }

    protected void printDeleteMessage(Task task, int i) {
        printOut("_________________________ \n" + "This task has been removed \n"
                + task.toString() + "\n" + "Now you have " + i
                + " tasks!! " + "\n _________________________ \n");
    }
    /**
     * Prints out the message to the terminal
     *
     * @param message
     */
    public void printOut(String message) {
        if (message.equals("hello")) {
            System.out.println(MESSAGE_HELLO);
        } else if (message.equals("bye")) {
            System.out.println(MESSAGE_BYE);
        } else {
            System.out.println(message);
        }
    }

}

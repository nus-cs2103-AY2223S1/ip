import java.time.format.DateTimeParseException;

public class Ui {

    enum Commands {
        DELETE,
        MARK,
        UNMARK,
        LIST,
        TASK
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Jett");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void printCorrectMessage(Commands command, TaskList taskList, int index) {
        switch (command) {
            case TASK:
                System.out.println("Got it. I've added this task:");
                taskList.printByIndex(index);
                System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
                break;
            case LIST:
                System.out.println("Here are the tasks in your list:");
                taskList.printTaskList();
                break;
            case MARK:
                System.out.println("Nice! I've marked this task as done:");
                taskList.printByIndex(index);
                break;
            case DELETE:
                System.out.println("Noted. I've removed this task:");
                taskList.printByIndex(index);
                break;
            case UNMARK:
                System.out.println("OK, I've marked this task as not done yet:");
                taskList.printByIndex(index);
                break;
        }
    }

    public void printErrorMessage(String errorMessage) {
        if (errorMessage.equals("datetime")) {
            System.out.println("We do not recognise this date time format! Please enter in yyyy-mm-dd format!");
        } else {
            System.out.println(errorMessage);
        }
    }

}

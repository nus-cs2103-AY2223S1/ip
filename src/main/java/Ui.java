import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "\t___________________________\n";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }
        return "";
    }

    public void printWithDivider(String output) {
        System.out.println(DIVIDER);
        System.out.print(output);
        System.out.println(DIVIDER);
    }

    public void bye() {
        String output = "\tBye. Hope to see you again soon!\n";
        printWithDivider(output);
    }

    public void list(String tasks) {
        String output = "\tHere are the tasks in your list:\n";
        output += tasks;
        printWithDivider(output);
    }

    public void add(int numOfTasks, String message) {
        String output = "\tGot it. I've added this task:\n";
        output += message;
        output += String.format("\tNow you have %d tasks in the list.\n", numOfTasks);
        printWithDivider(output);
    }

    public void mark(String message) {
        String output = "\tNice! I've marked this task as done:\n";
        output += message;
        printWithDivider(output);
    }

    public void unmark(String message) {
        String output = "\tOK, I've marked this task as not done yet:\n";
        output += message;
        printWithDivider(output);
    }

    public void delete(int numOfTask, String message) {
        String output = "\tNoted. I've removed this task:\n";
        output += message;
        output += String.format("\tNow you have %d tasks in the list.\n", numOfTask);
        printWithDivider(output);
    }

    public void showLoadingError(String message) {
        switch (message) {
            case "empty command":
            printWithDivider("\t☹ OOPS!!! The description cannot be empty.\n");
            break;

            case "invalid command":
            printWithDivider("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            break;
            
            case "empty command mark":
            printWithDivider("\t☹ OOPS!!! The description of mark cannot be empty.\n");
            break;

            case "invalid command mark":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: mark <position in list>\n");
            break;

            case "empty command unmark":
            printWithDivider("\t☹ OOPS!!! The description of unmark cannot be empty.\n");
            break;

            case "invalid command unmark":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: unmark <position in list>\n");
            break;

            case "empty command delete":
            printWithDivider("\t☹ OOPS!!! The description of delete cannot be empty.\n");
            break;

            case "invalid command delete":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: delete <position in list>\n");
            break;

            case "empty todo":
            printWithDivider("\t☹ OOPS!!! The description of todo cannot be empty.\n");
            break;

            case "empty deadline":
            printWithDivider("\t☹ OOPS!!! The description of deadline cannot be empty.\n");
            break;

            case "invalid command deadline":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: deadline <desciption> /by <yyyy-mm-dd HH:MM>\n");
            break;

            case "empty event":
            printWithDivider("\t☹ OOPS!!! The description of a event cannot be empty.\n");
            break;

            case "invalid command event":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: event <desciption> /at <yyyy-mm-dd HH:MM>\n");
            break;

            case "empty taskslist":
            printWithDivider("\tTasklist is empty\n");
            break;

        }
    }
}

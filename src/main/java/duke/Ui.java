package duke;

import java.util.Scanner;

/**
 * Ui class deals with interactions with users
 */
public class Ui {
    private final String COMMAND_LIST = "1. Add a ToDo\n" + "2. Add an Event\n" + "3. Add a Deadline\n" +
            "4. List all Tasks\n" + "5. Mark\n" + "6. Unmark\n" + "7. Delete a Task\n" + "8. Find\n" + "9. Exit";
    private final String SPLIT_LINE = "*".repeat(80);
    private final Scanner sc = new Scanner(System.in);

    /**
     * Says hi to the user.
     */
    public void startGreeting() {
        System.out.println(SPLIT_LINE);
        String INITIAL_GREETING = "Hello, I'm Duke. What can I do for you?";
        System.out.println(INITIAL_GREETING);
        System.out.println(COMMAND_LIST);
        System.out.println(SPLIT_LINE);
    }

    /**
     * Prints the given text.
     * @param s message to be prompted
     */
    public void prompt(String s) {
        System.out.println(s);
    }

    /**
     * Reports a checked Duke exception
     * @param error exception message
     */
    public void reportError(String error) {
        System.out.println(error);
    }
    /**
     * Retrieves input from the user.
     * @return user's input string
     */
    public String inputText() {
        return sc.nextLine().strip().replaceAll("(?m)^\\s*$[\n\r]+", "");
    }

    /**
     * Gets user's choice for command.
     * @return user's input command
     */
    public String inputCommand() {
        return sc.nextLine();
    }

    /**
     * Get user's choice for task.
     * @return user's input task
     */
    public int inputTask() {
        int targetTask = -1;
        try {
            targetTask = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please input a number");
        }
        return targetTask;
    }

    /**
     * Displays chatbot info with list command.
     * @param info message to be displayed
     * @param isList true if it is a list command
     * @param isMark true if it is a mark command
     * @param isUnmark true if it is an unmark command
     */
    public void display(String info, boolean isList, boolean isMark, boolean isUnmark, boolean isDelete,
                        boolean isFind) {
        if (isList) {
            showSplitLine();
            System.out.println("Here are all your tasks:");
            System.out.println(info);
            showSplitLine();
        } else if (isMark) {
            showSplitLine();
            System.out.println("Successfully marked! You can see it in your task list as follows:");
            System.out.println(info);
            showSplitLine();
        } else if (isUnmark) {
            showSplitLine();
            System.out.println( "Successfully unmarked! You can see it in your task list as follows:");
            System.out.println(info);
            showSplitLine();
        } else if (isDelete) {
            showSplitLine();
            System.out.println("Successfully deleted! You can use list command to check your tasks.");
            showSplitLine();
        } else if (isFind) {
            showSplitLine();
            if (info.isBlank()) {
                System.out.println("Sorry, we couldn't find anything you want");
            } else {
                System.out.println("Here are what we found for you:");
                System.out.println(info);
            }
            showSplitLine();
        } else {
            showSplitLine();
            System.out.println("Successfully added! You can see it in your task list as follows:");
            System.out.println(info);
            showSplitLine();
        }
    }

    /**
     * Prints the split line.
     */
    public void showSplitLine() {
        System.out.println(SPLIT_LINE);
    }

    /**
     * Displays the command list.
     */
    public void showCommandList() {
        System.out.println("Anything else? I'm always here for you!");
        System.out.println(COMMAND_LIST);
        System.out.println(SPLIT_LINE);
    }

    /**
     * Says goodbye to the user.
     */
    public void sayBye() {
        System.out.println(SPLIT_LINE);
        String GOODBYE_GREETING = "Bye. Hope to see you soon!";
        System.out.println(GOODBYE_GREETING);
        System.out.println(SPLIT_LINE);
    }
}

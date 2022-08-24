package duke;

import java.util.Scanner;

public class Ui {

    private final String listString = "list";
    private final String commandList = "1. Add a ToDo\n" + "2. Add an Event\n" + "3. Add a Deadline\n" +
            "4. List all Tasks\n" + "5. Mark\n" + "6. Unmark\n" + "7. Delete a Task\n" + "8. Exit";
    private final String splitLine = "*".repeat(80);

    private final Scanner sc = new Scanner(System.in);

    /**
     * Say hi to the user.
     */
    public void startGreeting() {
        System.out.println(splitLine);
        String initialGreeting = "Hello, I'm Duke. What can I do for you?";
        System.out.println(initialGreeting);
        System.out.println(commandList);
        System.out.println(splitLine);
    }

    public void prompt(String s) {
        System.out.println(s);
    }

    public void reportError(String error) {
        System.out.println(error);
    }
    /**
     * Retrieve input from the user
     * @return user's input string
     */
    public String inputText() {
        return sc.nextLine().strip().replaceAll("(?m)^\\s*$[\n\r]+", "");
    }

    /**
     * Get user's choice for command
     * @return user's input command
     */
    public String inputCommand() {
        return sc.nextLine();
    }

    /**
     * Get user's choice for task
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
     * Display chatbot info with list command
     * @param info chatbot info
     * @param isList boolean value to show if the user asked for a list command
     * @param isMark boolean value to show if the user asked for a mark command
     * @param isUnmark boolean value to show if the user asked for an unmark command
     */
    public void display(String info, boolean isList, boolean isMark, boolean isUnmark, boolean isDelete) {
        if (isList) {
            System.out.println(splitLine);
            System.out.println("Here are all your tasks:");
            System.out.println(info);
            System.out.println(splitLine);
        } else if (isMark) {
            System.out.println(splitLine);
            System.out.println("Successfully marked! You can see it in your task list as follows:");
            System.out.println(info);
            System.out.println(splitLine);
        } else if (isUnmark) {
            System.out.println(splitLine);
            System.out.println( "Successfully unmarked! You can see it in your task list as follows:");
            System.out.println(info);
            System.out.println(splitLine);
        } else if (isDelete) {
            System.out.println(splitLine);
            System.out.println("Successfully deleted! You can use list command to check your tasks.");
            System.out.println(splitLine);
        } else {
            System.out.println(splitLine);
            System.out.println("Successfully added! You can see it in your task list as follows:");
            System.out.println(info);
            System.out.println(splitLine);
        }
    }

    public void showSplitLine() {
        System.out.println(splitLine);
    }

    /**
     * Display the command list.
     */
    public void showCommandList() {
        System.out.println("Anything else? I'm always here for you!");
        System.out.println(commandList);
        System.out.println(splitLine);
    }

    /**
     * Say goodbye to the user.
     */
    public void sayBye() {
        System.out.println(splitLine);
        String goodbyeGreeting = "Bye. Hope to see you soon!";
        System.out.println(goodbyeGreeting);
        System.out.println(splitLine);
    }
}

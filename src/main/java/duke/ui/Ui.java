package duke.ui;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * A Ui class that encapsulates the ui of Duke.
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * To start reading the command
     * @return the command entered by the user
     */
    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    /**
     * Shows the welcome message
     */
    public void showWelcome() {
        System.out.println(dialog("Hello! I'm Duke", "What can I do for you?"));
    }

    /**
     * Shows the termination message
     */
    public void showBye() {
        System.out.println(dialog("Bye. See you next time."));
        sc.close();
    }

    /**
     * Shows the error message
     * @param errorMessage the error message
     */
    public void showError(String errorMessage) {
        System.out.println(dialog(errorMessage));
    }

    /**
     * Shows the task that is being marked or unmarked
     * @param task the task
     */
    public void showTask(Task task) {
        if (task.isDone()) {
            System.out.println(dialog("Nice! I've marked this task as done:", task.toString()));
        } else {
            System.out.println(dialog("Nice! I've marked this task as not done yet:", task.toString()));
        }
    }

    /**
     * Shows the task that is being deleted
     * @param task the task
     * @param numOfRemainingTasks the number of remaining tasks in the list
     */
    public void showDeletedTask(Task task, Integer numOfRemainingTasks) {
        String remainder = String.format("Now you have %d tasks in the list.", numOfRemainingTasks);
        System.out.println(dialog("Noted. I've removed this task:", task.toString(), remainder));
    }

    /**
     * Shows the task that is being added.
     * @param task the task
     * @param numOfRemainingTasks the number if tasks in the list
     */
    public void showAddTask(Task task, Integer numOfRemainingTasks) {
        String remainder = String.format("Now you have %d tasks in the list.", numOfRemainingTasks);
        System.out.println(dialog("Got it. I've added this task:", task.toString(), remainder));
    }

    /**
     * Shows the command the Duke support
     */
    public void showHelpMenu() {
        System.out.println(dialog("The following is the list of commands:",
                    "BYE    To terminate the programme.",
                    "LIST   To see the list of current tasks.",
                    "Mark {any number}    To mark a task as done.",
                    "Unmark {any number}    To unmark a task.",
                    "Delete {any number}    To delete a task.",
                    "Todo {Task description}    To add a TODO task.",
                    "Deadline {Task description}/{YYYY-MM-DD}    To add a DEADLINE task.",
                    "Event {Task description}/{YYYY-MM-DD}    To add an EVENT task.",
                    "Help    To see the list of commands."));
    }

    /**
     * Shows the list of current tasks.
     * @param memo
     */
    public void showList(List<? extends Task> memo) {
        String[] strArr = new String[memo.size() + 1];
        strArr[0] = "Here are the tasks in your list:";
        for (int i = 1; i < strArr.length; i++) {
            strArr[i] = i + ". " + memo.get(i - 1);
        }
        System.out.println(dialog(strArr));
    }

    /**
     * To wrap the response from Duke in a frame
     * @param strings the response from Duke
     * @return the wrapped response that is ready to be shown to the user
     */
    public static String dialog(String... strings) {
        StringBuilder sb = new StringBuilder();
        sb.append("  ____________________________________________________________\n");
        for (String message : strings) {
            sb.append("   " + message + "\n");
        }
        sb.append("  ____________________________________________________________");
        return sb.toString();
    }

}

package duke.ui;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    public void showWelcome() {
        System.out.println(dialog("Hello! I'm Duke", "What can I do for you?"));
    }

    public void showBye() {
        System.out.println(dialog("Bye. See you next time."));
        sc.close();
    }

    public void showError(String errorMessage) {
        System.out.println(dialog(errorMessage));
    }

    public void showTask(Task task) {
        if (task.isDone()) {
            System.out.println(dialog("Nice! I've marked this task as done:", task.toString()));
        } else {
            System.out.println(dialog("Nice! I've marked this task as not done yet:", task.toString()));
        }
    }

    public void showDeletedTask(Task task, Integer numOfRemainingTasks) {
        String remainder = String.format("Now you have %d tasks in the list.", numOfRemainingTasks);
        System.out.println(dialog("Noted. I've removed this task:", task.toString(), remainder));
    }

    public void showAddTask(Task task, Integer numOfRemainingTasks) {
        String remainder = String.format("Now you have %d tasks in the list.", numOfRemainingTasks);
        System.out.println(dialog("Got it. I've added this task:", task.toString(), remainder));
    }

    public void showHelpMenu() {
    }

    public void showList(List<? extends Task> memo) {
        String[] strArr = new String[memo.size() + 1];
        strArr[0] = "Here are the tasks in your list:";
        for (int i = 1; i < strArr.length; i++) {
            strArr[i] = i + ". " + memo.get(i - 1);
        }
        System.out.println(dialog(strArr));
    }

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

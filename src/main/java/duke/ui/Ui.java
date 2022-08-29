package duke.ui;

import duke.task.TaskList;
import duke.task.Task;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(LINE + "\n" + message + "\n" + LINE + "\n");
    }

    public String makeIndent(String message) {
        return INDENTATION + message;
    }

    public void displayGreeting() {
        printMessage(makeIndent("Hi, how are you doing?! I'm JRH2000\n") +
                makeIndent("How can I help you?\n"));
    }

    public void displayBye() {
        printMessage(makeIndent("Sigh...abandoned again. See you again next time :("));
    }

    public void displayMark(Task markedTask) {
        printMessage(makeIndent("Alright then! This task is marked as done: \n") +
                makeIndent(markedTask.toString()) + "\n");
    }

    public void displayUnmark(Task unmarkedTask) {
        printMessage(makeIndent("Oh OK, this task is now marked as not done yet: \n") +
                makeIndent(unmarkedTask.toString()) + "\n");
    }

    public void displayDelete(Task deletedTask) {
        printMessage(makeIndent("Fine. I've removed this task: \n") +
                makeIndent(deletedTask.toString()) + "\n");
    }

    public void displayAdd(Task addedTask, int size) {
        printMessage(makeIndent("Sure thing! I've added this task: \n") +
                makeIndent(addedTask.toString()) + "\n" +
                makeIndent("Now you have " + size + " task(s) in the list.\n"));
    }

    public void displayMatch(TaskList matchedTasks) {
        int size = matchedTasks.getSize();

        String list = "These tasks matches your keyword: \n";

        for (int i = 0; i < size; i++) {
            list = list + makeIndent((i + 1) + ". " + matchedTasks.getTask(i).toString() + "\n");
        }

        printMessage(list);
    }

    public void displayList(TaskList tasks) {
        int size = tasks.getSize();

        String list = "";

        for (int i = 0; i < size; i++) {
            list = list + makeIndent((i + 1) + ". " + tasks.getTask(i).toString() + "\n");
        }

        printMessage(list);
    }

    public void showError(String errMsg) {
        System.out.println(makeIndent(errMsg));
    }

    public void showLoadingError() {
        showError("There are no data about tasks found!");
    }
}

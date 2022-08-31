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

    public String printMessage(String message) {
        return LINE + "\n" + message + "\n" + LINE + "\n";
    }

    public String makeIndent(String message) {
        return INDENTATION + message;
    }

    public String displayGreeting() {
        return printMessage(makeIndent("Hi, how are you doing?! I'm JRH2000\n") +
                makeIndent("How can I help you?\n"));
    }

    public String displayBye() {
        return printMessage(makeIndent("Sigh...abandoned again. See you again next time :("));
    }

    public String displayMark(Task markedTask) {
        return printMessage(makeIndent("Alright then! This task is marked as done: \n") +
                makeIndent(markedTask.toString()) + "\n");
    }

    public String displayUnmark(Task unmarkedTask) {
        return printMessage(makeIndent("Oh OK, this task is now marked as not done yet: \n") +
                makeIndent(unmarkedTask.toString()) + "\n");
    }

    public String displayDelete(Task deletedTask) {
        return printMessage(makeIndent("Fine. I've removed this task: \n") +
                makeIndent(deletedTask.toString()) + "\n");
    }

    public String displayAdd(Task addedTask, int size) {
        return printMessage(makeIndent("Sure thing! I've added this task: \n") +
                makeIndent(addedTask.toString()) + "\n" +
                makeIndent("Now you have " + size + " task(s) in the list.\n"));
    }

    public String displayMatch(TaskList matchedTasks) {
        int size = matchedTasks.getSize();

        String list = "These tasks matches your keyword: \n";

        for (int i = 0; i < size; i++) {
            list = list + makeIndent((i + 1) + ". " + matchedTasks.getTask(i).toString() + "\n");
        }

        return printMessage(list);
    }

    public String displayList(TaskList tasks) {
        int size = tasks.getSize();

        String list = "";

        for (int i = 0; i < size; i++) {
            list = list + makeIndent((i + 1) + ". " + tasks.getTask(i).toString() + "\n");
        }

        return printMessage(list);
    }

    public String showError(String errMsg) {
        return makeIndent(errMsg);
    }

    public String showLoadingError() {
        return showError("There are no data about tasks found!");
    }
}

package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String LOGO = "\t ____        _\n"
            + "\t|  _ \\ _   _| | _____\n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";
    protected static final String LINE = "\t____________________________________________________________";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.printf("%s%n%s%s%s%s%n", LINE, LOGO, LINE,"\n\tHello! I'm Duke\n\tWhat can I do for you?\n", LINE);
    }

    public void showBye() {
        System.out.println("\tBye. Hope to see you again soon!");
        sc.close();
    }

    public void showList(TaskList tasks) {
        System.out.printf("%s%n%s%n", "\tHere are the task(s) in your list: ", tasks.toString());
    }

    public void showMark(Task task) {
        System.out.printf("%s%s%n", "\tNice! I've marked this task as done:\n\t  ", task);
    }

    public void showUnmark(Task task) {
        System.out.printf("%s%s%n", "\tOK, I've marked this task as not done yet:\n\t  ", task);
    }

    public void showAdd(Task task, int size) {
        System.out.printf("%s%s%s%s%s", "\tGot it. I've added this task:\n\t  ", task, "\n\tNow you have ",
                size, " task(s) in the list.\n");
    }

    public void showDelete(Task task, int size) {
        System.out.printf("%s%s%s%s%s", "\tNoted. I've removed this task:\n\t  ", task, "\n\tNow you have ",
                size, " task(s) in the list.\n");
    }

    public void showFind(ArrayList<Task> foundTasks) {
        String lst = "";
        int size = foundTasks.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                lst += String.format("\t%d.%s", i + 1, foundTasks.get(i));
            } else {
                lst += String.format("\t%d.%s\n", i + 1, foundTasks.get(i));
            }
        }
        System.out.printf("%s%n%s%n", "\tHere are the matching task(s) in your list: ", lst);
    }

    public void showLoadingError() {
        System.out.printf("%s%s", LINE, "\n\tSave data does not exist.\n");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}

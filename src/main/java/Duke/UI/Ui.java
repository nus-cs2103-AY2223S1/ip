package Duke.UI;

import Duke.Task.Task;
import Duke.TaskList.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String TAB = "    ";
    private static final String LINE = String
            .format("%s%s", TAB, "____________________________________________________________");
    private static final String WELCOME_MESSAGE = String
            .format("Hello! I'm Duke\n%s What can I do for you?", TAB);
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String CREATE_MESSAGE = "Got it. I've added this task:";
    private static final String MARK_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARK_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";

    // Error Messages
    private static final String ERROR_PREFIX = "DukeError";

    // scanner
    private final Scanner sc = new Scanner(System.in);

    public void printWelcome() {
        prettyPrint(WELCOME_MESSAGE);
    }

    public void printGoodbye() {
        sc.close();
        prettyPrint(GOODBYE_MESSAGE);
    }

    public void printException(Exception e) {
        prettyPrint(String.format("%s: %s", ERROR_PREFIX, e.toString()));
    }

    public void printTaskCreated(Task task) {
        String taskStr = task.toString();
        prettyPrint(String.format("%s\n%s %s", CREATE_MESSAGE, TAB,  taskStr));
    }

    public void printTaskMarked(Task task) {
        String taskStr = task.toString();
        prettyPrint(String.format("%s\n%s %s", MARK_MESSAGE, TAB, taskStr));
    }

    public void printTaskUnmarked(Task task) {
        String taskStr = task.toString();
        prettyPrint(String.format("%s\n%s %s", UNMARK_MESSAGE, TAB, taskStr));
    }

    public void printTaskDeleted(Task task) {
        String taskStr = task.toString();
        prettyPrint(String.format("%s\n%s %s", DELETE_MESSAGE, TAB,  taskStr));
    }

    public void printAll(TaskList taskList) {
        List<Task> taskArrayList = taskList.getTaskList();
        List<String> printables = new ArrayList<>();
        printables.add(LIST_MESSAGE);
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            int index = i + 1;
            printables.add(String.format("%d.%s", index, task.toString()));
        }
        prettyPrint(printables);
    }

    private void prettyPrint() {
        prettyPrint("");
    }

    private void prettyPrint(String printable) {
        String out = String.format("%s\n%s %s\n%s", LINE, TAB, printable, LINE);
        System.out.println(out);
    }

    private void prettyPrint(List<String> printables) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < printables.size(); i++) {
            String s = printables.get(i);
            if (i == 0) {
                sb.append(s);
            }
            else {
                sb.append(String.format("\n%s %s", TAB, s));
            }
        }
        String printable = sb.toString();
        prettyPrint(printable);
    }

    public String getNext() {
        return sc.next();
    }

    public String getNextLine() {
        return sc.nextLine();
    }
}

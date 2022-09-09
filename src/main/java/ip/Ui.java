package ip;

import java.util.ArrayList;
import java.util.List;

class Ui {
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
    private static final String LOADING_ERROR_MESSAGE = "Error encountered while loading file!";

    protected void printWelcome() {
        prettyPrint(WELCOME_MESSAGE);
    }

    protected void printGoodbye() {
        prettyPrint(GOODBYE_MESSAGE);
    }

    protected void printLoadingError(String errorMessage) {
        prettyPrint(String.format("%s: %s - %s", ERROR_PREFIX, LOADING_ERROR_MESSAGE, errorMessage));
    }

    protected void printAll(TaskList taskList) {
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

    protected void prettyPrint() {
        prettyPrint("");
    }

    protected void prettyPrint(String printable) {
        String out = String.format("%s\n%s %s\n%s", LINE, TAB, printable, LINE);
        System.out.println(out);
    }

    protected void prettyPrint(List<String> printables) {
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
}

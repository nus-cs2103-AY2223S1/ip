package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {
    private String logo;
    private Scanner scanner;

    public Ui() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.scanner = new Scanner(System.in);
    }

    public final String readCommand() {
        return scanner.nextLine();
    }

    public String showWelcome() {
        return "Hello from\n" + logo + "I'm Duke! \n What can I do for you?";
    }

    public String showError(DukeException error) {
        return addLineBreak(error.getMessage());
    }

    public String addLineBreak(String text) {
        return "\n" + text + "\n";
    }

    public void print(String text) {
        System.out.println(text);
    }

    public String showLine() {
        return "____________________________________________________________\n";
    }

    public String showArray(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        if (list.size() == 0) {
            return addLineBreak("You have no tasks in your list.");
        } else {
            String result = "Here are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                result += "\t" + (i + 1) + ". " + list.get(i) + "\n";
            }
            return addLineBreak(result);
        }
    }

    public String showExitMessage() {
        return addLineBreak("Goodbye! Hope to see you again!");
    }

    public String showAddMessage(Task task, TaskList taskList) {
        return addLineBreak(
                "Got it. I've added this task:\n" + task + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public String showDeleteMessage(Task task) {
        return addLineBreak("Noted. I've removed this task:\n" + task);
    }

    public String showMarkMessage(Task task) {
        return addLineBreak("Nice! I've marked this task as done:\n" + task);
    }

    public String showUnmarkMessage(Task task) {
        return addLineBreak("Okay, I've unmarked this task as undone:\n" + task);
    }
}

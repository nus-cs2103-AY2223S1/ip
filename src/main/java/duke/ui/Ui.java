package duke.ui;

import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;

public class Ui {

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public String printOnAdd(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n " + task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String printOnDelete(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n " + task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String printOnMark(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    public String printOnUnmark(Task task) {
        return "Ok. I've marked this task as not done yet: \n" + task.toString();
    }

    public String printTaskList(TaskList taskList) {
        return taskList.toString();
    }

    public String printOnFind(List<Task> found) {
        StringBuilder sb = new StringBuilder();
        if (found.size() == 0) {
            printNoTasks(found, sb);
        } else {
            printTasks(found, sb);
        }
        return sb.toString();
    }

    public void printNoTasks(List<Task> found, StringBuilder sb) {
        String str = ("There are currently no tasks in the list.");
        sb.append(str);
    }

    public void printTasks(List<Task> found, StringBuilder sb) {
        String str = ("Here are the tasks in your list: \n");
        sb.append(str);
        for (int i = 0; i < found.size(); i++) {
            str = ("\t" + (i+1) + ".\t " + found.get(i).toString());
            sb.append(str);
            if (i != found.size() - 1) {
                sb.append("\n");
            }
        }
    }

    public String showDukeException(DukeException e) {
        return e.toString();
    }

    public String printOnHelp() {
        return "Need help? I got you!\n " +
                "1) To add a task, type: \n " +
                "i. todo <name of todo> OR \n" +
                "ii. deadline <name of deadline> /by <date in YYYY-MM-DD> OR \n" +
                "iii. event <name of event> /at <event location>\n" +
                "2) To mark/unmark a task, type: \n" +
                "i. mark <index of task> OR \n" +
                "ii. unmark <index of task> \n" +
                "3) To delete a task, type: delete <index of task> \n" +
                "4) To view the tasklist, type: list \n" +
                "5) To search for a keyword, type: find <keyword>";
    }
}

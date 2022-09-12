package duke.ui;

import duke.DukeException;
import duke.Task;
import duke.TaskList;

import java.util.List;

public class Ui {

    public String start() {
        return "Hello! I'm duke.Duke\n" + "What can I do for you?";
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public String printOnAdd(Task task, TaskList taskList) {
        return "Got it. I've added this duke.task:\n " + task.toString() +
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
            String str = ("There are currently no tasks in the list.");
            sb.append(str);
        } else {
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
        return sb.toString();
    }

    public String showDukeException(DukeException e) {
        return e.toString();
    }
}

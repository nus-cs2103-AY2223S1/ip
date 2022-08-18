package duke;

import duke.exceptions.CannotFindTaskException;
import duke.exceptions.DukeException;
import duke.exceptions.TaskMarkedException;
import duke.exceptions.TaskUnmarkedException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /*
    public Task toggleTaskStatus(Task task) throws CannotFindTaskException{
        for (int i = 0; i < taskList.size(); i ++) {
            Task curr = taskList.get(i);
            if (curr.equals(task)) {
                curr.toggleStatus();
                return curr;
            }
        }
        throw new CannotFindTaskException();
    }
    */

    public Task markStatus(int task) throws DukeException {
        try {
            Task curr = taskList.get(task - 1);
            if (curr.isDone()) {
                throw new TaskMarkedException(task);
            }
            curr.toggleStatus();
            return curr;
        } catch (IndexOutOfBoundsException e) {
            throw new CannotFindTaskException();
        }
    }

    public Task unmarkStatus(int task) throws DukeException {
        try {
            Task curr = taskList.get(task - 1);
            if (!curr.isDone()) {
                throw new TaskUnmarkedException(task);
            }
            curr.toggleStatus();
            return curr;
        } catch (IndexOutOfBoundsException e) {
            throw new CannotFindTaskException();
        }
    }
    @Override
    public String toString() {
        Object[] taskArr = this.taskList.toArray();
        String result = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            result += (i + 1) + ". " + taskArr[i].toString() + "\n";
        }
        return result;
    }

    public String numOfTask() {
        if (taskList.size() > 1) {
            return "YOU HAVE " + taskList.size() + " TASKS!";
        } else {
            return "YOU HAVE " + taskList.size() + " TASK!";
        }
    }
}

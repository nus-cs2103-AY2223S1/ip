package duke;

import duke.exceptions.DukeException;
import duke.exceptions.CannotFindTaskException;
import duke.exceptions.NoMatchingKeywordException;
import duke.exceptions.TaskUnmarkedException;
import duke.exceptions.TaskMarkedException;


import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }


    public TaskList findTask(String keyword) throws NoMatchingKeywordException {
        TaskList successList = new TaskList();
        for (int i = 0; i < taskList.size(); i ++) {
            Task curr = taskList.get(i);
            if (curr.isMatchKeyword(keyword)) {
                successList.addTask(curr);
            }
        }
        if (successList.size() == 0) {
            throw new NoMatchingKeywordException(keyword);
        }
        return successList;
    }

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

    public Task deleteTask(int task) throws DukeException {
        try {
            Task curr = taskList.get(task - 1);
            taskList.remove(task - 1);
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
            result += (i + 1)
                    + ". "
                    + taskArr[i].toString()
                    + "\n";
        }
        return result;
    }

    public int size() {
        return taskList.size();
    }
    public String numOfTask() {
        if (taskList.size() > 1) {
            return "YOU HAVE "
                    + taskList.size()
                    + " TASKS!";
        } else {
            return "YOU HAVE "
                    + taskList.size()
                    + " TASK!";
        }
    }

    public String generateSave() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += this.taskList.get(i).toSaveVersion();
        }
        return result;
    }

}

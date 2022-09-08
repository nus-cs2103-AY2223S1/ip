package duke;

import duke.exceptions.DukeException;
import duke.exceptions.CannotFindTaskException;
import duke.exceptions.NoMatchingKeywordException;
import duke.exceptions.TaskUnmarkedException;
import duke.exceptions.TaskMarkedException;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }


    public TaskList findTask(String keyword) throws NoMatchingKeywordException {
        assert !keyword.isBlank(): "keyword should not be blank";
        List<Task> filtered = this.taskList.stream()
                .filter(task -> task.isMatchKeyword(keyword))
                .collect(Collectors.toList());
        TaskList filteredTaskList = new TaskList(filtered);
        
        if (filteredTaskList.size() == 0) {
            throw new NoMatchingKeywordException(keyword);
        }
        return new TaskList(filtered);
    }

    public Task markStatus(int task) throws DukeException {
        assert task >= 0: "task index should be more than or equal to 0";
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
        assert task >= 0: "task index should be more than or equal to 0";
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
        assert task >= 0: "task index should be more than or equal to 0";
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
        String result = "";
        Object[] taskArr = this.taskList.toArray();
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
        String result = taskList
                .stream()
                .map(task -> task.toSaveVersion())
                .reduce("", (res, task) -> res + task);
        return result;
    }
}

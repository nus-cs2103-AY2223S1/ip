package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<String> strList) {
        this.taskList = new ArrayList<>();
        for (String s : strList) {
            this.taskList.add(Parser.parseTaskFromText(s));
        }
    }


    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to be mark done
     */
    public void markTaskAsDone(int index) throws DukeException {
        if (1 <= index && index <= taskList.size()) {
            Task currTask = taskList.get(index - 1);
            currTask.markAsDone();
            Ui.markedDoneMessage(currTask);
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Unmarks the specified task as not done.
     *
     * @param index The index of the task to be unmarked
     */
    public void unmarkTask(int index) throws DukeException {
        if (1 <= index && index <= taskList.size()) {
            Task currTask = taskList.get(index - 1);
            currTask.markUndone();
            Ui.markUndoneMessage(currTask);
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Deletes the specified task.
     *
     * @param index The index of the task to be deleted
     */
    public void deleteTask(int index) throws DukeException {
        if (1 <= index && index <= taskList.size()) {
            System.out.println("Noted. I've removed this task:\n  " + taskList.get(index - 1));
            taskList.remove(index - 1);
            System.out.println("Now you have " + taskList.size() + " taskList in the list.");
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }
}

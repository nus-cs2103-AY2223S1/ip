package task;

import exceptions.InvalidIndexException;
import utils.Prompt;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<? super Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void listTask() {
        System.out.println("\nCurrent Tasking");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ") " + taskList.get(i - 1));
        }
        System.out.println("Number of tasking: " + taskList.size());
    }

    public void addTask(Task task) {
        taskList.add(task);
        Prompt.addTask(task);
    }

    public void deleteTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) taskList.remove(index - 1);
        Prompt.deleteTask(task);
    }

    public void checkTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) taskList.get(index - 1);
        task.markDone();
    }

    public void uncheckTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) taskList.get(index - 1);
        task.markUndone();
    }

    private void validateIndex(int index) throws InvalidIndexException {
        if (index < 0 || index >= taskList.size()) {
            String message;
            switch (taskList.size()) {
                case 0:
                    message = "Please add a task first!";
                    break;
                case 1:
                    message = "Please choose the index 1";
                    break;
                default:
                    message = "Please choose an index between 1 and " + taskList.size();
                    break;
            }
            throw new InvalidIndexException(message);
        }
    }
}

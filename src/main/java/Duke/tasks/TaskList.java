package Duke.tasks;

import Duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskArrayList) throws DukeException {
        if (taskArrayList.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! Seems like your list is empty.");
        } else {
            taskList = taskArrayList;
        }
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return taskList.size() == 0;
    }

    public int size() {
        return taskList.size();
    }

    public void printList() {
        try {
            if (isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! Seems like your list is empty.");
            } else {
                for (int i = 0; i < taskList.size(); i++)
                    System.out.println("     " + (i + 1) + ". " + taskList.get(i).toString());
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    public void markTask(int taskIndex) {
        taskList.get(taskIndex).mark();
    }

    public void unmarkTask(int taskIndex) {
        taskList.get(taskIndex).unmark();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int taskID) {
        return taskList.get(taskID);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }
}

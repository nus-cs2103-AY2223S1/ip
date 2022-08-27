package ploopy;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private UI ui;
    private Storage storage;

    public TaskList(UI ui, Storage storage) {
        taskList = new ArrayList<>();
        this.ui = ui;
        this.storage = storage;
    }

    public void displayList() {
        ui.showTopWindow();
        int index = 1;
        for (Task item : taskList) {
            System.out.println("\n" + index + "." + item);
            index++;
        }
        ui.showBottomWindow();
    }

    public void markTask(int taskIndex) throws PloopyException {
        Task current = taskList.get(taskIndex - 1);
        current.markDone();
        ui.markTaskMessage(current);
        storage.rewriteFile(taskList);
    }

    public void unmarkTask(int taskIndex) throws PloopyException {
        Task current = taskList.get(taskIndex - 1);
        current.unmark();
        ui.unmarkTaskMessage(current);
        storage.rewriteFile(taskList);
    }

    public void deleteTask(int taskNumber) throws PloopyException {
        ui.deleteTaskMessage(taskList.get(taskNumber - 1), taskList.size() - 1);
        taskList.remove(taskNumber - 1);
        storage.rewriteFile(taskList);
    }

    public void createToDo(String input) throws PloopyException {
        Task newTask = new ToDo(input);
        taskList.add(newTask);
        ui.addTaskMessage(newTask, taskList.size());
        storage.writeToFile(newTask);
    }

    public void createDeadline(String name, String date) throws PloopyException {
        Task newTask = new Deadline(name, date);
        taskList.add(newTask);
        ui.addTaskMessage(newTask, taskList.size());
        storage.writeToFile(newTask);
    }

    public void createEvent(String name, String date) throws PloopyException {
        Task newTask = new Event(name, date);
        taskList.add(newTask);
        ui.addTaskMessage(newTask, taskList.size());
        storage.writeToFile(newTask);
    }

    public void findTasks(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getName().contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        if (matchedTasks.size() > 0) {
            ui.showTopWindow();
            ui.foundTasks();
            int index = 1;
            for (Task matched : matchedTasks) {
                System.out.println("\n" + index + "." + matched);
                index++;
            }
            ui.showBottomWindow();
        } else {
            ui.noTasksFound();
        }

    }

    public void addTasksFromFile(String input) {
        String[] inputSequence = input.split("_");
        String type = inputSequence[0];
        String name = inputSequence[2];
        String date = inputSequence.length > 3 ? inputSequence[3] : "";
        Task createdTask = Task.addTaskFromFile(type, name, date);
        if (inputSequence[1].equals("1")) {
            createdTask.markDone();
        }
        taskList.add(createdTask);
    }
}

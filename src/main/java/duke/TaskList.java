package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private static List<Task> savedTasks;

    public TaskList() {
        TaskList.savedTasks = new ArrayList<Task>();
    }

    public TaskList(String savedTasks) throws DukeException {
        Scanner s = new Scanner(savedTasks);
        TaskList.savedTasks = new ArrayList<Task>();

        while (s.hasNext()) {
            String[] inputArr = s.nextLine().split(" \\| ");

            String taskType = inputArr[0];
            Task newTask = null;

            if (taskType.equals("T")) {
                newTask = new ToDo();
                newTask.addName("todo " + inputArr[2]);
            } else if (taskType.equals("E")) {
                newTask = new Event();
                newTask.addName("event " + inputArr[2] + " /at " + inputArr[3]);
            } else if (taskType.equals("D")) {
                newTask = new DeadLine();
                newTask.addName("deadline " + inputArr[2] + " /by " + inputArr[3]);
            } else {
                throw new DukeException("File is corrupted!");
            }

            if (inputArr[1].equals("1")) {
                newTask.markAsDone();
            }
            TaskList.savedTasks.add(newTask);
        }
    }

    public void add(Task task) {
        TaskList.savedTasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task.getStatus() +
                String.format("\nNow you have %d tasks in the list.", TaskList.savedTasks.size()));
    }

    public void delete(int index) throws DukeException {
        int size = TaskList.savedTasks.size();
        if (index <= 0 || index > size) {
            throw new DukeException("Index has to be between 1 and " + String.valueOf(size));
        } else {
            Task removedTask = TaskList.savedTasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + removedTask.getStatus()
                    + String.format("\nNow you have %d tasks in the list.", size - 1));
        }
    }

    public void mark(int index) throws DukeException {
        int size = TaskList.savedTasks.size();
        if (index <= 0 || index > size) {
            throw new DukeException("Index has to be between 1 and " + String.valueOf(size));
        } else {
            Task selectedTask = TaskList.savedTasks.get(index - 1);
            selectedTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + selectedTask.getStatus());
        }
    }

    public void unmark(int index) throws DukeException {
        int size = TaskList.savedTasks.size();
        if (index <= 0 || index > size) {
            throw new DukeException("Index has to be between 1 and " + String.valueOf(size));
        } else {
            Task selectedTask = TaskList.savedTasks.get(index - 1);
            selectedTask.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + selectedTask.getStatus());
        }
    }

    public String getList() {
        String listString = "";
        for (int i = 0; i < savedTasks.size(); i++) {
            listString += String.format("%d. %s", i + 1, savedTasks.get(i).getStatus());
            if (i != savedTasks.size() - 1) {
                listString += "\n";
            }
        }
        return listString;
    }

    public String writeTasks() {
        String dataWritten = "";

        for (int i = 0; i < TaskList.savedTasks.size(); i++) {
            dataWritten += TaskList.savedTasks.get(i).getTask();

            if (i != TaskList.savedTasks.size() - 1) {
                dataWritten+= "\n";
            }
        }

        return dataWritten;
    }



}

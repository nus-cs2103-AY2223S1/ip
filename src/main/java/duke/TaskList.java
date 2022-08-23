package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskList {
    private LinkedList<Task> tasks = new LinkedList<>();

    public TaskList(File dataFile) {
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String[] splitInput = scanner.nextLine().split(",");
                int len = splitInput.length;
                if (len == 0) {
                    break;
                }
                String taskType = splitInput[0];
                String taskDescription = splitInput[2];
                boolean isDone = splitInput[1].equals("1");
                switch (taskType) {
                    case "T":
                        Task todo = new Todo(taskDescription);
                        todo.setStatus(isDone);
                        tasks.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(taskDescription, splitInput[3]);
                        deadline.setStatus(isDone);
                        tasks.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(taskDescription, splitInput[3]);
                        event.setStatus(isDone);
                        tasks.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printList() {
        System.out.println("My List Of Tasks :D");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + tasks.get(i).toString());
        }
    }

    public void editTaskList(String taskType, String taskDescription, String date) {
        Task newTask = null;
        switch (taskType) {
        case "todo":
            newTask = new Todo(taskDescription);
            break;
        case "deadline":
            newTask = new Deadline(taskDescription, date);
            break;
        case "event":
            newTask = new Event(taskDescription, date);
            break;
        }
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:\n "
                + newTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void editTaskList(String cmd, int index) {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Duke: Looks like your task list currently does not have a task at this index.");
        }
        Task t = tasks.get(index);
        switch (cmd) {
        case "mark":
            t.markAsDone();
            break;
        case "unmark":
            t.markAsNotDone();
            break;
        case "delete":
            tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n "+ t.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list");
            break;
        }
    }


    public String toCsv() {
        String csv = "";
        for (int i = 0; i < tasks.size(); i++) {
            csv += tasks.get(i).toCsv();
        }
        return csv;
    }
}

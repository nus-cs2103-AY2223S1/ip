package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * 'List' object attribute to store String inputs given.
     */
    private static final List<Task> taskList = new ArrayList<>();

    /**
     * Get description as String from input.
     * @param input String of words given by user as input
     * @return      Return description of input task.
     */
    private String getDescription(String input) {
        if (input.startsWith("deadline")) {
            return input.substring(9, input.indexOf("/by ") - 1);
        } else if (input.startsWith("event")) {
            return input.substring(6, input.indexOf("/at ") - 1);
        } else if (input.startsWith("todo")){
            return input.substring(5);
        }
        return null;
    }

    /**
     * Get venue as String from input.
     * @param input String of words given by user as input
     * @return      Return venue of input task.
     */
    private String getVenue(String input) {
        return input.substring(input.indexOf("/at ") + 4);
    }

    /**
     * Get venue as LocalDate from input.
     * @param input String of words given by user as input
     * @return      Return date of input task.
     */
    private LocalDate getDate(String input) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dateString = input.substring(input.indexOf("/by ") + 4);
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Method to add String input to 'inputList'.
     * @param input String object to be added to 'inputList'.
     * @return      String output to be shown to user.
     */
    public String add(String input) {
        String[] inputArr = input.split(" ");

        Task newTask = null;
        switch (inputArr[0]) {
            case "deadline":
                newTask = new Deadline(getDescription(input), getDate(input));
                break;
            case "event":
                newTask = new Event(getDescription(input), getVenue(input));
                break;
            case "todo":
                newTask = new ToDo(getDescription(input));
                break;
        }

        taskList.add(newTask);
        int size = this.getSize();
        return String.format("Got it. I've added this task:%n   %s%n" +
                "Now you have %d task%s in the list.%n",
                newTask, size, size == 1 ? "" : "s");
    }


    /**
     * Method to delete a task.
     * @param input String user input command to delete a task.
     * @return      String output to be shown to user.
     */
    public String delete(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        String taskToDelete = taskList.get(index).toString();
        taskList.remove(index);
        int size = this.getSize();
        return String.format("Got it. I've removed this task:%n   %s%n" +
                "Now you have %d task%s in the list.%n",
                taskToDelete, size, size == 1 ? "" : "s");
    }

    /**
     * Override 'toString' method of 'TaskList' object.
     * @return      String output to be shown to user.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(
                String.format("Here are the tasks in your list:%n"));
        for (int i = 0; i < taskList.toArray().length; i++) {
            res.append(String.format("%d. %s%n", i + 1, taskList.get(i)));
        }
        return res.toString();
    }

    /**
     * Mark 'Task' at given index as done.
     * @param index Index of task to be done. 1 based indexing.
     * @return      String output to be shown to user.
     */
    public String markDone(int index) {
        taskList.get(index - 1).markDone();
        return String.format("Nice! I've marked this task as done:%n   %s%n",
                taskList.get(index - 1));
    }

    /**
     * Mark 'Task' at given index as undone.
     * @param index Index of task to be undone. 1 based indexing.
     * @return      String output to be shown to user.
     */
    public String markUnDone(int index) {
        taskList.get(index - 1).markUnDone();
        return String.format(
                "OK, I've marked this task as not done yet:%n   %s%n",
                taskList.get(index - 1));
    }

    /**
     * Method to get number of tasks in 'taskList'.
     * @return Number of tasks in 'taskList'.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Return 'taskList' as format to be saved in hard disk.
     * @return String of 'taskList' as format to be saved in file.
     */
    public String toFile() {
        StringBuilder res = new StringBuilder();
        for (Task task : taskList) {
            res.append(task.toFileFormat() + "\n");
        }
        return res.toString();
    }

    /**
     * Method to add tasks from file to `taskList`.
     * @param dataArgs Array containing details of task to be
     *                 added to `inputList`.
     */
    public void addFromFile(String[] dataArgs) {
        Boolean isDone = dataArgs[1].equals("true");
        Task newTask = null;

        switch (dataArgs[0]) {
        case ("deadline"):
            LocalDate deadline = LocalDate.parse(dataArgs[3],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            newTask = new Deadline(dataArgs[2], deadline, isDone);
            break;

        case ("event"):
            newTask = new Event(dataArgs[2], dataArgs[3], isDone);
            break;

        case ("todo"):
            newTask = new ToDo(dataArgs[2], isDone);
            break;
        }
        taskList.add(newTask);
    }
}
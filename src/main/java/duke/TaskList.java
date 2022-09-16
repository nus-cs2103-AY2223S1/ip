package duke;

import duke.task.*;

import java.security.KeyPair;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/** A class that manages the Tasks in the to-do list. */
public class TaskList {

    /** The list of tasks in the to-do list */
    private static ArrayList<Task> taskArr;

    public TaskList() {
        this.taskArr = new ArrayList<>();
    }
    public TaskList(ArrayList<String> taskArray) throws DukeException {
        this.taskArr = new ArrayList<>();
        if (taskArray == null) {
            throw new DukeException("Sorry, I encountered an error while trying to load your Task List :(");
        }
        for (int i = 0; i < taskArray.size(); i++) {
            String[] splitTask = taskArray.get(i).split(",", 2);
            addTask(splitTask[0], splitTask[1]);
        }
    }

    /**
     * Adds a tasks to the to-do list.
     *
     * @param keyword The String representing the type of task to add.
     * @param input The rest of the input from the user, including the task description and deadline, if applicable.
     * @throws DukeException If the task is invalid and cannot be added to the to-do list.
     */
    public void addTask(String keyword, String input) throws DukeException {
        if (keyword.equals("todo")) {
            Task todoTask = new Todo(input);
            taskArr.add(todoTask);
        } else {
            int slashChar = input.indexOf("/");

            if (slashChar == -1) {
                throw new DukeException("oops, looks like you're missing the command to tell me the deadline");
            }
            assert slashChar > 0;
            String taskDesc = input.substring(0, slashChar);
            String deadlineInput = input.substring(slashChar + 1);
            if (taskDesc.isBlank() || deadlineInput.isBlank()) {
                throw new DukeException("oops, the description of your task seems to be incomplete!");
            } else {
                try {
                    String taskDate = input.substring(slashChar + 4);
                    LocalDate deadline = LocalDate.parse(taskDate);
                    if (keyword.equals("deadline")) {
                        taskArr.add(new Deadline(taskDesc,
                                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                    } else if (keyword.equals("event")) {
                        taskArr.add(new Event(taskDesc, deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                    }
                } catch (DateTimeParseException e) {
                    throw new DukeException("oops, your date seems to be of an invalid format");
                }
            }
        }
    }

    /**
     * Deletes a task from the to-do list.
     *
     * @param input The task number to delete.
     * @param ui The UI to display relevant messages to the user.
     */
    public String deleteTask(String input, Ui ui) {
        Integer taskNo = Integer.valueOf(input) - 1;
        assert taskNo > -1;
        Task taskToRemove = this.taskArr.get(taskNo);
        this.taskArr.remove(getTask(taskNo));
        return ui.showDeletingTask(taskToRemove.toString());
    }

    /**
     * Marks a task in the to-do list as done or undone.
     *
     * @param keyword The command on whether to mark as done or undone.
     * @param input The task number to mark.
     * @param ui The UI to display relevant messages to the user.
     */
    public String markTask(String keyword, String input, Ui ui) {
        Integer taskNo = Integer.valueOf(input) - 1;
        switch (keyword) {
            case "mark":
                this.taskArr.get(taskNo).markAsDone();
                return ui.showMarkedTask(this.taskArr.get(taskNo).toString());
            case "unmark":
                this.taskArr.get(taskNo).markAsUndone();
                return ui.showUnmarkedTask(this.taskArr.get(taskNo).toString());
        }
        return "oops, I am unable to mark your task";
    }

    /**
     * Find tasks that contain a specific keyword given by the user.
     *
     * @param wordToSearch The keyword given by the user.
     * @param ui The Ui object to display the found tasks to the user.
     */
    public String findTasks(String wordToSearch, Ui ui) {
        ArrayList<Integer> taskNo = new ArrayList<>();
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (Integer i = 0; i < taskArr.size(); i++) {
            if (taskArr.get(i).toString().contains(wordToSearch)) {
                taskNo.add(i);
                relevantTasks.add(taskArr.get(i));
            }
        }
        return ui.showFoundTasks(taskNo, relevantTasks);
    }

    /**
     * Gets the most recent task added to the to-do list.
     *
     * @return The retrieved Task.
     */
    public Task getRecentTask() {
        return this.taskArr.get(taskArr.size() - 1);
    }

    public int getNoOfTasks() {
        return this.taskArr.size();
    }

    public Task getTask(int taskNo) {
        return this.taskArr.get(taskNo);
    }
}

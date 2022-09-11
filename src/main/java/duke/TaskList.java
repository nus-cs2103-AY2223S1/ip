package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Class that stores and manipulates tasks for Duke Bot.
 */
public class TaskList {
    protected final ArrayList<Task> tasks;
    private final Storage store;
    private final Ui ui;

    /**
     * Class constructor for TaskList.
     *
     * @param uiInstance Ui instance being used in Duke Bot.
     */
    public TaskList(Ui uiInstance) {
        tasks = new ArrayList<>();
        store = new Storage();
        ui = uiInstance;
    }

    /**
     * Adds a task to TaskList.
     *
     * @param task Task to be added.
     * @return Response string from Duke Bot.
     * @throws DukeException If duplicate task is added.
     */
    public String addTask(Task task) throws DukeException {
        if (checkDuplicate(task)) {
            throw new DukeException("no duplicate tasks pls");
        }
        tasks.add(task);
        return ui.printMessage("Got it. I've added this task:\n      "
                + task
                + "\n    Now you have "
                + tasks.size()
                + " tasks in the list.");
    }

    private boolean checkDuplicate(Task task) {
        for (Task t: tasks) {
            if (task.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a task from TaskList.
     *
     * @param id Index of task to be deleted. Index is numbering from calling "list" command.
     * @return Response string from Duke Bot.
     * @throws DukeException If id is out of bounds.
     */
    public String deleteTask(int id) throws DukeException {
        if (id < 0 || id >= tasks.size()) {
            throw new DukeException("Invalid index for deleting");
        }
        Task t = tasks.remove(id);
        return ui.printMessage("Noted. I've removed this task:\n      "
                + t
                + "\n    Now you have "
                + tasks.size()
                + " tasks in the list.");
    }

    /**
     * Marks a task from TaskList as done.
     *
     * @param id Index of task to be marked. Index is numbering from calling "list" command.
     * @return Response string from Duke Bot.
     * @throws DukeException If id is out of bounds.
     */
    public String markTask(int id) throws DukeException {
        if (id < 0 || id >= tasks.size()) {
            throw new DukeException("Invalid index for marking");
        }
        Task t = tasks.get(id);
        t.setAsDone();
        return ui.printMessage("Nice! I've marked this task as done:\n    " + t);
    }

    /**
     * Marks a task from TaskList as undone.
     *
     * @param id Index of task to be unmarked. Index is numbering from calling "list" command.
     * @return Response string from Duke Bot.
     * @throws DukeException If id is out of bounds.
     */
    public String unmarkTask(int id) throws DukeException {
        if (id < 0 || id >= tasks.size()) {
            throw new DukeException("Invalid index for unmarking");
        }
        Task t = tasks.get(id);
        t.setAsUndone();
        return ui.printMessage("OK, I've marked this task as not done yet:\n    " + t);
    }

    /**
     * Prints list of tasks in TaskList that contains identifier.
     *
     * @param identifier String to search for tasks.
     * @return Response string from Duke Bot.
     */
    public String findTask(String identifier) {
        String messageList = "";
        int taskCount = 1;
        for (Task t: tasks) {
            if (t.toString().contains(identifier)) {
                messageList += "\n    " + taskCount++ + ". " + t;
            }
        }
        return ui.printMessage("Here are the matching tasks in your list:\n    " + messageList);
    }

    /**
     * Returns number of tasks in TaskList
     *
     * @return Size of TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Prints list representation of TaskList.
     * Ui instance is used to print this list.
     *
     * @return Response string from Duke Bot.
     */
    public String generateList() {
        String messageList = "";
        int taskCount = 1;
        for (Task t: tasks) {
            messageList += "\n    " + taskCount++ + ". " + t;
        }
        return ui.printMessage("Here are the tasks in your list:\n    " + messageList);
    }

    /**
     * Loads tasks from previous instance of Duke Bot.
     * Previous tasks are obtained from an instance of Storage.
     * These previous tasks are presented as Duke Bot commands and are loaded back into TaskList by parsing them.
     *
     * @param parser Parser being used for Duke Bot.
     */
    public void loadTasks(Parser parser) {
        String tasks = store.getPreviousText();
        if (tasks != null && tasks.length() <= 0) {
            return;
        }
        String[] taskSplit = tasks.split("\n");
        for (String s: taskSplit) {
            try {
                parser.parse(s, false);
            } catch (DukeException e) {
                System.out.println("Failed to load saved tasks: " + e);
            }
        }
    }

    /**
     * Saves task from current TaskList.
     * Tasks are saved using an instance of Storage.
     *
     * @return Response string from Duke Bot.
     */
    public String saveTasks() {
        for (Task t : tasks) {
            store.writeText(t.toStorageString(), true);
        }
        store.closeWriter();
        return ui.printMessage("Bye. Hope to see you again soon!");
    }
}

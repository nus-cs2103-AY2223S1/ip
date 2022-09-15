package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tag.Tag;
import duke.ui.Ui;

/**
 * Encapsulates a task list containing the tasks
 * that the user has requested for Duke to add.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs an empty task list.
     */
    public TaskList() {}

    /**
     * Constructs the task list using information about existing
     * tasks save in a data file.
     *
     * @param infoList A list containing information about the tasks to be added.
     */
    public TaskList(ArrayList<String[]> infoList) {
        for (String[] info : infoList) {
            Task t;

            switch (info[0]) {
            case "T":
                t = Parser.parseToDo(info);
                break;
            case "D":
                t = Parser.parseDeadline(info);
                break;
            case "E":
                t = Parser.parseEvent(info);
                break;
            default:
                t = null;
            }

            assert t != null : "Task description has not been correctly saved to file.";
            add(t);
            if (info[1].equals("1")) {
                t.setDone();
            }
        }
    }

    /**
     * Prints the contents of the task list to the user.
     *
     * @return A string containing the list of tasks.
     */
    public String printList() {
        if (tasks.isEmpty()) {
            return Ui.START + "your list is empty. start adding some tasks to do now!";
        }

        String reply = Ui.START + "these are the tasks in your list:";
        int x = 1;
        for (Task task : tasks) {
            reply += "\n  " + x + ". " + task.toString();
            x++;
        }

        assert x > 1 : "No tasks in task list.";
        return reply;
    }

    /**
     * Adds the given task to the task list.
     *
     * @param t Task to be added.
     * @return A string informing the user that the task was successfully added.
     */
    public String add(Task t) {
        String reply;

        tasks.add(t);
        reply = Ui.START + "added:\n" + "     " + t;

        if (tasks.size() == 1) {
            reply += "\n    you now have 1 task in the list. type list to see it!";
        } else {
            reply += "\n    now you have " + tasks.size() + " tasks in the list. type list to view them.";
        }

        return reply;
    }

    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param i Index of the task to delete
     * @return A string informing the user that the specified task was successfully deleted,
     *     or that there are not tasks to delete if the task list is empty.
     * @throws DukeException if the user entered an invalid index.
     */
    public String delete(int i) throws DukeException {
        if (tasks.isEmpty()) {
            return Ui.START + "hmm, you do not have any tasks in your list to delete. add some now!";
        }

        Task t;

        try {
            t = tasks.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.requestValidInput(tasks.size()));
        }

        String reply = "";
        reply += Ui.START + "okay! i have deleted the following task from your list:";
        reply += "\n     " + t;

        if (tasks.isEmpty()) {
            reply += "\n  your list is now empty. time to add some more!";
        } else if (tasks.size() == 1) {
            reply += "\n  you now have 1 task remaining in the list. type list to see it!";
        } else {
            reply += "\n  now you have " + tasks.size() + " tasks in the list. type list to view them.";
        }

        return reply;
    }

    /**
     * Searches for tasks occurring on the specified date and returns
     * a list of the results to the user.
     *
     * @param date Date to search for.
     * @return A list of tasks that start on the specified date.
     */
    public String search(LocalDate date) {
        String list = "";
        int x = 1;
        for (Task task : tasks) {
            if (task.isOn(date)) {
                list += "\n  " + x + ". " + task;
                x++;
            }
        }

        if (x == 1) {
            return Ui.START + "There are no tasks occurring on " + date + ".";
        } else {
            return Ui.START + "These are the tasks occurring on " + date + ":" + list;
        }
    }

    /**
     * Finds tasks containing the given keyword(s) and returns
     * a list of the results to the user.
     *
     * @param keyword Keyword(s) to search for.
     * @return A list of tasks with descriptions matching the keywords.
     */
    public String find(String keyword) {
        assert keyword.length() > 0: "No keywords passed.";
        String list = "";
        int x = 1;
        for (Task task : tasks) {
            if (task.containsSearchTerm(keyword)) {
                list += "\n  " + x + ". " + task;
                x++;
            }
        }

        if (x == 1) {
            return Ui.START + "there are no tasks matching the search term '"
                    + keyword + "'.";
        } else {
            return Ui.START + "These are the matching tasks in your list:" + list;
        }
    }

    /**
     * Marks the task at the specified position in the task list
     * as done.
     *
     * @param t Index of the task to mark.
     * @return A string informing the user that the specified task was successfully marked
     *     as done, or that there are no tasks to be marked if the task list is empty.
     * @throws DukeException if the user entered an invalid index.
     */
    public String mark(int t) throws DukeException {
        if (tasks.isEmpty()) {
            return Ui.START + "hmm, you do not have any tasks in your list to be marked. add some now!";
        }

        Task doneTask;

        try {
            doneTask = tasks.get(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.requestValidInput(tasks.size()));
        }

        doneTask.setDone();
        return Ui.START + "good job! this task has been marked as done:"
                + "\n     " + doneTask;
    }

    /**
     * Marks the task at the specified position of the task list as not done.
     *
     * @param t Index of the task to unmark.
     * @return A string informing the user that the specified task was unmarked, or that
     *     there are no tasks to be unmarked if the task list is empty.
     * @throws DukeException if the user entered an invalid index.
     */
    public String unmark(int t) throws DukeException {
        if (tasks.isEmpty()) {
            return Ui.START + "hmm, you do not have any tasks in your list. add some now!";
        }

        Task undoneTask;

        try {
            undoneTask = tasks.get(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.requestValidInput(tasks.size()));
        }

        undoneTask.setUndone();
        return Ui.START + "ok, i've marked this task as not done yet:"
                + "\n     " + undoneTask;
    }

    /**
     * Tags the task at the specified position of the task list with
     * the given tag.
     *
     * @param t Index of the task to tag.
     * @param tag Tag to tag the task with.
     * @return A string informing the user about whether the task was tagged or not.
     * @throws DukeException if the user entered an invalid index.
     */
    public String tag(int t, Tag tag) throws DukeException {
        if (tasks.isEmpty()) {
            return Ui.START + "you do not have any tasks in your list to be tagged. add some now!";
        }

        Task task;

        try {
            task = tasks.get(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.requestValidInput(tasks.size()));
        }

        return task.addTag(tag);
    }

    /**
     * Removes the given tag from the task at the specified position of the
     * task list.
     *
     * @param t Index of the task to untag.
     * @param tag Tag to be deleted from the task.
     * @return A string informing the user about whether the task was untagged or not.
     * @throws DukeException if the user entered an invalid index.
     */
    public String untag(int t, Tag tag) throws DukeException {
        if (tasks.isEmpty()) {
            return Ui.START + "there are no tasks in your list. add some now!";
        }

        Task task;

        try {
            task = tasks.get(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.requestValidInput(tasks.size()));
        }

        return task.deleteTag(tag);
    }

    /**
     * Prints out a list of tags under the specified task.
     *
     * @param t Index of the task.
     * @return A string containing a list of tags under the specified task, if any.
     * @throws DukeException if the user entered an invalid index.
     */
    public String getTags(int t) throws DukeException {
        if (tasks.isEmpty()) {
            return Ui.START + "there are no tasks in your list. add some now!";
        }

        Task task;

        try {
            task = tasks.get(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.requestValidInput(tasks.size()));
        }

        return task.printTags();
    }

    /**
     * Saves the contents of the task list to a data file.
     *
     * @param storage Storage to save the tasks to the hard drive.
     * @throws IOException if the data file cannot be found on the hard drive.
     */
    public void writeToFile(Storage storage) throws IOException {
        int len = tasks.size();
        String[] taskDescriptions = new String[len];
        for (int i = 0; i < len; i++) {
            taskDescriptions[i] = tasks.get(i).fileDescription()
                    + tasks.get(i).fileTags() + "\n";
        }
        storage.save(taskDescriptions);
    }
}

package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
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
            if (info[0].equals("T")) {
                t = new ToDo(info[2]);
            } else if (info[0].equals("D")) {
                if (info.length == 4) {
                    t = new Deadline(info[2], LocalDate.parse(info[3]));
                } else {
                    t = new Deadline(
                            info[2], LocalDate.parse(info[3]), LocalTime.parse(info[4]));
                }
            } else {
                try {
                    t = new Event(
                            info[2], info[3], LocalDateTime.parse(info[4]),
                            LocalDateTime.parse(info[5]));
                } catch (DateTimeParseException e) {
                    t = new Event(
                            info[2], info[3], LocalDateTime.parse(info[4]),
                            LocalTime.parse(info[5]));
                }
            }
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
        if (tasks.size() == 0) {
            return Ui.START + "your list is empty. start adding some tasks to do now!";
        } else {
            String reply = Ui.START + "these are the tasks in your list:";
            int x = 1;
            for (Task task : tasks) {
                reply += "\n  " + x + ". " + task.toString();
                x++;
            }
            assert x > 1 : "No tasks in task list.";
            return reply;
        }
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
            reply += "\n  you now have 1 task in the list. type list to see it!";
        } else {
            reply += "\n  now you have " + tasks.size() + " tasks in the list. type list to view them.";
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
        if (tasks.size() == 0) {
            return Ui.START + "hmm, you do not have any tasks in your list to delete. add some now!";
        } else {
            String reply = "";
            try {
                Task t = tasks.remove(i);
                reply += Ui.START + "okay! i have deleted the following task from your list:";
                reply += "\n     " + t;
                if (tasks.size() == 0) {
                    reply += "\n  your list is now empty. time to add some more!";
                } else if (tasks.size() == 1) {
                    reply += "\n  you now have 1 task remaining in the list. type list to see it!";
                } else {
                    reply += "\n  now you have " + tasks.size() + " tasks in the list. type list to view them.";
                }
                return reply;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.SAD_FACE + "please enter an integer from 1 - " + tasks.size());
            }
        }
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
        try {
            if (tasks.size() == 0) {
                return Ui.START + "hmm, you do not have any tasks in your list to be marked. add some now!";
            } else {
                Task doneTask = tasks.get(t);
                doneTask.setDone();
                return Ui.START + "good job! this task has been marked as done:"
                        + "\n     " + doneTask;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.SAD_FACE + "please enter an integer from 1 - " + tasks.size());
        }
    }

    /**
     * Marks the task at the specified position of the task list
     * as not done.
     *
     * @param t Index of the task to unmark.
     * @return A string informing the user that the specified task was unmarked, or that
     *     there are no tasks to be unmarked if the task list is empty.
     * @throws DukeException if the user entered an invalid index.
     */
    public String unmark(int t) throws DukeException {
        try {
            if (tasks.size() == 0) {
                return Ui.START + "hmm, you do not have any tasks in your list. add some now!";
            } else {
                Task undoneTask = tasks.get(t);
                undoneTask.setUndone();
                return Ui.START + "ok, i've marked this task as not done yet:"
                        + "\n     " + undoneTask;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.SAD_FACE + "please enter an integer from 1 - " + tasks.size());
        }
    }

    /**
     * Saves the contents of the task list to a data file.
     *
     * @param storage Storage to save the tasks to the hard drive.
     * @throws IOException If the data file cannot be found on the hard drive.
     */
    public void writeToFile(Storage storage) throws IOException {
        int len = tasks.size();
        String[] taskDescriptions = new String[len];
        for (int i = 0; i < len; i++) {
            taskDescriptions[i] = tasks.get(i).fileDescription() + "\n";
        }
        storage.save(taskDescriptions);
    }
}

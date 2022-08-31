package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
     */
    public void printList() {
        if (tasks.size() == 0) {
            System.out.println(Ui.START + "your list is empty. start adding some tasks to do now!");
        } else {
            System.out.println(Ui.START + "these are the tasks in your list:");
            int x = 1;
            for (Task task : tasks) {
                System.out.println("  " + x + ". " + task.toString());
                x++;
            }
        }
    }

    /**
     * Adds the given task to the task list.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
        System.out.println(Ui.START + "added:\n" + "     " + t);
        if (tasks.size() == 1) {
            System.out.println("  you now have 1 task in the list. type list to see it!");
        } else {
            System.out.println("  now you have " + tasks.size() + " tasks in the list. type list to view them.");
        }
    }

    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param i Index of the task to delete
     */
    public void delete(int i) {
        if (tasks.size() == 0) {
            System.out.println(
                    Ui.START + "hmm, you do not have any tasks in your list to delete. add some now!"
            );
        } else {
            try {
                Task t = tasks.remove(i);
                System.out.println(Ui.START + "okay! i have deleted the following task from your list:");
                System.out.println("     " + t);
                if (tasks.size() == 0) {
                    System.out.println("  your list is now empty. time to add some more!");
                } else if (tasks.size() == 1) {
                    System.out.println("  you now have 1 task remaining in the list. type list to see it!");
                } else {
                    System.out.println(
                            "  now you have " + tasks.size() + " tasks in the list. type list to view them.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(Ui.SAD_FACE + "please enter an integer from 1 - " + tasks.size());
            }
        }
    }

    /**
     * Searches for tasks occurring on the specified date and returns
     * a list of the results to the user.
     *
     * @param date Date to search for.
     */
    public void search(LocalDate date) {
        String list = "";
        int x = 1;
        for (Task task : tasks) {
            if (task.isOn(date)) {
                list = list + "\n  " + x + ". " + task;
                x++;
            }
        }
        if (x == 1) {
            System.out.println(
                    Ui.START + "There are no tasks occurring on " + date + ".");
        } else {
            System.out.println(
                    Ui.START + "These are the tasks occurring on " + date + ":" + list
            );
        }
    }

    /**
     * Finds tasks containing the given keyword(s) and returns
     * a list of the results to the user.
     *
     * @param keyword Keyword(s) to search for.
     */
    public void find(String keyword) {
        String list = "";
        int x = 1;
        for (Task task : tasks) {
            if (task.containsSearchTerm(keyword)) {
                list = list + "\n  " + x + ". " + task;
                x++;
            }
        }
        if (x == 1) {
            System.out.println(
                    Ui.START + "there are no tasks matching the search term '"
                            + keyword + "'.");
        } else {
            System.out.println(
                    Ui.START + "These are the matching tasks in your list:" + list
            );
        }
    }

    /**
     * Marks the task at the specified position in the task list
     * as done.
     *
     * @param t Index of the task to mark.
     */
    public void mark(int t) {
        try {
            if (tasks.size() == 0) {
                System.out.println(
                        Ui.START + "hmm, you do not have any tasks in your list to be marked. add some now!"
                );
            } else {
                Task doneTask = tasks.get(t);
                doneTask.setDone();
                System.out.println(Ui.START + "good job! this task has been marked as done:");
                System.out.println("     " + doneTask);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.SAD_FACE + "please enter an integer from 1 - " + tasks.size());
        }
    }

    /**
     * Marks the task at the specified position of the task list
     * as not done.
     *
     * @param t Index of the task to unmark.
     */
    public void unmark(int t) {
        try {
            if (tasks.size() == 0) {
                System.out.println(Ui.START + "hmm, you do not have any tasks in your list. add some now!");
            } else {
                Task undoneTask = tasks.get(t);
                undoneTask.setUndone();
                System.out.println(Ui.START + "ok, i've marked this task as not done yet:");
                System.out.println("     " + undoneTask);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.SAD_FACE + "please enter an integer from 1 - " + tasks.size());
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

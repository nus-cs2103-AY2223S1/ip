package duke;

import duke.task.Note;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Keeps track of the current tasks of the user.
 *
 * @author Lim Ai Lin
 */
public class TaskList {

    private final ArrayList<Task> MY_TASKS;
    private final ArrayList<Note> MY_NOTES;
    public TaskList(ArrayList<Task> tasks, ArrayList<Note> notes) {
        this.MY_TASKS = tasks;
        this.MY_NOTES = notes;
    }

    /**
     * Prints out all tasks in the list.
     */
    public String list() {
        StringBuilder builder = new StringBuilder();
        if (MY_TASKS.size() > 0) {
            builder.append("RAWR! Here is your tasks:\n");
            for (int i = 0; i < MY_TASKS.size(); i++) {
                builder.append(i + 1).append(". ")
                        .append(MY_TASKS.get(i).toString()).append("\n");
            }
        } else {
            builder.append("RAWR! You have no tasks!\n");
        }

        if (MY_NOTES.size() > 0) {
            builder.append("\nRAWR! Here is your notes:\n");
            for (Note my_note : MY_NOTES) {
                builder.append("> ")
                        .append(my_note.toString()).append("\n");
            }
        } else {
            builder.append("\nRAWR! You have no notes!\n");
        }

        return builder.toString();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The specified task to be added.
     */
    public void add(Task task) {
        MY_TASKS.add(task);
    }

    /**
     * Gets the task at a specified index.
     *
     * @param i The index of the task to be returned.
     * @return The task at index i.
     */
    public Task getTask(int i) {
        assert i > 0 && i < MY_TASKS.size();
        return MY_TASKS.get(i);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        assert i > 0 && i < MY_TASKS.size();
        MY_TASKS.remove(i);
    }

    /**
     * Gets the size of the task list.
     *
     * @return The number of items in the task list.
     */
    public int getTaskSize() {
        return MY_TASKS.size();
    }

    /**
     * Gets the size of the note list.
     *
     * @return The number of items in the note list.
     */
    public int getNoteSize() {
        return MY_NOTES.size();
    }

    /**
     * Gets tasks containing given keyword.
     *
     * @param match The given keyword.
     * @return The tasks with the given keyword in the description.
     */
    public ArrayList<Task> findTask(String match) {
        ArrayList<Task> matches = new ArrayList<>();
        String matching = match.toUpperCase(Locale.ROOT);
        for (Task task : MY_TASKS) {
            String description = task.getDescription().toUpperCase(Locale.ROOT);
            if (description.contains(matching)) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Gets notes containing given keyword.
     *
     * @param match The given keyword.
     * @return The notes with the given keyword in the description.
     */
    public ArrayList<Note> findNote(String match) {
        ArrayList<Note> matches = new ArrayList<>();
        String matching = match.toUpperCase(Locale.ROOT);
        for (Note note : MY_NOTES) {
            String description = note.getDescription().toUpperCase(Locale.ROOT);
            if (description.contains(matching)) {
                matches.add(note);
            }
        }
        return matches;
    }

    /**
     * Adds a new note to the list.
     *
     * @param note The specified task to be added.
     */
    public void add(Note note) {
        MY_NOTES.add(note);
    }

    /**
     * Gets the note at a specified index.
     *
     * @param i The index of the note to be returned.
     * @return The note at index i.
     */
    public Note getNote(int i) {
        assert i > 0 && i < MY_NOTES.size();
        return MY_NOTES.get(i);
    }
}

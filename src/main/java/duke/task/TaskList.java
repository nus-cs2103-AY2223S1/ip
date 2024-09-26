package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a list of <code>Task</code> objects.
 */
public class TaskList {
    private LinkedList<Task> tasks = new LinkedList<>();
    private LinkedList<Note> notes = new LinkedList<>();

    /**
     * Constructs a <code>TaskList</code> containing <code>Task</code> from the data file.
     *
     * @param dataFile file with stored tasks
     */
    public TaskList(File dataFile) {
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String[] splitInput = scanner.nextLine().split(",");
                String taskType = splitInput[0];
                String taskDescription = splitInput[2];
                boolean isDone = splitInput[1].equals("1");
                switch (taskType) {
                case "T":
                    Task todo = new Todo(taskDescription);
                    todo.setDone(isDone);
                    tasks.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(taskDescription, LocalDate.parse(splitInput[3]));
                    deadline.setDone(isDone);
                    tasks.add(deadline);
                    break;
                case "E":
                    Task event = new Event(taskDescription, LocalDate.parse(splitInput[3]));
                    event.setDone(isDone);
                    tasks.add(event);
                    break;
                case "N":
                    Note note = new Note(taskDescription);
                    notes.add(note);
                    break;
                default:
                    throw new DukeException("Error is loading saved data!");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param newTask new task being added to <code>TaskList</code>
     * @return successful task creation message
     */
    public String addTask(Task newTask) {
        tasks.add(newTask);
        return Ui.printTaskCreationMessage(newTask, tasks.size());
    }

    /**
     * Deletes <code>Task</code> at the given index from <code>TaskList</code>
     *
     * @param index index of task being deleted
     * @return successful task deletion message
     */
    public String deleteTask(int index) {
        if (index < 0 || index > tasks.size() - 1) {
            throw new DukeException("Invalid task index!");
        }
        Task deletedTask = tasks.remove(index);
        return Ui.printTaskDeletionMessage(deletedTask, tasks.size());
    }

    /**
     * @param newNote new note being added to <code>TaskList</code>
     * @return successful note creation message
     */
    public String addNote(Note newNote) {
        notes.add(newNote);
        return Ui.printTaskCreationMessage(newNote, notes.size());
    }

    /**
     * Deletes <code>Note</code> at the given index from <code>TaskList</code>
     *
     * @param index index of note being deleted
     * @return successful note deletion message
     */
    public String deleteNote(int index) {
        if (index < 0 || index > notes.size() - 1) {
            throw new DukeException("Invalid note index!");
        }
        Task deletedTask = notes.remove(index);
        return Ui.printTaskDeletionMessage(deletedTask, notes.size());
    }


    /**
     * Marks the <code>Task</code> at the given index as done.
     *
     * @param index index of Task to be marked
     * @return successful task marking message
     */
    public String markAsDone(int index) {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("No task at this index!");
        }
        Task task = tasks.get(index);
        return task.markAsDone();
    }

    /**
     * Marks the <code>Task</code> at the given index as not done.
     *
     * @param index index of Task to be marked
     * @return successful task marking message
     */
    public String markAsNotDone(int index) {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("No task at this index!");
        }
        Task task = tasks.get(index);
        assert task != null : "Task not found";
        return task.markAsNotDone();
    }

    /**
     * Returns this <code>TaskList</code> in a CSV format.
     *
     * @return CSV representation of this <code>TaskList</code>
     */
    public String toCsv() {
        StringBuilder csv = new StringBuilder();
        for (Task task : tasks) {
            csv.append(task.toCsv());
        }
        for (Note note : notes) {
            csv.append(note.toCsv());
        }
        return csv.toString();
    }

    /**
     * Returns true if, and only if, this <code>TaskList</code> has zero tasks.
     *
     * @return true if number of tasks in <code>TaskList</code> is 0, otherwise false
     */
    public boolean isEmpty() {
        return tasks.isEmpty() && notes.isEmpty();
    }

    /**
     * Prints the list of <code>Task</code> in this <code>TaskList</code> with the keyword in it.
     *
     * @param keyword word being searched for in the <code>TaskList</code>
     */
    public String find(String keyword) {
        StringBuilder match = new StringBuilder();
        int i = 0;
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                i++;
                match.append(i).append(". ").append(task).append("\n");
            }
        }
        return Ui.printTaskSearch(match.toString());
    }

    /**
     * Returns a string representation of tasks in the <code>TaskList</code>
     *
     * @return a string representation of tasks in the <code>TaskList</code>
     */
    public String tasksToString() {
        StringBuilder strTasks = new StringBuilder().append("Tasks\n");
        if (tasks.isEmpty()) {
            strTasks.append("You have no tasks in your list\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                strTasks.append(index).append(". ").append(tasks.get(i).toString()).append("\n");
            }
        }
        return strTasks.toString();
    }

    /**
     * Returns a string representation of notes in the <code>TaskList</code>
     *
     * @return a string representation of notes in the <code>TaskList</code>
     */
    public String notesToString() {
        StringBuilder strNotes = new StringBuilder().append("Notes\n");
        if (notes.isEmpty()) {
            strNotes.append("You have no notes in your list\n");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                int index = i + 1;
                strNotes.append(index).append(". ").append(notes.get(i).toString()).append("\n");
            }
        }
        return strNotes.toString();
    }

    /**
     * Returns a string representation of this <code>TaskList</code>.
     *
     * @return a string representation of this <code>TaskList</code>
     */
    @Override
    public String toString() {
        return tasksToString() + notesToString();
    }
}

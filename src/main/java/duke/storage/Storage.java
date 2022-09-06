package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.utilities.DukeException;

/**
 * Storage class, handles loading and saving tasks to hard disk.
 */
public class Storage {
    /** The separator used to separate text in storage. */
    private static final String SEPARATOR = "|";
    /** A File object to represent the File we read from and write to. */
    private final File file;


    /**
     * Constructor for Storage object.
     *
     * @param filePath The location on disk to store the tasks.
     * @throws IOException Handles case of File not being found or location not existing.
     */
    public Storage(String filePath) throws IOException {
        String[] parts = filePath.split("/");

        File folder = new File(parts[0]);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        this.file = file;
    }

    /**
     * Writes the tasks in the task list to hard disk, a .txt file.
     *
     * @param taskList The task list object, whose tasks we want to store on disk.
     * @throws IOException Handles File not being found when writing to hard disk.
     */
    public void writeTasksToStorage(TaskList taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getTasks();
        FileWriter fileWriter = new FileWriter(this.file);

        for (Task task : tasks) {
            StringBuilder rep = new StringBuilder();
            if (task instanceof Todo) {
                Todo t = (Todo) task;
                rep.append(convertTodoToStorageFormat(t));
            } else if (task instanceof Event) {
                Event e = (Event) task;
                rep.append(convertEventToStorageFormat(e));
            } else if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                rep.append(convertDeadlineToStorageFormat(d));
            }

            fileWriter.write(rep.toString());
            fileWriter.write(System.lineSeparator());
        }

        fileWriter.close();
    }

    /**
     * Reads the saved tasks in from hard disk.
     *
     * @return Returns an ArrayList of Task objects for use in TaskList.
     * @throws FileNotFoundException Handles cases where File is not found.
     * @throws DukeException Handles case of there not being tasks in storage.
     */
    public ArrayList<Task> readTasksFromStorage() throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] lineComponents = line.split("\\|");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            String taskType = lineComponents[0];
            boolean isDone = lineComponents[1].equals("X");
            String description = lineComponents[2];

            switch (taskType) {
            case "T":
                Todo t = new Todo(description);
                t.setDoneStatus(isDone);
                tasks.add(t);
                break;
            case "D":
                LocalDateTime by = LocalDateTime.parse(lineComponents[3], dateFormat);
                Deadline d = new Deadline(description, by);
                d.setDoneStatus(isDone);
                tasks.add(d);
                break;
            case "E":
                LocalDateTime from = LocalDateTime.parse(lineComponents[3], dateFormat);
                LocalDateTime to = LocalDateTime.parse(lineComponents[4], dateFormat);
                Event e = new Event(description, to, from);
                e.setDoneStatus(isDone);
                tasks.add(e);
                break;
            default:
                throw new DukeException("No tasks to read from storage!");
            }
        }

        sc.close();

        return tasks;
    }

    /**
     * Converts a to-do object into it's storage String format.
     *
     * @param todo The todo task.
     * @return The string format by which to store a to-do object.
     */
    public String convertTodoToStorageFormat(Todo todo) {
        StringBuilder rep = new StringBuilder();
        rep.append("T");
        rep.append(SEPARATOR);
        rep.append(todo.getDoneStatus());
        rep.append(SEPARATOR);
        rep.append(todo.getDescription());
        return rep.toString();
    }

    /**
     * Converts a deadline object into it's storage String format.
     *
     * @param deadline The deadline task.
     * @return The string format by which to store a deadline object.
     */
    public String convertDeadlineToStorageFormat(Deadline deadline) {
        StringBuilder rep = new StringBuilder();
        rep.append("D");
        rep.append(SEPARATOR);
        rep.append(deadline.getDoneStatus());
        rep.append(SEPARATOR);
        rep.append(deadline.getDescription());
        rep.append(SEPARATOR);
        rep.append(deadline.getBy());
        return rep.toString();
    }

    /**
     * Converts an event object into it's storage String format.
     *
     * @param event The event task.
     * @return The string format by which to store the event object.
     */
    public String convertEventToStorageFormat(Event event) {
        StringBuilder rep = new StringBuilder();
        rep.append("E");
        rep.append(SEPARATOR);
        rep.append(event.getDoneStatus());
        rep.append(SEPARATOR);
        rep.append(event.getDescription());
        rep.append(SEPARATOR);
        rep.append(event.getStart());
        rep.append(SEPARATOR);
        rep.append(event.getEnd());
        return rep.toString();
    }
}

package jenny.storage;

import jenny.exceptions.JennyException;
import jenny.tasks.*;
import jenny.util.Validator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


/**
 * Handles storage for a list of {@link Task Task>}.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 * @param <T> the type of item to store.
 */
public class TaskStorage<T> extends Storage<T> {
    private static final String MESSAGE_SCOPE = TaskStorage.class.getSimpleName();
    private static final String ERROR_INVALID_TYPE = "TaskStorage can only save a list of tasks!";
    private static final String ERROR_CORRUPTED_SAVE = "Your save contains corrupted data!";

    private final Path filePath;

    /**
     * Constructor for an instance of a new storage.
     * Will initialise folders at the default home location {@code [user.home]},
     * under the provided folderName {@code [jenny.storage.tasks]}.
     * A storage file will be created under the provided {@code fileName}.
     *
     * @param fileName the name of the storage file.
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    public TaskStorage(String fileName) throws JennyException {
        super("tasks"); // throws JennyException
        this.filePath = Paths.get(super.toString(), fileName);
    }

    /**
     * {@inheritDoc}
     *
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    @Override
    public void save(T t) throws JennyException {
        boolean isArrayList = t instanceof ArrayList;
        if (!isArrayList) {
            assert !isArrayList;
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_TYPE);
        }
        assert isArrayList;

        @SuppressWarnings("unchecked")
        ArrayList<Task> tasks = (ArrayList<Task>) t;

        try {
            FileWriter fileWriter = new FileWriter(filePath.toFile());
            for (Task task : tasks) {
                fileWriter.write(String.format("%s\n", task.save()));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }

    }

    /**
     * {@inheritDoc}
     *
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    @Override
    public T load() throws JennyException {
        try {
            FileReader fileReader = new FileReader(filePath.toFile());
            Scanner scanner = new Scanner(fileReader);
            ArrayList<Task> tasks = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String jennyTask = scanner.nextLine();
                String[] data = jennyTask.split(",");
                loadHelper(tasks, data); // Throws JennyException
            }

            @SuppressWarnings("unchecked")
            T t = (T) tasks;
            return t;
        } catch (FileNotFoundException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }

    private void loadHelper(ArrayList<Task> tasks, String[] data) throws JennyException {
        switch (data[0]) {
            case "DeadlineTask":
                loadDeadline(tasks, data);
                break;
            case "EventTask":
                loadEvent(tasks, data);
                break;
            case "TodoTask":
                loadTodo(tasks, data);
                break;
            case "NoteTask":
                loadNote(tasks, data);
                break;
            default:
                throw new JennyException(MESSAGE_SCOPE, ERROR_CORRUPTED_SAVE);
        }
    }

    private void loadDeadline(ArrayList<Task> tasks, String[] data) throws JennyException {
        try {
            LocalDate dueDate = Validator.parseDate(data[3]);
            Task task = new DeadlineTask(data[2], dueDate);
            tasks.add(task);
            if ((Objects.equals(data[1], "true"))) {
                task.mark();
            } else {
                task.unmark();
            }
        } catch (JennyException e) {
            throw new JennyException(MESSAGE_SCOPE,
                String.format("Tried to parse [%s] as a date. Failed Reason: %s", data[2], e.getMessage()));
        }
    }

    private void loadEvent(ArrayList<Task> tasks, String[] data) throws JennyException {
        try {
            LocalDate dueDate = Validator.parseDate(data[3]);
            Task task = new EventTask(data[2], dueDate);
            tasks.add(task);
            if ((Objects.equals(data[1], "true"))) {
                task.mark();
            } else {
                task.unmark();
            }
        } catch (JennyException e) {
            throw new JennyException(MESSAGE_SCOPE,
                String.format("Tried to parse [%s] as a date. Failed Reason: %s", data[2], e.getMessage()));
        }
    }

    private void loadTodo(ArrayList<Task> tasks, String[] data) throws JennyException {
        Task task = new TodoTask(data[2]);
        tasks.add(task);
        if ((Objects.equals(data[1], "true"))) {
            task.mark();
        } else {
            task.unmark();
        }
    }

    private void loadNote(ArrayList<Task> tasks, String[] data) throws JennyException {
        Task task = new NoteTask(data[2]);
        tasks.add(task);
        if ((Objects.equals(data[1], "true"))) {
            task.mark();
        } else {
            task.unmark();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representation of the path to the file.
     */
    @Override
    public String toString() {
        return filePath.toString();
    }
}

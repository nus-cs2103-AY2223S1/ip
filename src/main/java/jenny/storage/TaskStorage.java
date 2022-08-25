package jenny.storage;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;
import jenny.tasks.DeadlineTask;
import jenny.tasks.EventTask;
import jenny.tasks.TodoTask;
import jenny.util.Printer;
import jenny.util.UserInterface;
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
 * Serves as a base class for different types of storage.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @param <T> the type of item to process.
 * @author Deon
 */
public class TaskStorage<T> extends AbstractStorage<T> {
    private static final String MESSAGE_SCOPE = TaskStorage.class.getSimpleName();
    private static final String ERROR_INVALID_TYPE = "TaskStorage can only save a list of tasks!";
    private static final String ERROR_NOTHING_TO_SAVE = "There is nothing in your list to save!";
    private static final String SUCCESS_WRITING_FILE = "List saved successfully.";
    private static final String ERROR_WRITING_FILE = "Failed to save your list!";
    private static final String ERROR_CORRUPTED_SAVE = "Your save contains corrupted data!";
    private static final String ERROR_NO_SAVE_FOUND = "Could not find your save!";

    private final Path filePath;

    /**
     * Initialise a storage at the default home location under the folderName: tasks.
     *
     * @param fileName name of the file in storage.
     */
    public TaskStorage(String fileName) {
        super("tasks");
        this.filePath = Paths.get(super.toString(), fileName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(T t) throws JennyException {
        if (!(t instanceof ArrayList)) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_TYPE);
        }

        ArrayList<?> tArrayList = (ArrayList<?>) t;
        if (tArrayList.isEmpty()) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_NOTHING_TO_SAVE);
        }

        if (!(tArrayList.get(0) instanceof AbstractTask)) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_TYPE);
        }

        @SuppressWarnings("unchecked")
        ArrayList<AbstractTask> tasks = (ArrayList<AbstractTask>) tArrayList;

        try {
            FileWriter fileWriter = new FileWriter(filePath.toFile());
            for (AbstractTask task : tasks) {
                fileWriter.write(String.format("%s\n", task.save()));
            }
            fileWriter.close();
            UserInterface.print(SUCCESS_WRITING_FILE);
        } catch (IOException e) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_WRITING_FILE);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T load() throws JennyException {
        try {
            FileReader fileReader = new FileReader(filePath.toFile());
            Scanner scanner = new Scanner(fileReader);
            ArrayList<AbstractTask> tasks = new ArrayList<>();
            AbstractTask task;
            LocalDate dueDate;

            while (scanner.hasNextLine()) {
                String jennyTask = scanner.nextLine();
                String[] data = jennyTask.split(",");

                switch (data[0]) {
                case "DeadlineTask":
                    try {
                        dueDate = Validator.parseDate(data[3]);
                        task = new DeadlineTask(data[2], dueDate);
                        tasks.add(task);
                        task.markAsDone((Objects.equals(data[1], "true")));
                    } catch (JennyException e) {
                        throw new JennyException(MESSAGE_SCOPE, String.format("Tried to parse [%s] as a date. Failed Reason: %s",
                                data[2], e.getMessage()));
                    }
                    break;

                case "EventTask":
                    try {
                        dueDate = Validator.parseDate(data[3]);
                        task = new EventTask(data[2], dueDate);
                        tasks.add(task);
                        task.markAsDone((Objects.equals(data[1], "true")));
                    } catch (JennyException e) {
                        throw new JennyException(MESSAGE_SCOPE, String.format("Tried to parse [%s] as a date. Failed Reason: %s",
                                data[2], e.getMessage()));
                    }
                    break;

                case "TodoTask":
                    task = new TodoTask(data[2]);
                    tasks.add(task);
                    task.markAsDone((Objects.equals(data[1], "true")));
                    break;

                default:
                    throw new JennyException(MESSAGE_SCOPE, ERROR_CORRUPTED_SAVE);
                }
            }

            @SuppressWarnings("unchecked")
            T t = (T) tasks;
            return t;
        } catch (FileNotFoundException e) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_NO_SAVE_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return the path to the file.
     */
    @Override
    public String toString() {
        return filePath.toString();
    }
}

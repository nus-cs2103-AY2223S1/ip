package jenny.storage;

import jenny.tasks.AbstractTask;
import jenny.tasks.DeadlineTask;
import jenny.tasks.EventTask;
import jenny.tasks.TodoTask;
import jenny.util.Printer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public void save(T t) {
        if (!(t instanceof ArrayList)) {
            Printer.echo(MESSAGE_SCOPE, ERROR_INVALID_TYPE);
            return;
        }

        ArrayList<?> tArrayList = (ArrayList<?>) t;
        if (tArrayList.isEmpty()) {
            Printer.echo(MESSAGE_SCOPE, ERROR_NOTHING_TO_SAVE);
            return;
        }

        if (!(tArrayList.get(0) instanceof AbstractTask)) {
            Printer.echo(MESSAGE_SCOPE, ERROR_INVALID_TYPE);
            return;
        }

        @SuppressWarnings("unchecked")
        ArrayList<AbstractTask> tasks = (ArrayList<AbstractTask>) tArrayList;

        try {
            FileWriter fileWriter = new FileWriter(filePath.toFile());
            for (AbstractTask task : tasks) {
                fileWriter.write(String.format("%s\n", task.save()));
            }
            fileWriter.close();
            Printer.echo(MESSAGE_SCOPE, SUCCESS_WRITING_FILE);
        } catch (IOException e) {
            Printer.echo(MESSAGE_SCOPE, ERROR_WRITING_FILE);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T load() {
        try {
            FileReader fileReader = new FileReader(filePath.toFile());
            Scanner scanner = new Scanner(fileReader);
            ArrayList<AbstractTask> tasks = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String jennyTask = scanner.nextLine();
                String[] data = jennyTask.split(",");

                switch (data[0]) {
                case "DeadlineTask":
                    tasks.add(new DeadlineTask(data[1], data[2]));
                    break;

                case "EventTask":
                    tasks.add(new EventTask(data[1], data[2]));
                    break;

                case "TodoTask":
                    tasks.add(new TodoTask(data[1]));
                    break;

                default:
                    Printer.echo(MESSAGE_SCOPE, ERROR_CORRUPTED_SAVE);
                }
            }

            @SuppressWarnings("unchecked")
            T t = (T) tasks;
            return t;
        } catch (FileNotFoundException e) {
            Printer.echo(MESSAGE_SCOPE, ERROR_NO_SAVE_FOUND);
            return null;
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

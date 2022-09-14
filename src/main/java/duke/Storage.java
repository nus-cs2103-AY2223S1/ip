package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Encapsulation of reading from and writing into file.
 *
 * @author   Sun Ruoxin
 */
public class Storage {
    /** The file being read from and write to. */
    protected File file;

    /**
     * Class constructor.
     *
     * @param filePath the path of the file
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads from the file containing existing records of tasks.
     *
     * @return the list of existing tasks
     */
    public ArrayList<Task> load() {
        initialiseFile();
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task task = Parser.toTask(scanner.nextLine());
                taskList.add(task);
            }
            scanner.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return taskList;
    }

    /**
     * Creates the directory and the file if not already exist.
     */
    public void initialiseFile() {
        File directory = new File(this.file.getParent());
        if (!directory.exists()) {
            directory.mkdir();
        }
        assert directory.exists() : "The directory should already exist";
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            assert file.exists() : "The file should already exist";
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Writes the current list of tasks to the file.
     *
     * @param list the current list of tasks
     */
    public void writeFile(TaskList list) {
        initialiseFile();

        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                if (task instanceof Todo) {
                    writer.write(task.getType() + " | " + task.getStatusInt() + " | " + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    writer.write(task.getType() + " | " + task.getStatusInt() + " | " + task.getDescription()
                            + " | " + deadline.getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n");
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    writer.write(task.getType() + " | " + task.getStatusInt() + " | " + task.getDescription()
                            + " | " + event.getAt() + "\n");
                }
            }
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

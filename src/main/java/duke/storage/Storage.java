package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.domain.task.Task;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskSpecificationException;

/**
 * Storage class in charge of storing the tasks in a text file.
 */
public class Storage {

    private static final String DELIMITER = "@@@";
    private static final File DATA_FILE = new File("tasks.txt");

    /**
     * Ensures that the data file exists, then reads the data file and returns a
     * list of tasks
     *
     * @return A list of loaded tasks
     */
    public List<Task> load() {
        List<Task> initTask = new ArrayList<>();

        if (!DATA_FILE.exists()) {
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        assert (DATA_FILE.exists());

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(DATA_FILE));
            String taskString = reader.readLine();
            while (taskString != null) {
                String[] taskArgs = taskString.split(DELIMITER);
                try {
                    initTask.add(
                            Task.of(
                                    taskArgs[0],
                                    taskArgs[1],
                                    taskArgs[2],
                                    taskArgs[3]));
                } catch (InvalidDateTimeException | InvalidTaskSpecificationException e) {
                    e.printStackTrace();
                }
                taskString = reader.readLine();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return initTask;
    }

    /**
     * Writes the data to the file.
     *
     * @param tasks
     *            The list of tasks to be written to the file.
     */
    public void writeDataToFile(List<String> tasks) {
        if (!DATA_FILE.exists()) {
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter writer;
        try {
            writer = new FileWriter(DATA_FILE.getAbsolutePath(), false);
            for (String taskString : tasks) {
                writer.write(String.format("%s%s", taskString, "\n"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

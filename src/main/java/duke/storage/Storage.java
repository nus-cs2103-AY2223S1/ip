package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import duke.domain.Task;

/**
 * Storage class in charge of storing the tasks in a text file.
 */
public class Storage {
    private static final String delimiter = "@@@";

    /**
     * Ensures that the data file exists, then reads the data file and returns a
     * list of tasks
     * 
     * @param filePath
     *            The path to the file where the data is stored.
     * @return A list of tasks
     */
    public List<Task> load(String filePath) {
        try {
            ensureDataFileExist(filePath);
            List<Task> initTask = new ArrayList<>();

            File dataFile = new File(filePath);
            try {
                Scanner sc = new Scanner(dataFile);
                while (sc.hasNextLine()) {
                    String taskString = sc.nextLine();
                    if (!Objects.equals(taskString, "")) {
                        String[] taskArgs = taskString.split(delimiter);
                        initTask.add(
                                Task.of(
                                        taskArgs[0],
                                        taskArgs[1],
                                        taskArgs[2],
                                        taskArgs[3]));
                    }
                }
                sc.close();
                return initTask;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(
                        "This cannot happen has the duke/data file is ensured to be there");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Writes the data to the file.
     * 
     * @param filePath
     *            The path to the file you want to write to.
     * @param tasks
     *            The list of tasks to be written to the file.
     */
    public void writeDataToFile(String filePath, List<String> tasks) {
        FileWriter writer;
        try {
            writer = new FileWriter(filePath, false);
            for (String taskString : tasks) {
                writer.write(String.format("%s%s", taskString, "\n"));
            }
            try {
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Creates the data file if it doesn't exist
     * 
     * @param filePath
     *            The path to the file that will be created.
     */
    private static void ensureDataFileExist(String filePath)
            throws IOException, SecurityException {
        Path dataFilePath = Paths.get(filePath);
        boolean dataFileExists = Files.exists(dataFilePath);
        if (!(dataFileExists)) {
            createDataFile(filePath);
        }
        new File(filePath);
    }

    /**
     * It creates a file at the given path
     * 
     * @param filePath
     *            The path to the file you want to create.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void createDataFile(String filePath) throws IOException, SecurityException {
        File dataFile = new File(filePath);
        dataFile.getParentFile().mkdirs();
        dataFile.createNewFile();
    }
}

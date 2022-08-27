package blob.storage;

import blob.exception.ErrorLoadingTaskException;
import blob.exception.InvalidDateFormatException;
import blob.tasks.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The Storage class deals with loading and saving tasks from a text file.
 */
public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public TaskList loadTaskList() throws ErrorLoadingTaskException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Files.createDirectories(filePath.getParent());
            File taskFile = filePath.toFile();
            if (!taskFile.createNewFile()) {
                Scanner sc = new Scanner(new FileReader(taskFile));
                while (sc.hasNextLine()) {
                    String[] deconstructedDetails = sc.nextLine().trim().split("\\s+\\|\\s+");
                    String taskType = deconstructedDetails[0];
                    String done = deconstructedDetails[1];
                    String description = deconstructedDetails[2];
                    Task task = null;

                    if (Objects.equals(taskType, "T")) {
                        task = new ToDo(description);
                    } else if (Objects.equals(taskType, "D")) {
                        task = new Deadline(description, deconstructedDetails[3]);
                    } else if (Objects.equals(taskType, "E")) {
                        task = new Event(description, deconstructedDetails[3]);
                    }

                    if (Objects.equals(done, "1")) {
                        task.markAsDone();
                    }

                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            throw new ErrorLoadingTaskException();
        } catch (InvalidDateFormatException e) {
            // Improper datetime, do nothing (don't add to task list)
        }

        return new TaskList(taskList);
    }

    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter taskFileWriter = new FileWriter(filePath.toFile());
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                taskFileWriter.write(taskList.getTask(i).toFileString());
                taskFileWriter.write(System.lineSeparator());
            }
            taskFileWriter.close();
        } catch (IOException e) {
        }
    }
}
